package com.flakkeeverhuizers.controller.common;


import com.flakkeeverhuizers.controller.model.response.*;
import com.flakkeeverhuizers.db.entity.*;

import java.util.Set;
import java.util.stream.Collectors;

public class EntityToDTO{


	public static Set<AddressCommentResponse> addressCommentsToAddressCommentResponse(Set<AddressComments> addressComments){
		return addressComments.stream().map(addressComment -> addressCommentsToAddressCommentResponse(addressComment)).collect(Collectors.toSet());
	}

	public static AddressCommentResponse addressCommentsToAddressCommentResponse(AddressComments addressComments){
		AddressCommentResponse addressCommentResponse = new AddressCommentResponse();
		addressCommentResponse.setId(addressComments.getAddressComment().toString());
		addressCommentResponse.setAddress(addressesToAddressResponse(addressComments.getAddress()));
		addressCommentResponse.setContent(addressComments.getContent());
		addressCommentResponse.setCreatedDt(addressComments.getCreatedDt());
		addressCommentResponse.setUser(userToUserResponse(addressComments.getUser()));

		return addressCommentResponse;
	}

	public static Set<AddressResponse> addressesToAddressResponse(Set<Addresses> addresses){
		return addresses.stream().map(address -> addressesToAddressResponse(address)).collect(Collectors.toSet());
	}

	public static AddressResponse addressesToAddressResponse(Addresses addresses){
		AddressResponse addressResponse = new AddressResponse();
		addressResponse.setId(addresses.getAddress().toString());
		addressResponse.setRelocation(relocationsToRelocationResponse(addresses.getRelocation()));
		addressResponse.setAddressType(addressTypesToAddressTypeResponse(addresses.getAddressType()));
		addressResponse.setHouseType(houseTypesToHouseTypeResponse(addresses.getHouseType()));
		addressResponse.setStreet(addresses.getStreet());
		addressResponse.setHouseNumber(addresses.getHouseNumber());
		addressResponse.setZipcode(addresses.getZipcode());
		addressResponse.setCity(addresses.getCity());
		addressResponse.setMobilePlatformNeeded(addresses.getMobilePlatformNeeded());
		addressResponse.setMovingLift(addresses.getMovingLift());
		addressResponse.setWalkingDistance(addresses.getWalkingDistance());
		addressResponse.setIndoorElevator(addresses.getIndoorElevator());
		addressResponse.setAssemble(addresses.getAssemble());
		addressResponse.setExemption(addresses.getExemption());
		addressResponse.setExemptionHandledByCustomer(addresses.getExemptionHandledByCustomer());
		addressResponse.setRelocating(addresses.getRelocating());
		addressResponse.setSequence(addresses.getSequence());
		addressResponse.setFloorNumber(addresses.getFloorNumber());
		addressResponse.setLift(liftOptionsToLiftOptionResponse(addresses.getLift()));
		addressResponse.setLocalAddress(addresses.getLocalAddress());
		addressResponse.setCountry(countriesToCountryResponse(addresses.getCountry()));
		addressResponse.setAddressNo(addresses.getAddressNo());
		addressResponse.setPackingSpeed(addresses.getPackingSpeed());
		addressResponse.setUnpackingSpeed(addresses.getUnpackingSpeed());
		addressResponse.setLoadingFactor(addresses.getLoadingFactor());
		addressResponse.setUnloadingFactor(addresses.getUnloadingFactor());

		addressResponse.setAddressFloors(addressFloorsToAddressFloorResponse(addresses.getAddressFloors()));
		addressResponse.setAddressComments(addressCommentsToAddressCommentResponse(addresses.getAddressComments()));

		return addressResponse;
	}


	public static Set<AddressFloorResponse> addressFloorsToAddressFloorResponse(Set<AddressFloors> addressFloors){
		return addressFloors.stream().map(addressFloor -> addressFloorsToAddressFloorResponse(addressFloor)).collect(Collectors.toSet());
	}


