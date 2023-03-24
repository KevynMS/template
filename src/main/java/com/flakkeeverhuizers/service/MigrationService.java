package com.flakkeeverhuizers.service;

import com.flakkeeverhuizers.db.entity.*;
import com.flakkeeverhuizers.db.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service(value = "migrationService")
public class MigrationService {

    private final AddressTypesRepository addressTypeRepository;

    private final HouseTypesRepository houseTypesRepository;

    private final ViasRepository viasRepository;

    private final BoxesRepository boxesRepository;

    private final FloorsRepository floorsRepository;

    private final CountriesRepository countriesRepository;

    private final LiftOptionsRepository liftOptionsRepository;


    public void saveAddressTypes() {
        addressTypeRepository.deleteAll();
        List<AddressTypes> addressTypeList = new ArrayList<>();
        addressTypeList.add(new AddressTypes(null, "Laden", "laden ", 99));
        addressTypeList.add(new AddressTypes(null, "Lossen", "lossen ", 99));
        addressTypeList.add(new AddressTypes(null, "Opslag", "opslag ", 99));
        addressTypeList.add(new AddressTypes(null, "Uitladen", "uitladen ", 99));
        addressTypeList.add(new AddressTypes(null, "Afvoer", "afvoer ", 99));
        addressTypeList.add(new AddressTypes(null, "Factuuradres", "factuuradres ", 99));
        addressTypeList.add(new AddressTypes(null, "Correspondentieadres", "correspondentieadres ", 99));
        addressTypeList.add(new AddressTypes(null, "Tijdelijke woning", "tijdelijke-woning ", 99));
        addressTypeList.add(new AddressTypes(null, "Laden/lossen", "laden-lossen ", 99));

        addressTypeRepository.saveAll(addressTypeList);
    }

    public void saveHouseTypes() {
        houseTypesRepository.deleteAll();
        List<HouseTypes> houseTypeList = new ArrayList<>();
        houseTypeList.add(new HouseTypes(null, "Eengezinswoning"));
        houseTypeList.add(new HouseTypes(null, "Drive-inwoning"));
        houseTypeList.add(new HouseTypes(null, "Bungalow"));
        houseTypeList.add(new HouseTypes(null, "Appartement"));
        houseTypeList.add(new HouseTypes(null, "Maisonnette"));
        houseTypeList.add(new HouseTypes(null, "Seniorenwoning"));
        houseTypeList.add(new HouseTypes(null, "Kantoor"));
        houseTypeList.add(new HouseTypes(null, "School"));
        houseTypeList.add(new HouseTypes(null, "Twee-onder-een-kapwoning"));
        houseTypeList.add(new HouseTypes(null, "Vrijstaand huis"));
        houseTypeList.add(new HouseTypes(null, "Splitlevelwoning"));
        houseTypeList.add(new HouseTypes(null, "Herenhuis"));
        houseTypeList.add(new HouseTypes(null, "Geschakelde woning"));
        houseTypeList.add(new HouseTypes(null, "Kamer in verzorgingshuis"));
        houseTypeList.add(new HouseTypes(null, "Hoekwoning"));
        houseTypeList.add(new HouseTypes(null, "Dijkwoning"));
        houseTypeList.add(new HouseTypes(null, "Semibungalow"));
        houseTypeList.add(new HouseTypes(null, "Boerderij"));
        houseTypeList.add(new HouseTypes(null, "Grachtenpand"));
        houseTypeList.add(new HouseTypes(null, "Garage"));
        houseTypeList.add(new HouseTypes(null, "Caravan"));
        houseTypeList.add(new HouseTypes(null, "Recreatiewoning"));
        houseTypeList.add(new HouseTypes(null, "Woonboot"));
        houseTypeList.add(new HouseTypes(null, "Opslag"));
        houseTypeList.add(new HouseTypes(null, "Loods"));
        houseTypeList.add(new HouseTypes(null, "Bedrijfsruimte"));
        houseTypeList.add(new HouseTypes(null, "Winkel"));
        houseTypeList.add(new HouseTypes(null, "Showroom"));

        houseTypesRepository.saveAll(houseTypeList);
    }

