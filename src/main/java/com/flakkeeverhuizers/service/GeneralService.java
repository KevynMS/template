package com.flakkeeverhuizers.service;

import com.flakkeeverhuizers.controller.model.request.RelocationRequest;
import com.flakkeeverhuizers.controller.model.response.*;
import com.flakkeeverhuizers.db.entity.LiftOptions;
import com.flakkeeverhuizers.db.entity.Relocations;
import com.flakkeeverhuizers.db.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.flakkeeverhuizers.controller.common.EntityToDTO.*;

@RequiredArgsConstructor
@Slf4j
@Service(value = "generalService")
public class GeneralService {

    private final AddressTypesRepository addressTypeRepository;

    private final HouseTypesRepository houseTypesRepository;

    private final BoxesRepository boxesRepository;

    private final ViasRepository viasRepository;

    private final FloorsRepository floorsRepository;

    private final CountriesRepository countriesRepository;

    private final LiftOptionsRepository liftOptionsRepository;

    private final ScenariosRepository scenariosRepository;

    private final AddressCommentsRepository addressCommentsRepository;

    private final AddressesRepository addressesRepository;

    private final AddressFloorsRepository addressFloorsRepository;

    private final AddressFloorSpacesRepository  addressFloorSpacesRepository;

    private final AddressFloorSpaceBoxesRepository addressFloorSpaceBoxesRepository;

    private final AddressFloorSpaceItemsRepository addressFloorSpaceItemsRepository;

    private final BranchAddressesRepository branchAddressesRepository;

    private final ExtendedUserRepository extendedUserRepository;

    private final MovingActivitiesRepository movingActivitiesRepository;

    private final MovingDaysRepository movingDaysRepository;

    private final NotesRepository notesRepository;

    private final QuotationsRepository quotationsRepository;

    private final RelocationsRepository relocationsRepository;

    private final RelocationLabelsRepository  relocationLabelsRepository;

    private final VatRatesRepository vatRatesRepository;

    private final SpacesRepository spacesRepository;

    private final ItemCategoriesRepository itemCategoriesRepository;

    public AddressTypesResponse findAllAddressTypes(String sortedBy, String orderBy) {
        AddressTypesResponse addressTypesResponse = new AddressTypesResponse();
        List<AddressTypeResponse> addressTypes = new ArrayList<>();

        addressTypeRepository.findAll(getSort(sortedBy, orderBy)).forEach(addressType -> {
            AddressTypeResponse addressTypeResponse = new AddressTypeResponse();
            addressTypeResponse.setId(addressType.getId().toString());
            addressTypeResponse.setAddressTypeId(addressType.getId().toString());
            addressTypeResponse.setMethod(addressType.getMethod());
            addressTypeResponse.setCode(addressType.getCode());
            addressTypeResponse.setSequence(addressType.getSequence());

            addressTypes.add(addressTypeResponse);
        });

        addressTypesResponse.setAddressTypes(addressTypes);
        return addressTypesResponse;
    }

    public HouseTypesResponse findAllHouseTypes(String sortedBy, String orderBy) {
        HouseTypesResponse houseTypesResponse = new HouseTypesResponse();
        List<HouseTypeResponse> houseTypes = new ArrayList<>();

        houseTypesRepository.findAll(getSort(sortedBy, orderBy)).forEach(houseType -> {
            HouseTypeResponse houseTypeResponse = new HouseTypeResponse();
            houseTypeResponse.setId(houseType.getId().toString());
            houseTypeResponse.setHouseTypeId(houseType.getId().toString());
            houseTypeResponse.setName(houseType.getName());

            houseTypes.add(houseTypeResponse);
        });

        houseTypesResponse.setHouseTypes(houseTypes);
        return houseTypesResponse;
    }

    public ViasResponse findAllVias(String sortedBy, String orderBy){
        ViasResponse viasResponse = new ViasResponse();
        List<ViaResponse> vias = new ArrayList<>();

        viasRepository.findAll(getSort(sortedBy, orderBy)).forEach(via -> {
            ViaResponse viaResponse = new ViaResponse();
            viaResponse.setId(via.getId().toString());
            viaResponse.setViaId(via.getId().toString());
            viaResponse.setMethod(via.getMethod());

            vias.add(viaResponse);
        });

        viasResponse.setVias(vias);
        return viasResponse;
    }

    public BoxesResponse findAllBoxes(String sortedBy, String orderBy){
        BoxesResponse boxesResponse = new BoxesResponse();
        List<BoxResponse> boxes = new ArrayList<>();

        boxesRepository.findAll(getSort(sortedBy, orderBy)).forEach(box ->{
            BoxResponse boxResponse = new BoxResponse();
            boxResponse.setId(box.getId().toString());
            boxResponse.setBoxId(box.getId().toString());
            boxResponse.setCode(box.getCode());
            boxResponse.setVolume(box.getVolume());
            boxResponse.setName(box.getName());
            boxResponse.setFragile(box.getFragile());

            boxes.add(boxResponse);
        });

        boxesResponse.setBoxes(boxes);
        return boxesResponse;
    }


