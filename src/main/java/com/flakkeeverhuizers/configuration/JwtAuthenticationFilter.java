package com.flakkeeverhuizers.configuration;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.flakkeeverhuizers.configuration.Constants.*;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        String email = null;
        String authToken = null;
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX,"");
            try {
                email = jwtTokenUtil.getEmailFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("An error occurred while trying to fetch mail from the token", e);
            } catch (ExpiredJwtException e) {
                // Refresh token authorization flow
                String isRefreshToken = req.getHeader("isRefreshToken");
                String requestURL = req.getRequestURL().toString();

                if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("generate-refresh-token")) {
                    allowForRefreshToken(e, req);
                } else {
                    req.setAttribute("exception", e);

                    logger.warn("The token has expired and is no longer valid", e);
                }
            } catch(SignatureException e){
                logger.error("Authentication failed. Invalid e-mail or password");
            }
        } else {
            logger.warn("Bearer prefix not found, header will be ignored");
        }
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("authenticated user " + email + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(req, res);
    }

    private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {
        // authenticates the user, so he can access the refresh token service
        // adds the claims in the request so that the controller has access
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                null, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        request.setAttribute("claims", ex.getClaims());

    }
}