	public static AddressFloorResponse addressFloorsToAddressFloorResponse(AddressFloors addressFloors){
		AddressFloorResponse addressFloorResponse = new AddressFloorResponse();
		addressFloorResponse.setId(addressFloors.getAddressFloor().toString());
		addressFloorResponse.setAddress(addressesToAddressResponse(addressFloors.getAddress()));
		addressFloorResponse.setFloor(floorsToFloorResponse(addressFloors.getFloor()));
		addressFloorResponse.setLabel(addressFloors.getLabel());
		addressFloorResponse.setNumber(addressFloors.getNumber());
		addressFloorResponse.setCreatedDt(addressFloors.getCreatedDt());
		addressFloorResponse.setRelocating(addressFloors.getRelocating());
		addressFloorResponse.setSequence(addressFloors.getSequence());
		addressFloorResponse.setAddressFloorSpaces(addressFloorSpacesToAddressFloorSpaceResponse(addressFloors.getAddressFloorSpaces()));

		return addressFloorResponse;
	}

	public static Set<AddressFloorSpaceResponse> addressFloorSpacesToAddressFloorSpaceResponse(Set<AddressFloorSpaces> addressFloorSpaces){
		return addressFloorSpaces.stream().map(addressFloorSpace -> addressFloorSpacesToAddressFloorSpaceResponse(addressFloorSpace))
				.collect(Collectors.toSet());
	}

	public static AddressFloorSpaceResponse addressFloorSpacesToAddressFloorSpaceResponse(AddressFloorSpaces addressFloorSpaces){
		AddressFloorSpaceResponse addressFloorSpaceResponse = new AddressFloorSpaceResponse();
		addressFloorSpaceResponse.setId(addressFloorSpaces.getAddressFloorSpace().toString());
		addressFloorSpaceResponse.setAddressFloor(addressFloorsToAddressFloorResponse(addressFloorSpaces.getAddressFloor()));
		addressFloorSpaceResponse.setSpace(spacesToSpaceResponse(addressFloorSpaces.getSpace()));
		addressFloorSpaceResponse.setLabel(addressFloorSpaces.getLabel());
		addressFloorSpaceResponse.setCreatedDt(addressFloorSpaces.getCreatedDt());
		addressFloorSpaceResponse.setRelocating(addressFloorSpaces.getRelocating());
		addressFloorSpaceResponse.setSequence(addressFloorSpaces.getSequence());

		addressFloorSpaceResponse.setAddressFloorSpaceItems(addressFloorSpaceItemsToAddressFloorSpaceItemResponse(addressFloorSpaces.getAddressFloorSpaceItems()));
		addressFloorSpaceResponse.setAddressFloorSpaceBoxes(addressFloorSpaceBoxesToAddressFloorSpaceBoxResponse(addressFloorSpaces.getAddressFloorSpaceBoxes()));

		return addressFloorSpaceResponse;
	}

	public static AddressTypeResponse addressTypesToAddressTypeResponse(AddressTypes addressTypes){
		AddressTypeResponse addressTypeResponse = new AddressTypeResponse();
		addressTypeResponse.setId(addressTypes.getId().toString());
		addressTypeResponse.setCode(addressTypes.getCode());
		addressTypeResponse.setMethod(addressTypes.getMethod());
		addressTypeResponse.setSequence(addressTypes.getSequence());

		return addressTypeResponse;
	}

	public static BoxResponse boxesToBoxResponse(Boxes boxes){
		BoxResponse boxResponse = new BoxResponse();
		boxResponse.setId(boxes.getId().toString());
		boxResponse.setCode(boxes.getCode());
		boxResponse.setName(boxes.getName());
		boxResponse.setVolume(boxes.getVolume());
		boxResponse.setFragile(boxes.getFragile());

		return boxResponse;
	}

	public static CountryResponse countriesToCountryResponse(Countries countries){
		CountryResponse countryResponse = new CountryResponse();
		countryResponse.setId(countries.getId().toString());
		countryResponse.setName(countries.getName());
		countryResponse.setCountryCode(countries.getCountryCode());
		countryResponse.setFlagIcon(countries.getFlagIcon());

		return countryResponse;
	}

	public static FloorResponse floorsToFloorResponse(Floors floors){
		FloorResponse floorResponse = new FloorResponse();
		floorResponse.setId(floors.getId().toString());
		floorResponse.setName(floors.getName());

		return floorResponse;
	}

