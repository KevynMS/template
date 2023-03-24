package com.flakkeeverhuizers.controller.common;

import com.flakkeeverhuizers.controller.model.request.*;
import com.flakkeeverhuizers.db.entity.*;

import java.util.Set;
import java.util.stream.Collectors;

public class DTOToEntity {

    public static Relocations relocationRequestToRelocation(RelocationRequest relocationRequest){
        Relocations relocation = new Relocations();
        relocation.setViaId(viaRequestToVias(relocationRequest.getVia()));
        relocation.setQuotationId(quotationRequestToQuotations(relocationRequest.getQuotation()));
        relocation.setApplicantName(relocationRequest.getApplicantName());
        relocation.setPhone(relocationRequest.getPhone());
        relocation.setMobile(relocationRequest.getMobile());
        relocation.setExtraPhones(relocationRequest.getExtraPhones());
        relocation.setEmail(relocationRequest.getEmail());
        relocation.setExtraEmails(relocationRequest.getExtraEmails());
        relocation.setStorage(relocationRequest.getStorage());
        relocation.setRelocationDate(relocationRequest.getRelocationDate());
        relocation.setIban(relocationRequest.getIban());
        relocation.setBillingTo(relocationRequest.getBillingTo());
        relocation.setCreatedDt(relocationRequest.getCreatedDt());
        relocation.setAgepeImportXml(relocationRequest.getAgepeImportXml());
        relocation.setRelationNo(relocationRequest.getRelationNo());
        relocation.setUser(userRequestToUser(relocationRequest.getUser()));
        relocation.setExtraMobilePhones(relocationRequest.getExtraMobilePhones());
        relocation.setShortName(relocationRequest.getShortName());
        relocation.setRecordNo(relocationRequest.getRecordNo());
        relocation.setMovingTruckRate(relocationRequest.getMovingTruckRate());
        relocation.setSmall_movingTruckRate(relocationRequest.getSmall_movingTruckRate());
        relocation.setTrailerRate(relocationRequest.getTrailerRate());
        relocation.setMovingRate(relocationRequest.getMovingRate());
        relocation.setMovingElevatorRate(relocationRequest.getMovingElevatorRate());

        relocation.setAddresses(addressRequestToAddresses(relocationRequest.getAddresses()));
        relocation.setNotes(noteRequestToNotes(relocationRequest.getNotes()));
        relocation.setScenarios(scenarioRequestToScenarios(relocationRequest.getScenarios()));

        return relocation;
    }


    public static Vias viaRequestToVias(ViaRequest viaRequest){
        Vias vias = new Vias();
        vias.setMethod(viaRequest.getMethod());

        return vias;
    }


    public static Quotations quotationRequestToQuotations(QuotationRequest quotationRequest){
        Quotations quotations = new Quotations();
        quotations.setScenarios(scenarioRequestToScenarios(quotationRequest.getScenarios()));
        quotations.setTotalPrice(quotationRequest.getTotalPrice());
        quotations.setRelocation(relocationRequestToRelocation(quotationRequest.getRelocation()));
        quotations.setVatRate(vatRateRequestToVatRate(quotationRequest.getVatRate()));
        quotations.setTotalTravelTime(quotationRequest.getTotalTravelTime());
        quotations.setHourlyRate(quotationRequest.getHourlyRate());
        quotations.setInternHourlyrate(quotationRequest.getInternHourlyrate());
        quotations.setMovers(quotationRequest.getMovers());
        quotations.setInterns(quotationRequest.getInterns());
        quotations.setTrailerPrice(quotationRequest.getTrailerPrice());
        quotations.setTravelTimeFactor(quotationRequest.getTravelTimeFactor());
        quotations.setAssembleFactor(quotationRequest.getAssembleFactor());
        quotations.setHangupFactor(quotationRequest.getHangupFactor());
        quotations.setPackFactor(quotationRequest.getPackFactor());
        quotations.setLoadFactor(quotationRequest.getLoadFactor());

        return quotations;
    }


    public static User userRequestToUser(UserRequest userRequest){
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        return user;
    }

    public static Set<AddressComments> addressCommentRequestToAddressComment(Set<AddressCommentRequest> addressCommentRequest){
        return addressCommentRequest.stream().map(request -> addressCommentRequestToAddressComment(request)).collect(Collectors.toSet());
    }