    public FloorsResponse findAllFloors(String sortedBy, String orderBy){
        FloorsResponse floorsResponse = new FloorsResponse();
        List<FloorResponse> floors = new ArrayList<>();

        floorsRepository.findAll(getSort(sortedBy, orderBy)).forEach(floor -> {
            FloorResponse floorResponse = new FloorResponse();
            floorResponse.setId(floor.getId().toString());
            floorResponse.setFloorId(floor.getId().toString());
            floorResponse.setName(floor.getName());

            floors.add(floorResponse);
        });

        floorsResponse.setFloors(floors);
        return floorsResponse;
    }

    public CountriesResponse findAllCountries(String sortedBy, String orderBy){
        CountriesResponse countriesResponse = new CountriesResponse();
        List<CountryResponse> countries = new ArrayList<>();

        countriesRepository.findAll(getSort(sortedBy, orderBy)).forEach(country -> {
            CountryResponse countryResponse = new CountryResponse();
            countryResponse.setId(country.getId().toString());
            countryResponse.setCountryId(country.getId().toString());
            countryResponse.setCountryCode(country.getCountryCode());
            countryResponse.setName(country.getName());
            countryResponse.setFlagIcon(country.getFlagIcon());

            countries.add(countryResponse);
        });

        countriesResponse.setCountries(countries);
        return countriesResponse;
    }

    public LiftOptionsResponse findAllLiftOptions(String sortedBy, String orderBy){
        LiftOptionsResponse liftOptionsResponse = new LiftOptionsResponse();
        List<LiftOptionResponse> liftOptions = new ArrayList<>();

        liftOptionsRepository.findAll(getSort(sortedBy, orderBy)).forEach(liftOption -> {
            liftOptions.add(liftOptionsToLiftOptionsResponse(liftOption));
        });

        liftOptionsResponse.setLifts(liftOptions);
        return liftOptionsResponse;
    }

    public LiftOptionResponse  liftOptionsToLiftOptionsResponse(LiftOptions liftOption){
        LiftOptionResponse liftOptionResponse = new LiftOptionResponse();

        liftOptionResponse.setId(liftOption.getId().toString());
        liftOptionResponse.setLiftId(liftOption.getId().toString());
        liftOptionResponse.setName(liftOption.getName());
        liftOptionResponse.setSequence(liftOption.getSequence());

        return liftOptionResponse;
    }




    public AddressCommentsResponse findAllAddressComments(String sortedBy, String orderBy) {
        AddressCommentsResponse addressCommentsResponse = new AddressCommentsResponse();
        List<AddressCommentResponse> addressComments = new ArrayList<>();

        addressCommentsRepository.findAll(getSort(sortedBy, orderBy)).forEach(addressComment -> {
            AddressCommentResponse addressCommentResponse = addressCommentsToAddressCommentResponse(addressComment);

            addressComments.add(addressCommentResponse);
        });

        addressCommentsResponse.setAddressComments(addressComments);
        return addressCommentsResponse;
    }



    public AddressesResponse findAllAddresses(String sortedBy, String orderBy) {
        AddressesResponse addressesResponse = new AddressesResponse();
        List<AddressResponse> addresses = new ArrayList<>();

        addressesRepository.findAll(getSort(sortedBy, orderBy)).forEach(address -> {
            AddressResponse addressResponse = addressesToAddressResponse(address);

            addresses.add(addressResponse);
        });

        addressesResponse.setAddresses(addresses);
        return addressesResponse;
    }



    public AddressFloorsResponse findAllAddressFloors(String sortedBy, String orderBy) {
        AddressFloorsResponse addressFloorsResponse = new AddressFloorsResponse();
        List<AddressFloorResponse> addressFloors = new ArrayList<>();

        addressFloorsRepository.findAll(getSort(sortedBy, orderBy)).forEach(addressFloor -> {
            AddressFloorResponse addressFloorResponse = addressFloorsToAddressFloorResponse(addressFloor);

            addressFloors.add(addressFloorResponse);
        });

        addressFloorsResponse.setAddressFloors(addressFloors);
        return addressFloorsResponse;
    }