	public static HouseTypeResponse houseTypesToHouseTypeResponse(HouseTypes houseTypes){
		HouseTypeResponse houseTypeResponse = new HouseTypeResponse();
		houseTypeResponse.setId(houseTypes.getId().toString());
		houseTypeResponse.setName(houseTypes.getName());

		return houseTypeResponse;
	}

	public static ItemCategoryResponse itemCategoriesToItemCategoryResponse(ItemCategories itemCategories){
		ItemCategoryResponse itemCategoryResponse = new ItemCategoryResponse();
		itemCategoryResponse.setId(itemCategories.getId().toString());
		itemCategoryResponse.setName(itemCategories.getName());
		itemCategoryResponse.setIcon(itemCategories.getIcon());
		itemCategoryResponse.setCustom(itemCategories.getCustom());

		return itemCategoryResponse;
	}

	public static LiftOptionResponse liftOptionsToLiftOptionResponse(LiftOptions liftOptions){
		LiftOptionResponse liftOptionResponse = new LiftOptionResponse();
		liftOptionResponse.setId(liftOptions.getId().toString());
		liftOptionResponse.setName(liftOptions.getName());
		liftOptionResponse.setSequence(liftOptions.getSequence());

		return liftOptionResponse;
	}

	public static ViaResponse viasToViaResponse(Vias vias){
		ViaResponse viaResponse = new ViaResponse();
		viaResponse.setId(vias.getId().toString());
		viaResponse.setMethod(vias.getMethod());

		return viaResponse;
	}

	public static VatRateResponse vatRatesToVatRateResponse(VatRates vatRates){
		VatRateResponse vatRateResponse = new VatRateResponse();
		vatRateResponse.setId(vatRates.getId().toString());
		vatRateResponse.setRate(vatRates.getRate());

		return vatRateResponse;
	}

	public static SpaceResponse spacesToSpaceResponse(Spaces spaces){
		SpaceResponse spaceResponse = new SpaceResponse();
		spaceResponse.setId(spaces.getId().toString());
		spaceResponse.setName(spaces.getName());
		spaceResponse.setCustom(spaces.getCustom());

		return spaceResponse;
	}

	public static Set<AddressFloorSpaceBoxResponse> addressFloorSpaceBoxesToAddressFloorSpaceBoxResponse(Set<AddressFloorSpaceBoxes> addressFloorSpaceBoxes){
		return addressFloorSpaceBoxes.stream()
				.map(addressFloorSpaceBox -> addressFloorSpaceBoxesToAddressFloorSpaceBoxResponse(addressFloorSpaceBox)).collect(Collectors.toSet());
	}

	public static AddressFloorSpaceBoxResponse addressFloorSpaceBoxesToAddressFloorSpaceBoxResponse(AddressFloorSpaceBoxes addressFloorSpaceBoxes){
		AddressFloorSpaceBoxResponse addressFloorSpaceBoxResponse = new AddressFloorSpaceBoxResponse();
		addressFloorSpaceBoxResponse.setId(addressFloorSpaceBoxes.getId().toString());
		addressFloorSpaceBoxResponse.setBox(boxesToBoxResponse(addressFloorSpaceBoxes.getBox()));
		addressFloorSpaceBoxResponse.setAddressFloorSpace(addressFloorSpacesToAddressFloorSpaceResponse(addressFloorSpaceBoxes.getAddressFloorSpace()));
		addressFloorSpaceBoxResponse.setStorageAddress(addressesToAddressResponse(addressFloorSpaceBoxes.getStorageAddress()));
		addressFloorSpaceBoxResponse.setUnloadAddress(addressesToAddressResponse(addressFloorSpaceBoxes.getUnloadAddress()));
		addressFloorSpaceBoxResponse.setFragile(addressFloorSpaceBoxes.getFragile());
		addressFloorSpaceBoxResponse.setVolume(addressFloorSpaceBoxes.getVolume());
		addressFloorSpaceBoxResponse.setAmount(addressFloorSpaceBoxes.getAmount());
		addressFloorSpaceBoxResponse.setCreatedDt(addressFloorSpaceBoxes.getCreatedDt());
		addressFloorSpaceBoxResponse.
				setAddressFloorSpaceItem(addressFloorSpaceItemsToAddressFloorSpaceItemResponse(addressFloorSpaceBoxes.getAddressFloorSpaceItem()));
		addressFloorSpaceBoxResponse.setRelocating(addressFloorSpaceBoxes.getRelocating());
		addressFloorSpaceBoxResponse.setSequence(addressFloorSpaceBoxes.getSequence());

		return addressFloorSpaceBoxResponse;
	}