    public static AddressComments addressCommentRequestToAddressComment(AddressCommentRequest addressCommentRequest){
        AddressComments addressComments = new AddressComments();
        addressComments.setAddress(addressRequestToAddresses(addressCommentRequest.getAddress()));
        addressComments.setContent(addressCommentRequest.getContent());
        addressComments.setCreatedDt(addressCommentRequest.getCreatedDt());
        addressComments.setUser(userRequestToUser(addressCommentRequest.getUser()));

        return addressComments;
    }

    public static Set<Addresses> addressRequestToAddresses(Set<AddressRequest> addressRequest){
        return addressRequest.stream().map(request -> addressRequestToAddresses(request)).collect(Collectors.toSet());
    }

    public static Addresses addressRequestToAddresses(AddressRequest addressRequest){
        Addresses addresses = new Addresses();
        addresses.setRelocation(relocationRequestToRelocation(addressRequest.getRelocation()));
        addresses.setAddressType(addressTypeRequestToAddressTypes(addressRequest.getAddressType()));
        addresses.setHouseType(houseTypeRequestToHouseTypes(addressRequest.getHouseType()));
        addresses.setStreet(addressRequest.getStreet());
        addresses.setHouseNumber(addressRequest.getHouseNumber());
        addresses.setZipcode(addressRequest.getZipcode());
        addresses.setCity(addressRequest.getCity());
        addresses.setMobilePlatformNeeded(addressRequest.getMobilePlatformNeeded());
        addresses.setMovingLift(addressRequest.getMovingLift());
        addresses.setWalkingDistance(addressRequest.getWalkingDistance());
        addresses.setIndoorElevator(addressRequest.getIndoorElevator());
        addresses.setAssemble(addressRequest.getAssemble());
        addresses.setExemption(addressRequest.getExemption());
        addresses.setExemptionHandledByCustomer(addressRequest.getExemptionHandledByCustomer());
        addresses.setRelocating(addressRequest.getRelocating());
        addresses.setSequence(addressRequest.getSequence());
        addresses.setFloorNumber(addressRequest.getFloorNumber());
        addresses.setLift(liftOptionRequestToLiftOptions(addressRequest.getLift()));
        addresses.setLocalAddress(addressRequest.getLocalAddress());
        addresses.setCountry(countryRequestToCountries(addressRequest.getCountry()));
        addresses.setAddressNo(addressRequest.getAddressNo());
        addresses.setPackingSpeed(addressRequest.getPackingSpeed());
        addresses.setUnpackingSpeed(addressRequest.getUnpackingSpeed());
        addresses.setLoadingFactor(addressRequest.getLoadingFactor());
        addresses.setUnloadingFactor(addressRequest.getUnloadingFactor());

        addresses.setAddressFloors(addressFloorRequestToAddressFloors(addressRequest.getAddressFloors()));
        addresses.setAddressComments(addressCommentRequestToAddressComment(addressRequest.getAddressComments()));

        return addresses;
    }

    public static Set<Notes> noteRequestToNotes(Set<NoteRequest> noteRequest){
        return noteRequest.stream().map(note -> noteRequestToNotes(note)).collect(Collectors.toSet());
    }

    public static Notes noteRequestToNotes(NoteRequest noteRequest){
        Notes notes = new Notes();
        notes.setRelocation(relocationRequestToRelocation(noteRequest.getRelocation()));
        notes.setUser(userRequestToUser(noteRequest.getUser()));
        notes.setContent(noteRequest.getContent());
        notes.setCreatedDt(noteRequest.getCreatedDt());

        return notes;
    }


    public static Set<Scenarios> scenarioRequestToScenarios(Set<ScenarioRequest> scenarioRequest){
        return scenarioRequest.stream().map(scenario -> scenarioRequestToScenarios(scenario)).collect(Collectors.toSet());
    }