    public AddressFloorSpacesResponse findAllAddressFloorSpaces(String sortedBy, String orderBy) {
        AddressFloorSpacesResponse addressFloorSpacesResponse = new AddressFloorSpacesResponse();
        List<AddressFloorSpaceResponse> addressFloorSpaces = new ArrayList<>();

        addressFloorSpacesRepository.findAll(getSort(sortedBy, orderBy)).forEach(addressFloorSpace -> {
            AddressFloorSpaceResponse addressFloorSpaceResponse = addressFloorSpacesToAddressFloorSpaceResponse(addressFloorSpace);

            addressFloorSpaces.add(addressFloorSpaceResponse);
        });

        addressFloorSpacesResponse.setAddressFloorSpaces(addressFloorSpaces);
        return addressFloorSpacesResponse;
    }


    public VatRatesResponse findAllVatRates(String sortedBy, String orderBy) {
        VatRatesResponse vatRatesResponse = new VatRatesResponse();
        List<VatRateResponse> vatRates = new ArrayList<>();

        vatRatesRepository.findAll(getSort(sortedBy, orderBy)).forEach(vatRate -> {
            VatRateResponse vatRateResponse = vatRatesToVatRateResponse(vatRate);

            vatRates.add(vatRateResponse);
        });

        vatRatesResponse.setVatRates(vatRates);
        return vatRatesResponse;
    }



    public SpacesResponse findAllSpaces(String sortedBy, String orderBy) {
        SpacesResponse spacesResponse = new SpacesResponse();
        List<SpaceResponse> spaces = new ArrayList<>();

        spacesRepository.findAll(getSort(sortedBy, orderBy)).forEach(space -> {
            SpaceResponse spaceResponse = spacesToSpaceResponse(space);

            spaces.add(spaceResponse);
        });

        spacesResponse.setSpaces(spaces);
        return spacesResponse;
    }



    public AddressFloorSpaceBoxesResponse findAllAddressFloorSpaceBoxes(String sortedBy, String orderBy) {
        AddressFloorSpaceBoxesResponse addressFloorSpaceBoxesResponse = new AddressFloorSpaceBoxesResponse();
        List<AddressFloorSpaceBoxResponse> addressFloorSpaceBoxes = new ArrayList<>();

        addressFloorSpaceBoxesRepository.findAll(getSort(sortedBy, orderBy)).forEach(addressFloorSpaceBox -> {
            AddressFloorSpaceBoxResponse addressFloorSpaceBoxResponse = addressFloorSpaceBoxesToAddressFloorSpaceBoxResponse(addressFloorSpaceBox);

            addressFloorSpaceBoxes.add(addressFloorSpaceBoxResponse);
        });

        addressFloorSpaceBoxesResponse.setAddressFloorSpaceBoxes(addressFloorSpaceBoxes);
        return addressFloorSpaceBoxesResponse;
    }



    public AddressFloorSpaceItemsResponse findAllAddressFloorSpaceItems(String sortedBy, String orderBy) {
        AddressFloorSpaceItemsResponse addressFloorSpaceItemsResponse = new AddressFloorSpaceItemsResponse();
        List<AddressFloorSpaceItemResponse> addressFloorSpaceItems = new ArrayList<>();

        addressFloorSpaceItemsRepository.findAll(getSort(sortedBy, orderBy)).forEach(addressFloorSpaceItem -> {
            AddressFloorSpaceItemResponse addressFloorSpaceItemResponse = addressFloorSpaceItemsToAddressFloorSpaceItemResponse(addressFloorSpaceItem);

            addressFloorSpaceItems.add(addressFloorSpaceItemResponse);
        });

        addressFloorSpaceItemsResponse.setAddressFloorSpaceItems(addressFloorSpaceItems);
        return addressFloorSpaceItemsResponse;
    }



    public BranchAddressesResponse findAllBranchAddresses(String sortedBy, String orderBy) {
        BranchAddressesResponse branchAddressesResponse = new BranchAddressesResponse();
        List<BranchAddressResponse> branchAddresses = new ArrayList<>();

        branchAddressesRepository.findAll(getSort(sortedBy, orderBy)).forEach(branchAddress -> {
            BranchAddressResponse branchAddressResponse = branchAddressesToBranchAddressResponse(branchAddress);

            branchAddresses.add(branchAddressResponse);
        });

        branchAddressesResponse.setBranchAddresses(branchAddresses);
        return branchAddressesResponse;
    }



    public ExtendedUserResponse findAllExtendedUser(String sortedBy, String orderBy) {
        ExtendedUserResponse extendedUserResponse = new ExtendedUserResponse();
        List<ExtendedUseResponse> extendedUser = new ArrayList<>();

        extendedUserRepository.findAll(getSort(sortedBy, orderBy)).forEach(extendedUse -> {
            ExtendedUseResponse extendedUseResponse = extendedUserToExtendedUseResponse(extendedUse);

            extendedUser.add(extendedUseResponse);
        });

        extendedUserResponse.setExtendedUser(extendedUser);
        return extendedUserResponse;
    }



