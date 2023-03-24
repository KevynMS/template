package com.flakkeeverhuizers.controller;

import com.flakkeeverhuizers.configuration.TokenProvider;;
import com.flakkeeverhuizers.controller.model.request.RelocationRequest;
import com.flakkeeverhuizers.controller.model.response.*;
import com.flakkeeverhuizers.controller.model.request.LoginRequest;
import com.flakkeeverhuizers.controller.model.request.UserRequest;
import com.flakkeeverhuizers.db.entity.User;
import com.flakkeeverhuizers.service.CSVImportService;
import com.flakkeeverhuizers.service.GeneralService;
import com.flakkeeverhuizers.service.UserService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@Tag(name = "Flakkee Verhuizers")
@RequestMapping(path = "/")
@Validated
@RequiredArgsConstructor
@Slf4j
public class Controller {

    private final UserService userService;

    private final CSVImportService csvService;

    private final AuthenticationManager authenticationManager;

    private final TokenProvider jwtTokenUtil;

    private final GeneralService generalService;

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

    @PostMapping(path = "user")
    public void save(@Valid @RequestBody UserRequest userRequest) {
        userService.save(userRequest);
    }

    @GetMapping(path = "user/{userID}")
    public User findByID(@PathVariable("userID") String userID) {
        return userService.findByID(userID);
    }


    @PostMapping(path = "import/inventory-data")
    public void uploadCategoryCSV(@RequestParam("file") MultipartFile file) {
        csvService.saveCategoryCSV(file);
    }

    @PostMapping(path = "import/spaces")
    public void uploadSpacesCSV(@RequestParam("file") MultipartFile file) {
        csvService.saveSpacesCSV(file);
    }


    @GetMapping(path = "item-categories")
    public ItemCategoriesResponse findAllCategories(@RequestParam("sort") Optional<String> sortOptional,
                                                    @RequestParam("order") Optional<String> orderOptional) {

        String sortedBy = sortOptional.orElse("id");
        String orderBy = orderOptional.orElse("");

        return csvService.findAllCategories(sortedBy, orderBy);
    }

    @GetMapping(path = "spaces")
    public SpacesResponse findAllSpaces(@RequestParam("sort") Optional<String> sortOptional,
                                        @RequestParam("order") Optional<String> orderOptional) {

        String sortedBy = sortOptional.orElse("id");
        String orderBy = orderOptional.orElse("");

        return csvService.findAllSpaces(sortedBy, orderBy);
    }

    @GetMapping(path = "address-types")
    public AddressTypesResponse findAllAddressTypes(@RequestParam("sort") Optional<String> sortOptional,
                                                    @RequestParam("order") Optional<String> orderOptional) {

        String sortedBy = sortOptional.orElse("id");
        String orderBy = orderOptional.orElse("");

        return generalService.findAllAddressTypes(sortedBy, orderBy);
    }

    @GetMapping(path = "house-types")
    public HouseTypesResponse findAllHouseTypes(@RequestParam("sort") Optional<String> sortOptional,
                                                @RequestParam("order") Optional<String> orderOptional) {

        String sortedBy = sortOptional.orElse("id");
        String orderBy = orderOptional.orElse("");

        return generalService.findAllHouseTypes(sortedBy, orderBy);
    }

    @GetMapping(path = "vias")
    public ViasResponse findAllVias(@RequestParam("sort") Optional<String> sortOptional,
                                    @RequestParam("order") Optional<String> orderOptional) {

        String sortedBy = sortOptional.orElse("id");
        String orderBy = orderOptional.orElse("");

        return generalService.findAllVias(sortedBy, orderBy);
    }

    @GetMapping(path = "boxes")
    public BoxesResponse findAllBoxes(@RequestParam("sort") Optional<String> sortOptional,
                                      @RequestParam("order") Optional<String> orderOptional) {

        String sortedBy = sortOptional.orElse("id");
        String orderBy = orderOptional.orElse("");

        return generalService.findAllBoxes(sortedBy, orderBy);
    }


    @GetMapping(path = "floors")
    public FloorsResponse findAllFloors(@RequestParam("sort") Optional<String> sortOptional,
                                      @RequestParam("order") Optional<String> orderOptional) {

        String sortedBy = sortOptional.orElse("id");
        String orderBy = orderOptional.orElse("");

        return generalService.findAllFloors(sortedBy, orderBy);
    }

    @GetMapping(path = "countries")
    public CountriesResponse findAllCountries(@RequestParam("sort") Optional<String> sortOptional,
                                        @RequestParam("order") Optional<String> orderOptional) {

        String sortedBy = sortOptional.orElse("id");
        String orderBy = orderOptional.orElse("");

        return generalService.findAllCountries(sortedBy, orderBy);
    }

    @GetMapping(path = "lifts")
    public LiftOptionsResponse findAllLiftOptions(@RequestParam("sort") Optional<String> sortOptional,
                                              @RequestParam("order") Optional<String> orderOptional) {

        String sortedBy = sortOptional.orElse("id");
        String orderBy = orderOptional.orElse("");

        return generalService.findAllLiftOptions(sortedBy, orderBy);
    }

    @GetMapping(path = "relocations/{id}")
    public RelocationsResponse findRelocation(@RequestParam("id") String id){
        return generalService.findRelocationsById(id);
    }

    @PostMapping(path = "relocations")
    public void createRelocation(@Valid @RequestBody RelocationRequest relocation) {
        generalService.saveRelocation(relocation);
    }



    private Map<String, Object> successLoginResponse(String email, String token) {
        User user = userService.findByEmail(email);

        Map<String, Object> response = new HashMap<>();
        response.put("userID", user.getId().toString());
        response.put("token", token);

        return response;

    }

}
