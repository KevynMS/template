package com.flakkeeverhuizers.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {


	private String id;

	private RelocationResponse relocation;

	private AddressTypeResponse addressType;

	private HouseTypeResponse houseType;

	private String street;

	private String houseNumber;

	private String zipcode;

	private String city;

	private Boolean mobilePlatformNeeded;

	private String movingLift;

	private int walkingDistance;

	private Boolean indoorElevator;

	private Boolean assemble;

	private Boolean exemption;

	private Boolean exemptionHandledByCustomer;

	private Boolean relocating;

	private int sequence;

	private int floorNumber;

	private LiftOptionResponse lift;

	private String localAddress;

	private CountryResponse country;

	private String addressNo;

	private int packingSpeed;

	private int unpackingSpeed;

	private int loadingFactor;

	private int unloadingFactor;

	private Set<AddressFloorResponse> addressFloors;

	private Set<AddressCommentResponse> AddressComments;

}