    public MovingActivitiesResponse findAllMovingActivities(String sortedBy, String orderBy) {
        MovingActivitiesResponse movingActivitiesResponse = new MovingActivitiesResponse();
        List<MovingActivityResponse> movingActivities = new ArrayList<>();

        movingActivitiesRepository.findAll(getSort(sortedBy, orderBy)).forEach(movingActivity -> {
            MovingActivityResponse movingActivityResponse = movingActivitiesToMovingActivityResponse(movingActivity);

            movingActivities.add(movingActivityResponse);
        });

        movingActivitiesResponse.setMovingActivities(movingActivities);
        return movingActivitiesResponse;
    }



    public MovingDaysResponse findAllMovingDays(String sortedBy, String orderBy) {
        MovingDaysResponse movingDaysResponse = new MovingDaysResponse();
        List<MovingDayResponse> movingDays = new ArrayList<>();

        movingDaysRepository.findAll(getSort(sortedBy, orderBy)).forEach(movingDay -> {
            MovingDayResponse movingDayResponse = movingDaysToMovingDayResponse(movingDay);

            movingDays.add(movingDayResponse);
        });

        movingDaysResponse.setMovingDays(movingDays);
        return movingDaysResponse;
    }



    public NotesResponse findAllNotes(String sortedBy, String orderBy) {
        NotesResponse notesResponse = new NotesResponse();
        List<NoteResponse> notes = new ArrayList<>();

        notesRepository.findAll(getSort(sortedBy, orderBy)).forEach(note -> {
            NoteResponse noteResponse = notesToNoteResponse(note);

            notes.add(noteResponse);
        });

        notesResponse.setNotes(notes);
        return notesResponse;
    }



    public QuotationsResponse findAllQuotations(String sortedBy, String orderBy) {
        QuotationsResponse quotationsResponse = new QuotationsResponse();
        List<QuotationResponse> quotations = new ArrayList<>();

        quotationsRepository.findAll(getSort(sortedBy, orderBy)).forEach(quotation -> {
            QuotationResponse quotationResponse = quotationsToQuotationResponse(quotation);

            quotations.add(quotationResponse);
        });

        quotationsResponse.setQuotations(quotations);
        return quotationsResponse;
    }




    public RelocationsResponse findAllRelocations(String sortedBy, String orderBy) {
        RelocationsResponse relocationsResponse = new RelocationsResponse();
        List<RelocationResponse> relocations = new ArrayList<>();

        relocationsRepository.findAll(getSort(sortedBy, orderBy)).forEach(relocation -> {
            RelocationResponse relocationResponse = relocationsToRelocationResponse(relocation);

            relocations.add(relocationResponse);
        });

        relocationsResponse.setRelocations(relocations);
        return relocationsResponse;
    }

    public RelocationsResponse findRelocationsById(String id) {
        RelocationsResponse relocationsResponse = new RelocationsResponse();
        List<RelocationResponse> relocations = new ArrayList<>();

        Relocations relocation = relocationsRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException(""));
        RelocationResponse relocationResponse = relocationsToRelocationResponse(relocation);

        relocations.add(relocationResponse);

        relocationsResponse.setRelocations(relocations);
        return relocationsResponse;
    }

    public void saveRelocation(RelocationRequest relocation){

    }

    public RelocationLabelsResponse findAllRelocationLabels(String sortedBy, String orderBy) {
        RelocationLabelsResponse relocationLabelsResponse = new RelocationLabelsResponse();
        List<RelocationLabelResponse> relocationLabels = new ArrayList<>();

        relocationLabelsRepository.findAll(getSort(sortedBy, orderBy)).forEach(relocationLabel -> {
            RelocationLabelResponse relocationLabelResponse = relocationLabelsToRelocationLabelResponse(relocationLabel);

            relocationLabels.add(relocationLabelResponse);
        });

        relocationLabelsResponse.setRelocationLabels(relocationLabels);
        return relocationLabelsResponse;
    }



    public ScenariosResponse findAllScenarios(String sortedBy, String orderBy) {
        ScenariosResponse scenariosResponse = new ScenariosResponse();
        List<ScenarioResponse> scenarios = new ArrayList<>();

        scenariosRepository.findAll(getSort(sortedBy, orderBy)).forEach(scenario -> {
            ScenarioResponse scenarioResponse = scenariosToScenarioResponse(scenario);

            scenarios.add(scenarioResponse);
        });

        scenariosResponse.setScenarios(scenarios);
        return scenariosResponse;
    }



    private Sort getSort(String sortedBy, String orderBy) {
        return orderBy.equals("desc")
                ? Sort.by(Sort.Direction.DESC, sortedBy)
                : Sort.by(Sort.Direction.ASC, sortedBy);
    }
}