	public static Set<AddressFloorSpaceItemResponse> addressFloorSpaceItemsToAddressFloorSpaceItemResponse(Set<AddressFloorSpaceItems> addressFloorSpaceItems){
		return addressFloorSpaceItems.stream().
				map(addressFloorSpaceItem -> addressFloorSpaceItemsToAddressFloorSpaceItemResponse(addressFloorSpaceItem)).collect(Collectors.toSet());
	}

	public static AddressFloorSpaceItemResponse addressFloorSpaceItemsToAddressFloorSpaceItemResponse(AddressFloorSpaceItems addressFloorSpaceItems){
		AddressFloorSpaceItemResponse addressFloorSpaceItemResponse = new AddressFloorSpaceItemResponse();
		addressFloorSpaceItemResponse.setId(addressFloorSpaceItems.getAddressFloorSpaceItem().toString());
		addressFloorSpaceItemResponse.setAddressFloorSpace(addressFloorSpacesToAddressFloorSpaceResponse(addressFloorSpaceItems.getAddressFloorSpace()));
		//addressFloorSpaceItemResponse.setItem(addressFloorSpaceItems.getItem()); // todo ver depois
		addressFloorSpaceItemResponse.setStorageAddress(addressesToAddressResponse(addressFloorSpaceItems.getStorageAddress()));
		addressFloorSpaceItemResponse.setUnloadAddress(addressesToAddressResponse(addressFloorSpaceItems.getUnloadAddress()));
		addressFloorSpaceItemResponse.setRelocating(addressFloorSpaceItems.getRelocating());
		addressFloorSpaceItemResponse.setVolume(addressFloorSpaceItems.getVolume());
		addressFloorSpaceItemResponse.setFragile(addressFloorSpaceItems.getFragile());
		addressFloorSpaceItemResponse.setAmount(addressFloorSpaceItems.getAmount());
		addressFloorSpaceItemResponse.setAssembleTime(addressFloorSpaceItems.getAssembleTime());
		addressFloorSpaceItemResponse.setAssemble(addressFloorSpaceItems.getAssemble());
		addressFloorSpaceItemResponse.setDisassemble(addressFloorSpaceItems.getDisassemble());
		addressFloorSpaceItemResponse.setNotes(addressFloorSpaceItems.getNotes());
		addressFloorSpaceItemResponse.setDisassembleTime(addressFloorSpaceItems.getDisassembleTime());
		addressFloorSpaceItemResponse.setPickUp(addressFloorSpaceItems.getPickUp());
		addressFloorSpaceItemResponse.setPickUpTime(addressFloorSpaceItems.getPickUpTime());
		addressFloorSpaceItemResponse.setHangUp(addressFloorSpaceItems.getHangUp());
		addressFloorSpaceItemResponse.setHangUpTime(addressFloorSpaceItems.getHangUpTime());
		addressFloorSpaceItemResponse.setCreatedDt(addressFloorSpaceItems.getCreatedDt());
		addressFloorSpaceItemResponse.setLabel(addressFloorSpaceItems.getLabel());
		addressFloorSpaceItemResponse.setSequence(addressFloorSpaceItems.getSequence());

		addressFloorSpaceItemResponse.setAddressFloorSpaceBoxes(
				addressFloorSpaceBoxesToAddressFloorSpaceBoxResponse(addressFloorSpaceItems.getAddressFloorSpaceBoxes()));

		return addressFloorSpaceItemResponse;
	}

	public static BranchAddressResponse branchAddressesToBranchAddressResponse(BranchAddresses branchAddresses){
		BranchAddressResponse branchAddressResponse = new BranchAddressResponse();
		branchAddressResponse.setId(branchAddresses.getBranchAddress());
		branchAddressResponse.setCountry(countriesToCountryResponse(branchAddresses.getCountry()));
		branchAddressResponse.setStreet(branchAddresses.getStreet());
		branchAddressResponse.setHouseNumber(branchAddresses.getHouseNumber());
		branchAddressResponse.setZipcode(branchAddresses.getZipcode());
		branchAddressResponse.setCity(branchAddresses.getCity());
		branchAddressResponse.setAddressTypeCode(branchAddresses.getAddressTypeCode());

		return branchAddressResponse;
	}