    public static Scenarios scenarioRequestToScenarios(ScenarioRequest scenarioRequest){
        Scenarios scenarios = new Scenarios();
        scenarios.setQuotation(quotationRequestToQuotations(scenarioRequest.getQuotation()));
        scenarios.setName(scenarioRequest.getName());
        scenarios.setBoxesLoadedPerHour(scenarioRequest.getBoxesLoadedPerHour());
        scenarios.setBoxesUnloadedPerHour(scenarioRequest.getBoxesUnloadedPerHour());
        scenarios.setWarrantyVariable(scenarioRequest.getWarrantyVariable());
        scenarios.setWarrantyVariableAmount(scenarioRequest.getWarrantyVariableAmount());
        scenarios.setWarrantyVariableFactor(scenarioRequest.getWarrantyVariableFactor());
        scenarios.setTravelTimeAssemblyDisassembly(scenarioRequest.getTravelTimeAssemblyDisassembly());
        scenarios.setTravelTimeHangupPickup(scenarioRequest.getTravelTimeHangupPickup());
        scenarios.setTravelTimePackingUnpacking(scenarioRequest.getTravelTimePackingUnpacking());
        scenarios.setTravelTimeMoving(scenarioRequest.getTravelTimeMoving());
        scenarios.setTotalAmountMen(scenarioRequest.getTotalAmountMen());
        scenarios.setTotalAssembleTime(scenarioRequest.getTotalAssembleTime());
        scenarios.setTotalDisassembleTime(scenarioRequest.getTotalDisassembleTime());
        scenarios.setTotalElevatorTime(scenarioRequest.getTotalElevatorTime());
        scenarios.setTotalExtraWorkTime(scenarioRequest.getTotalExtraWorkTime());
        scenarios.setTotalHangupTime(scenarioRequest.getTotalHangupTime());
        scenarios.setTotalManHours(scenarioRequest.getTotalManHours());
        scenarios.setTotalManHours_each(scenarioRequest.getTotalManHours_each());
        scenarios.setTotalMovingTime(scenarioRequest.getTotalMovingTime());
        scenarios.setTotalMovingTrucks(scenarioRequest.getTotalMovingTrucks());
        scenarios.setTotalPackingTime(scenarioRequest.getTotalPackingTime());
        scenarios.setTotalPickupTime(scenarioRequest.getTotalPickupTime());
        scenarios.setTotalSmallMovingTrucks(scenarioRequest.getTotalSmallMovingTrucks());
        scenarios.setTotalTrailers(scenarioRequest.getTotalTrailers());
        scenarios.setTotalTravelTime(scenarioRequest.getTotalTravelTime());
        scenarios.setTotalUnpackingTime(scenarioRequest.getTotalUnpackingTime());
        scenarios.setTotalWalkingTime(scenarioRequest.getTotalWalkingTime());
        scenarios.setMovingDiscount(scenarioRequest.getMovingDiscount());
        scenarios.setPackingUnpackingDiscount(scenarioRequest.getPackingUnpackingDiscount());
        scenarios.setAssembleDisassembleDiscount(scenarioRequest.getAssembleDisassembleDiscount());
        scenarios.setHangupPickupDiscount(scenarioRequest.getHangupPickupDiscount());

        scenarios.setMovingDays(movingDayRequestToMovingDays(scenarioRequest.getMovingDays()));

        return scenarios;
    }

    public static VatRates vatRateRequestToVatRate(VatRateRequest vatRateRequest){
        VatRates vatRates = new VatRates();
        vatRates.setRate(vatRateRequest.getRate());

        return vatRates;
    }

    public static AddressTypes addressTypeRequestToAddressTypes(AddressTypeRequest addressTypeRequest){
        AddressTypes addressTypes = new AddressTypes();
        addressTypes.setCode(addressTypeRequest.getCode());
        addressTypes.setMethod(addressTypeRequest.getMethod());
        addressTypes.setSequence(addressTypeRequest.getSequence());

        return addressTypes;
    }


    public static Set<MovingActivities> movingActivityRequestToMovingActivities(Set<MovingActivityRequest> movingActivityRequest){
        return movingActivityRequest.stream().map(movingActivity -> movingActivityRequestToMovingActivities(movingActivity)).collect(Collectors.toSet());
    }