    public void saveVias(){
        viasRepository.deleteAll();
        List<Vias> viasList = new ArrayList<>();
        viasList.add(new Vias(null, "Advertentie"));
        viasList.add(new Vias(null, "Bekend"));
        viasList.add(new Vias(null, "CED"));
        viasList.add(new Vias(null, "Eerdere offerte aanvraag"));
        viasList.add(new Vias(null, "Erkende verhuizers website"));
        viasList.add(new Vias(null, "Euro 50,00 aktie"));
        viasList.add(new Vias(null, "Facebook"));
        viasList.add(new Vias(null, "Folder"));
        viasList.add(new Vias(null, "Gezien"));
        viasList.add(new Vias(null, "Gouden Gids"));
        viasList.add(new Vias(null, "Internet"));
        viasList.add(new Vias(null, "Mailing flyer"));
        viasList.add(new Vias(null, "Mailing sokken"));
        viasList.add(new Vias(null, "Mond op mond"));
        viasList.add(new Vias(null, "Telefoonboek"));
        viasList.add(new Vias(null, "Vaste klant"));
        viasList.add(new Vias(null, "Verhuisadvies"));
        viasList.add(new Vias(null, "Voucher Dörr"));
        viasList.add(new Vias(null, "Voucer Menno"));
        viasList.add(new Vias(null, "Vergelijk verhuizers"));
        viasList.add(new Vias(null, "Website"));

        viasList.add(new Vias(null, "Zelf benaderd"));

        viasRepository.saveAll(viasList);
    }

    public void saveBoxes(){
        boxesRepository.deleteAll();
        List<Boxes> boxesList = new ArrayList<>();
        boxesList.add(new Boxes(null, "moving-box", "Verhuisdoos", new BigDecimal("0.625"), false));
        boxesList.add(new Boxes(null, "wardrobe-box", "Garderobedoos", new BigDecimal("0.5"), false));

        boxesRepository.saveAll(boxesList);
    }

    public void saveFloors(){
        floorsRepository.deleteAll();
        List<Floors> floorsList = new ArrayList<>();
        floorsList.add(new Floors(null, "Kelder"));
        floorsList.add(new Floors(null, "Souterrain"));
        floorsList.add(new Floors(null, "Begane grond"));
        floorsList.add(new Floors(null, "Bel-etage"));
        floorsList.add(new Floors(null, "Entresol"));
        floorsList.add(new Floors(null, "Tussenverdieping"));
        floorsList.add(new Floors(null, "Vide"));
        floorsList.add(new Floors(null, "Zolder"));
        floorsList.add(new Floors(null, "Etage"));

        floorsRepository.saveAll(floorsList);
    }

    public void saveCountries(){
        countriesRepository.deleteAll();
        List<Countries> countriesList = new ArrayList<>();
        countriesList.add(new Countries(null, "Nederland", "NL", null));
        countriesList.add(new Countries(null, "Duitsland", "DE", null));
        countriesList.add(new Countries(null, "België", "BE", null));
        countriesList.add(new Countries(null, "Frankrijk", "FR", null));
        countriesList.add(new Countries(null, "Verenigd Koninkrijk", "GB", null));
        countriesList.add(new Countries(null, "Luxemburg", "LU", null));
        countriesList.add(new Countries(null, "Canada", "CA", null));

        countriesRepository.saveAll(countriesList);
    }

    public void saveLiftOptions(){
        liftOptionsRepository.deleteAll();
        List<LiftOptions> liftOptionsList = new ArrayList<>();
        liftOptionsList.add(new LiftOptions(null, "Lift 21 m", 1));
        liftOptionsList.add(new LiftOptions(null, "Lift 26 m", 2));
        liftOptionsList.add(new LiftOptions(null, "Lift 34 m", 3));
        liftOptionsList.add(new LiftOptions(null, "Klep XF 2.80 m", 4));
        liftOptionsList.add(new LiftOptions(null, "Klep XF 6.00 m", 5));
        liftOptionsList.add(new LiftOptions(null, "Geda", 6));

        liftOptionsRepository.saveAll(liftOptionsList);
    }

}