	public static ExtendedUseResponse extendedUserToExtendedUseResponse(ExtendedUser extendedUser){
		ExtendedUseResponse extendedUseResponse = new ExtendedUseResponse();
		extendedUseResponse.setId(extendedUser.getId().toString());
		extendedUseResponse.setAgepeId(extendedUser.getAgepeId());

		return extendedUseResponse;
	}

	public static Set<MovingActivityResponse> movingActivitiesToMovingActivityResponse(Set<MovingActivities> movingActivities){
		return movingActivities.stream().map(movingActivity -> movingActivitiesToMovingActivityResponse(movingActivity)).collect(Collectors.toSet());
	}

	public static MovingActivityResponse movingActivitiesToMovingActivityResponse(MovingActivities movingActivities){
		MovingActivityResponse movingActivityResponse = new MovingActivityResponse();
		movingActivityResponse.setId(movingActivities.getMovingActivity().toString());
		movingActivityResponse.setMovingDay(movingDaysToMovingDayResponse(movingActivities.getMovingDay()));
		movingActivityResponse.setSequence(movingActivities.getSequence());
		movingActivityResponse.setAddress(addressesToAddressResponse(movingActivities.getAddress()));
		movingActivityResponse.setTravelTime(movingActivities.getTravelTime());
		movingActivityResponse.setPackingTime(movingActivities.getPackingTime());
		movingActivityResponse.setUnpackingTime(movingActivities.getUnpackingTime());
		movingActivityResponse.setWalkingTime(movingActivities.getWalkingTime());
		movingActivityResponse.setMovingTime(movingActivities.getMovingTime());
		movingActivityResponse.setElevatorTime(movingActivities.getElevatorTime());
		movingActivityResponse.setExtraWork(movingActivities.getExtraWork());
		movingActivityResponse.setIsLoading(movingActivities.getIsLoading());
		movingActivityResponse.setIsUnloading(movingActivities.getIsUnloading());
		movingActivityResponse.setType(movingActivities.getType());
		movingActivityResponse.setArrivalTime(movingActivities.getArrivalTime());
		movingActivityResponse.setDepartureTime(movingActivities.getDepartureTime());
		movingActivityResponse.setStartTime(movingActivities.getStartTime());
		movingActivityResponse.setBranchAddress(branchAddressesToBranchAddressResponse(movingActivities.getBranchAddress()));

		return movingActivityResponse;
	}

	public static Set<MovingDayResponse> movingDaysToMovingDayResponse(Set<MovingDays> movingDays){
		return movingDays.stream().map(movingDay -> movingDaysToMovingDayResponse(movingDay)).collect(Collectors.toSet());
	}

	public static MovingDayResponse movingDaysToMovingDayResponse(MovingDays movingDays){
		MovingDayResponse movingDayResponse = new MovingDayResponse();
		movingDayResponse.setId(movingDays.getMovingDay().toString());
		movingDayResponse.setScenario(scenariosToScenarioResponse(movingDays.getScenario()));
		movingDayResponse.setSequence(movingDays.getSequence());
		movingDayResponse.setName(movingDays.getName());
		movingDayResponse.setAmountMen(movingDays.getAmountMen());
		movingDayResponse.setManHours(movingDays.getManHours());
		movingDayResponse.setManHoursEach(movingDays.getManHoursEach());
		movingDayResponse.setMovingTrucks(movingDays.getMovingTrucks());
		movingDayResponse.setSmallMovingTrucks(movingDays.getSmallMovingTrucks());
		movingDayResponse.setTrailers(movingDays.getTrailers());


		movingDayResponse.setMovingActivities(movingActivitiesToMovingActivityResponse(movingDays.getMovingActivities()));

		return movingDayResponse;
	}

	public static Set<NoteResponse> notesToNoteResponse(Set<Notes> notes){
		return notes.stream().map(note -> notesToNoteResponse(note)).collect(Collectors.toSet());
	}

