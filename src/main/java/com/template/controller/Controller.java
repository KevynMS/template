package com.template.controller;

import com.template.configuration.TokenProvider;;
import com.template.controller.model.request.LoginRequest;
import com.template.db.entity.User;
import com.template.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "template")
@RequestMapping(path = "/")
@Validated
@RequiredArgsConstructor
@Slf4j
public class Controller {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final TokenProvider jwtTokenUtil;

    @PostMapping(path = "login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication, null);
        return ResponseEntity.ok(successLoginResponse(loginRequest.getEmail(), token));
    }



    private Map<String, Object> successLoginResponse(String email, String token) {
        User user = userService.findByEmail(email);

        Map<String, Object> response = new HashMap<>();
        response.put("userID", user.getId().toString());
        response.put("token", token);

        return response;

    }

}