    public static MovingActivities movingActivityRequestToMovingActivities(MovingActivityRequest movingActivityRequest){
        MovingActivities movingActivities = new MovingActivities();
        movingActivities.setMovingDay(movingDayRequestToMovingDays(movingActivityRequest.getMovingDay()));
        movingActivities.setSequence(movingActivityRequest.getSequence());
        movingActivities.setAddress(addressRequestToAddresses(movingActivityRequest.getAddress()));
        movingActivities.setTravelTime(movingActivityRequest.getTravelTime());
        movingActivities.setPackingTime(movingActivityRequest.getPackingTime());
        movingActivities.setUnpackingTime(movingActivityRequest.getUnpackingTime());
        movingActivities.setWalkingTime(movingActivityRequest.getWalkingTime());
        movingActivities.setMovingTime(movingActivityRequest.getMovingTime());
        movingActivities.setElevatorTime(movingActivityRequest.getElevatorTime());
        movingActivities.setExtraWork(movingActivityRequest.getExtraWork());
        movingActivities.setIsLoading(movingActivityRequest.getIsLoading());
        movingActivities.setIsUnloading(movingActivityRequest.getIsUnloading());
        movingActivities.setType(movingActivityRequest.getType());
        movingActivities.setArrivalTime(movingActivityRequest.getArrivalTime());
        movingActivities.setDepartureTime(movingActivityRequest.getDepartureTime());
        movingActivities.setStartTime(movingActivityRequest.getStartTime());
        movingActivities.setBranchAddress(branchAddressesRequestToBranchAddresses(movingActivityRequest.getBranchAddress()));

        return movingActivities;
    }


    public static BranchAddresses branchAddressesRequestToBranchAddresses(BranchAddressRequest branchAddressRequest){
        BranchAddresses branchAddresses = new BranchAddresses();
        branchAddresses.setCountry(countryRequestToCountries(branchAddressRequest.getCountry()));
        branchAddresses.setStreet(branchAddressRequest.getStreet());
        branchAddresses.setHouseNumber(branchAddressRequest.getHouseNumber());
        branchAddresses.setZipcode(branchAddressRequest.getZipcode());
        branchAddresses.setCity(branchAddressRequest.getCity());
        branchAddresses.setAddressTypeCode(branchAddressRequest.getAddressTypeCode());

        return branchAddresses;
    }


    public static Countries countryRequestToCountries(CountryRequest countryRequest){
        Countries countries = new Countries();
        countries.setName(countryRequest.getName());
        countries.setCountryCode(countryRequest.getCountryCode());
        countries.setFlagIcon(countryRequest.getFlagIcon());

        return countries;
    }

    public static Set<MovingDays> movingDayRequestToMovingDays(Set<MovingDayRequest> movingDayRequest){
        return movingDayRequest.stream().map(movingDay -> movingDayRequestToMovingDays(movingDay)).collect(Collectors.toSet());
    }

    public static MovingDays movingDayRequestToMovingDays(MovingDayRequest movingDayRequest){
        MovingDays movingDays = new MovingDays();;
        movingDays.setScenario(scenarioRequestToScenarios(movingDayRequest.getScenario()));
        movingDays.setSequence(movingDayRequest.getSequence());
        movingDays.setName(movingDayRequest.getName());
        movingDays.setAmountMen(movingDayRequest.getAmountMen());
        movingDays.setManHours(movingDayRequest.getManHours());
        movingDays.setManHoursEach(movingDayRequest.getManHoursEach());
        movingDays.setMovingTrucks(movingDayRequest.getMovingTrucks());
        movingDays.setSmallMovingTrucks(movingDayRequest.getSmallMovingTrucks());
        movingDays.setTrailers(movingDayRequest.getTrailers());


        movingDays.setMovingActivities(movingActivityRequestToMovingActivities(movingDayRequest.getMovingActivities()));

        return movingDays;
    }

    public static HouseTypes houseTypeRequestToHouseTypes(HouseTypeRequest houseTypeRequest){
        HouseTypes houseTypes = new HouseTypes();
        houseTypes.setName(houseTypeRequest.getName());

        return houseTypes;
    }

    public static LiftOptions liftOptionRequestToLiftOptions(LiftOptionRequest liftOptionRequest){
        LiftOptions liftOptions = new LiftOptions();
        liftOptions.setName(liftOptionRequest.getName());
        liftOptions.setSequence(liftOptionRequest.getSequence());

        return liftOptions;
    }


    public static Set<AddressFloors> addressFloorRequestToAddressFloors(Set<AddressFloorRequest> addressFloorRequest){
        return addressFloorRequest.stream().map(addressFloor -> addressFloorRequestToAddressFloors(addressFloor)).collect(Collectors.toSet());
    }