	public static NoteResponse notesToNoteResponse(Notes notes){
		NoteResponse noteResponse = new NoteResponse();
		noteResponse.setId(notes.getNote().toString());
		noteResponse.setRelocation(relocationsToRelocationResponse(notes.getRelocation()));
		noteResponse.setUser(userToUserResponse(notes.getUser()));
		noteResponse.setContent(notes.getContent());
		noteResponse.setCreatedDt(notes.getCreatedDt());

		return noteResponse;
	}

	public static QuotationResponse quotationsToQuotationResponse(Quotations quotations){
		QuotationResponse quotationResponse = new QuotationResponse();
		quotationResponse.setId(quotations.getId().toString());
		quotationResponse.setScenarios(scenariosToScenarioResponse(quotations.getScenarios()));
		quotationResponse.setTotalPrice(quotations.getTotalPrice());
		quotationResponse.setRelocation(relocationsToRelocationResponse(quotations.getRelocation()));
		quotationResponse.setVatRate(vatRatesToVatRateResponse(quotations.getVatRate()));
		quotationResponse.setTotalTravelTime(quotations.getTotalTravelTime());
		quotationResponse.setHourlyRate(quotations.getHourlyRate());
		quotationResponse.setInternHourlyrate(quotations.getInternHourlyrate());
		quotationResponse.setMovers(quotations.getMovers());
		quotationResponse.setInterns(quotations.getInterns());
		quotationResponse.setTrailerPrice(quotations.getTrailerPrice());
		quotationResponse.setTravelTimeFactor(quotations.getTravelTimeFactor());
		quotationResponse.setAssembleFactor(quotations.getAssembleFactor());
		quotationResponse.setHangupFactor(quotations.getHangupFactor());
		quotationResponse.setPackFactor(quotations.getPackFactor());
		quotationResponse.setLoadFactor(quotations.getLoadFactor());

		return quotationResponse;
	}

	public static RelocationResponse relocationsToRelocationResponse(Relocations relocations){
		RelocationResponse relocationResponse = new RelocationResponse();
		relocationResponse.setId(relocations.getId().toString());
		relocationResponse.setViaId(viasToViaResponse(relocations.getViaId()));
		relocationResponse.setQuotationId(quotationsToQuotationResponse(relocations.getQuotationId()));
		relocationResponse.setApplicantName(relocations.getApplicantName());
		relocationResponse.setPhone(relocations.getPhone());
		relocationResponse.setMobile(relocations.getMobile());
		relocationResponse.setExtraPhones(relocations.getExtraPhones());
		relocationResponse.setEmail(relocations.getEmail());
		relocationResponse.setExtraEmails(relocations.getExtraEmails());
		relocationResponse.setStorage(relocations.getStorage());
		relocationResponse.setRelocationDate(relocations.getRelocationDate());
		relocationResponse.setIban(relocations.getIban());
		relocationResponse.setBillingTo(relocations.getBillingTo());
		relocationResponse.setCreatedDt(relocations.getCreatedDt());
		relocationResponse.setAgepeImportXml(relocations.getAgepeImportXml());
		relocationResponse.setRelationNo(relocations.getRelationNo());
		relocationResponse.setUser(userToUserResponse(relocations.getUser()));
		relocationResponse.setExtraMobilePhones(relocations.getExtraMobilePhones());
		relocationResponse.setShortName(relocations.getShortName());
		relocationResponse.setRecordNo(relocations.getRecordNo());
		relocationResponse.setMovingTruckRate(relocations.getMovingTruckRate());
		relocationResponse.setSmall_movingTruckRate(relocations.getSmall_movingTruckRate());
		relocationResponse.setTrailerRate(relocations.getTrailerRate());
		relocationResponse.setMovingRate(relocations.getMovingRate());
		relocationResponse.setMovingElevatorRate(relocations.getMovingElevatorRate());

		relocationResponse.setAddresses(addressesToAddressResponse(relocations.getAddresses()));
		relocationResponse.setNotes(notesToNoteResponse(relocations.getNotes()));
		relocationResponse.setScenarios(scenariosToScenarioResponse(relocations.getScenarios()));

		return relocationResponse;
	}

	public static RelocationLabelResponse relocationLabelsToRelocationLabelResponse(RelocationLabels relocationLabels){
		RelocationLabelResponse relocationLabelResponse = new RelocationLabelResponse();
		//relocationLabelResponse.setId(relocationLabels.getId()); // todo ver depois

		return relocationLabelResponse;
	}

