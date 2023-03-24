package com.flakkeeverhuizers.controller;

import com.flakkeeverhuizers.controller.model.request.UserRequest;
import com.flakkeeverhuizers.service.MigrationService;
import com.flakkeeverhuizers.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Tag(name = "Migration Flakkee Verhuizers")
@RequestMapping(path = "/")
@Validated
@RequiredArgsConstructor
@Slf4j
@Profile("migration")
public class MigrationController {

    private final MigrationService migrationService;

    @PostMapping(path = "address-types")
    public void saveAddressTypes() {
        migrationService.saveAddressTypes();
    }

    @PostMapping(path = "house-types")
    public void saveHouseTypes() {
        migrationService.saveHouseTypes();
    }

    @PostMapping(path = "vias")
    public void saveVias() {
        migrationService.saveVias();
    }

    @PostMapping(path = "boxes")
    public void saveBoxes() {
        migrationService.saveBoxes();
    }

    @PostMapping(path = "floors")
    public void saveFloors() {
        migrationService.saveFloors();
    }

    @PostMapping(path = "countries")
    public void saveCountries() {
        migrationService.saveCountries();
    }

    @PostMapping(path = "lifts")
    public void saveLiftOptions() {
        migrationService.saveLiftOptions();
    }

}