    public static AddressFloors addressFloorRequestToAddressFloors(AddressFloorRequest addressFloorRequest){
        AddressFloors addressFloors = new AddressFloors();
        addressFloors.setAddress(addressRequestToAddresses(addressFloorRequest.getAddress()));
        addressFloors.setFloor(floorRequestToFloors(addressFloorRequest.getFloor()));
        addressFloors.setLabel(addressFloorRequest.getLabel());
        addressFloors.setNumber(addressFloorRequest.getNumber());
        addressFloors.setCreatedDt(addressFloorRequest.getCreatedDt());
        addressFloors.setRelocating(addressFloorRequest.getRelocating());
        addressFloors.setSequence(addressFloorRequest.getSequence());
        addressFloors.setAddressFloorSpaces(addressFloorSpaceRequestToAddressFloorSpace(addressFloorRequest.getAddressFloorSpaces()));

        return addressFloors;
    }

    public static Floors floorRequestToFloors(FloorRequest floorRequest){
        Floors floors = new Floors();
        floors.setName(floorRequest.getName());

        return floors;
    }


    public static Set<AddressFloorSpaces> addressFloorSpaceRequestToAddressFloorSpace(Set<AddressFloorSpaceRequest> addressFloorSpaceRequest){
        return addressFloorSpaceRequest.stream().map(addressFloorSpace -> addressFloorSpaceRequestToAddressFloorSpace(addressFloorSpace))
                .collect(Collectors.toSet());
    }

    public static AddressFloorSpaces addressFloorSpaceRequestToAddressFloorSpace(AddressFloorSpaceRequest addressFloorSpaceRequest){
        AddressFloorSpaces addressFloorSpaces = new AddressFloorSpaces();
        addressFloorSpaces.setAddressFloor(addressFloorRequestToAddressFloors(addressFloorSpaceRequest.getAddressFloor()));
        addressFloorSpaces.setSpace(spaceRequestToSpaces(addressFloorSpaceRequest.getSpace()));
        addressFloorSpaces.setLabel(addressFloorSpaceRequest.getLabel());
        addressFloorSpaces.setCreatedDt(addressFloorSpaceRequest.getCreatedDt());
        addressFloorSpaces.setRelocating(addressFloorSpaceRequest.getRelocating());
        addressFloorSpaces.setSequence(addressFloorSpaceRequest.getSequence());

        addressFloorSpaces.setAddressFloorSpaceItems(addressFloorSpacesRequestItemsToAddressFloorSpaceItem(addressFloorSpaceRequest.getAddressFloorSpaceItems()));
        addressFloorSpaces.setAddressFloorSpaceBoxes(addressFloorSpaceBoxRequestToAddressFloorSpaceBoxes(addressFloorSpaceRequest.getAddressFloorSpaceBoxes()));

        return addressFloorSpaces;
    }


    public static Set<AddressFloorSpaceBoxes> addressFloorSpaceBoxRequestToAddressFloorSpaceBoxes(Set<AddressFloorSpaceBoxRequest> AddressFloorSpaceBoxRequest){
        return AddressFloorSpaceBoxRequest.stream()
                .map(addressFloorSpaceBox -> addressFloorSpaceBoxRequestToAddressFloorSpaceBoxes(addressFloorSpaceBox)).collect(Collectors.toSet());
    }

    public static AddressFloorSpaceBoxes addressFloorSpaceBoxRequestToAddressFloorSpaceBoxes(AddressFloorSpaceBoxRequest addressFloorSpaceBoxRequest){
        AddressFloorSpaceBoxes addressFloorSpaceBoxes = new AddressFloorSpaceBoxes();
        addressFloorSpaceBoxes.setBox(boxRequestToBox(addressFloorSpaceBoxRequest.getBox()));
        addressFloorSpaceBoxes.setAddressFloorSpace(addressFloorSpaceRequestToAddressFloorSpace(addressFloorSpaceBoxRequest.getAddressFloorSpace()));
        addressFloorSpaceBoxes.setStorageAddress(addressRequestToAddresses(addressFloorSpaceBoxRequest.getStorageAddress()));
        addressFloorSpaceBoxes.setUnloadAddress(addressRequestToAddresses(addressFloorSpaceBoxRequest.getUnloadAddress()));
        addressFloorSpaceBoxes.setFragile(addressFloorSpaceBoxRequest.getFragile());
        addressFloorSpaceBoxes.setVolume(addressFloorSpaceBoxRequest.getVolume());
        addressFloorSpaceBoxes.setAmount(addressFloorSpaceBoxRequest.getAmount());
        addressFloorSpaceBoxes.setCreatedDt(addressFloorSpaceBoxRequest.getCreatedDt());
        addressFloorSpaceBoxes.
                setAddressFloorSpaceItem(addressFloorSpacesRequestItemsToAddressFloorSpaceItem(addressFloorSpaceBoxRequest.getAddressFloorSpaceItem()));
        addressFloorSpaceBoxes.setRelocating(addressFloorSpaceBoxRequest.getRelocating());
        addressFloorSpaceBoxes.setSequence(addressFloorSpaceBoxRequest.getSequence());

        return addressFloorSpaceBoxes;
    }