	public static Set<ScenarioResponse> scenariosToScenarioResponse(Set<Scenarios> scenarios){
		return scenarios.stream().map(scenario -> scenariosToScenarioResponse(scenario)).collect(Collectors.toSet());
	}

	public static ScenarioResponse scenariosToScenarioResponse(Scenarios scenarios){
		ScenarioResponse scenarioResponse = new ScenarioResponse();
		scenarioResponse.setId(scenarios.getId().toString());
		scenarioResponse.setQuotation(quotationsToQuotationResponse(scenarios.getQuotation()));
		scenarioResponse.setName(scenarios.getName());
		scenarioResponse.setBoxesLoadedPerHour(scenarios.getBoxesLoadedPerHour());
		scenarioResponse.setBoxesUnloadedPerHour(scenarios.getBoxesUnloadedPerHour());
		scenarioResponse.setWarrantyVariable(scenarios.getWarrantyVariable());
		scenarioResponse.setWarrantyVariableAmount(scenarios.getWarrantyVariableAmount());
		scenarioResponse.setWarrantyVariableFactor(scenarios.getWarrantyVariableFactor());
		scenarioResponse.setTravelTimeAssemblyDisassembly(scenarios.getTravelTimeAssemblyDisassembly());
		scenarioResponse.setTravelTimeHangupPickup(scenarios.getTravelTimeHangupPickup());
		scenarioResponse.setTravelTimePackingUnpacking(scenarios.getTravelTimePackingUnpacking());
		scenarioResponse.setTravelTimeMoving(scenarios.getTravelTimeMoving());
		scenarioResponse.setTotalAmountMen(scenarios.getTotalAmountMen());
		scenarioResponse.setTotalAssembleTime(scenarios.getTotalAssembleTime());
		scenarioResponse.setTotalDisassembleTime(scenarios.getTotalDisassembleTime());
		scenarioResponse.setTotalElevatorTime(scenarios.getTotalElevatorTime());
		scenarioResponse.setTotalExtraWorkTime(scenarios.getTotalExtraWorkTime());
		scenarioResponse.setTotalHangupTime(scenarios.getTotalHangupTime());
		scenarioResponse.setTotalManHours(scenarios.getTotalManHours());
		scenarioResponse.setTotalManHours_each(scenarios.getTotalManHours_each());
		scenarioResponse.setTotalMovingTime(scenarios.getTotalMovingTime());
		scenarioResponse.setTotalMovingTrucks(scenarios.getTotalMovingTrucks());
		scenarioResponse.setTotalPackingTime(scenarios.getTotalPackingTime());
		scenarioResponse.setTotalPickupTime(scenarios.getTotalPickupTime());
		scenarioResponse.setTotalSmallMovingTrucks(scenarios.getTotalSmallMovingTrucks());
		scenarioResponse.setTotalTrailers(scenarios.getTotalTrailers());
		scenarioResponse.setTotalTravelTime(scenarios.getTotalTravelTime());
		scenarioResponse.setTotalUnpackingTime(scenarios.getTotalUnpackingTime());
		scenarioResponse.setTotalWalkingTime(scenarios.getTotalWalkingTime());
		scenarioResponse.setMovingDiscount(scenarios.getMovingDiscount());
		scenarioResponse.setPackingUnpackingDiscount(scenarios.getPackingUnpackingDiscount());
		scenarioResponse.setAssembleDisassembleDiscount(scenarios.getAssembleDisassembleDiscount());
		scenarioResponse.setHangupPickupDiscount(scenarios.getHangupPickupDiscount());

		scenarioResponse.setMovingDays(movingDaysToMovingDayResponse(scenarios.getMovingDays()));

		return scenarioResponse;
	}

	public static UserResponse userToUserResponse(User user){
		UserResponse useResponse = new UserResponse();
		useResponse.setId(user.getId().toString());
		useResponse.setFirstName(user.getFirstName());
		useResponse.setLastName(user.getLastName());
		useResponse.setEmail(user.getEmail());
		useResponse.setPassword(user.getPassword());
		useResponse.setCreatedAt(user.getCreatedAt());
		useResponse.setSuperAdmin(user.getSuperAdmin());

		return useResponse;
	}

}