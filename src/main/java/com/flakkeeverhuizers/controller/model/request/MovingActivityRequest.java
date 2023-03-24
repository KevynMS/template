package com.flakkeeverhuizers.controller.model.request;

import com.flakkeeverhuizers.controller.model.response.AddressResponse;
import com.flakkeeverhuizers.controller.model.response.BranchAddressResponse;
import com.flakkeeverhuizers.controller.model.response.MovingDayResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovingActivityRequest {

    private MovingDayRequest movingDay;

    private int sequence;

    private AddressRequest address;

    private double travelTime;

    private double packingTime;

    private double unpackingTime;

    private double walkingTime;

    private double movingTime;

    private double elevatorTime;

    private double extraWork;

    private Boolean isLoading;

    private Boolean isUnloading;

    private String type;

    private LocalDateTime arrivalTime;

    private LocalDateTime departureTime;

    private LocalDateTime startTime;

    private BranchAddressRequest branchAddress;
}
