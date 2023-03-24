package com.flakkeeverhuizers.controller.model.request;

import com.flakkeeverhuizers.controller.model.response.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    private RelocationRequest relocation;

    private AddressTypeRequest addressType;

    private HouseTypeRequest houseType;

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

    private LiftOptionRequest lift;

    private String localAddress;

    private CountryRequest country;

    private String addressNo;

    private int packingSpeed;

    private int unpackingSpeed;

    private int loadingFactor;

    private int unloadingFactor;

    private Set<AddressFloorRequest> addressFloors;

    private Set<AddressCommentRequest> AddressComments;
}