    public static Set<AddressFloorSpaceItems> addressFloorSpacesRequestItemsToAddressFloorSpaceItem(Set<AddressFloorSpaceItemRequest> addressFloorSpaceItemRequest){
        return addressFloorSpaceItemRequest.stream().
                map(addressFloorSpaceItem -> addressFloorSpacesRequestItemsToAddressFloorSpaceItem(addressFloorSpaceItem)).collect(Collectors.toSet());
    }

    public static AddressFloorSpaceItems addressFloorSpacesRequestItemsToAddressFloorSpaceItem(AddressFloorSpaceItemRequest addressFloorSpaceItemRequest){
        AddressFloorSpaceItems addressFloorSpaceItems = new AddressFloorSpaceItems();
        addressFloorSpaceItems.setAddressFloorSpace(addressFloorSpaceRequestToAddressFloorSpace(addressFloorSpaceItemRequest.getAddressFloorSpace()));
        //addressFloorSpaceItemResponse.setItem(addressFloorSpaceItems.getItem()); // todo ver depois
        addressFloorSpaceItems.setStorageAddress(addressRequestToAddresses(addressFloorSpaceItemRequest.getStorageAddress()));
        addressFloorSpaceItems.setUnloadAddress(addressRequestToAddresses(addressFloorSpaceItemRequest.getUnloadAddress()));
        addressFloorSpaceItems.setRelocating(addressFloorSpaceItemRequest.getRelocating());
        addressFloorSpaceItems.setVolume(addressFloorSpaceItemRequest.getVolume());
        addressFloorSpaceItems.setFragile(addressFloorSpaceItemRequest.getFragile());
        addressFloorSpaceItems.setAmount(addressFloorSpaceItemRequest.getAmount());
        addressFloorSpaceItems.setAssembleTime(addressFloorSpaceItemRequest.getAssembleTime());
        addressFloorSpaceItems.setAssemble(addressFloorSpaceItemRequest.getAssemble());
        addressFloorSpaceItems.setDisassemble(addressFloorSpaceItemRequest.getDisassemble());
        addressFloorSpaceItems.setNotes(addressFloorSpaceItemRequest.getNotes());
        addressFloorSpaceItems.setDisassembleTime(addressFloorSpaceItemRequest.getDisassembleTime());
        addressFloorSpaceItems.setPickUp(addressFloorSpaceItemRequest.getPickUp());
        addressFloorSpaceItems.setPickUpTime(addressFloorSpaceItemRequest.getPickUpTime());
        addressFloorSpaceItems.setHangUp(addressFloorSpaceItemRequest.getHangUp());
        addressFloorSpaceItems.setHangUpTime(addressFloorSpaceItemRequest.getHangUpTime());
        addressFloorSpaceItems.setCreatedDt(addressFloorSpaceItemRequest.getCreatedDt());
        addressFloorSpaceItems.setLabel(addressFloorSpaceItemRequest.getLabel());
        addressFloorSpaceItems.setSequence(addressFloorSpaceItemRequest.getSequence());

        addressFloorSpaceItems.setAddressFloorSpaceBoxes(
                addressFloorSpaceBoxRequestToAddressFloorSpaceBoxes(addressFloorSpaceItemRequest.getAddressFloorSpaceBoxes()));

        return addressFloorSpaceItems;
    }

    public static Spaces spaceRequestToSpaces(SpaceRequest spaceRequest){
        Spaces spaces = new Spaces();
        spaces.setName(spaceRequest.getName());
        spaces.setCustom(spaceRequest.getCustom());

        return spaces;
    }

    public static Boxes boxRequestToBox(BoxRequest boxRequest){
        Boxes boxes = new Boxes();
        boxes.setCode(boxRequest.getCode());
        boxes.setName(boxRequest.getName());
        boxes.setVolume(boxRequest.getVolume());
        boxes.setFragile(boxRequest.getFragile());

        return boxes;
    }
}
