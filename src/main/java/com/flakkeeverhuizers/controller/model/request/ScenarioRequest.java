package com.flakkeeverhuizers.controller.model.request;

import com.flakkeeverhuizers.controller.model.response.MovingDayResponse;
import com.flakkeeverhuizers.controller.model.response.QuotationResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScenarioRequest {

    private QuotationRequest quotation;

    private String name;

    private int boxesLoadedPerHour;

    private int boxesUnloadedPerHour;

    private Boolean warrantyVariable;

    private int warrantyVariableAmount;

    private int warrantyVariableFactor;

    private double travelTimeAssemblyDisassembly;

    private double travelTimeHangupPickup;

    private double travelTimePackingUnpacking;

    private double travelTimeMoving;

    private int totalAmountMen;

    private int totalAssembleTime;

    private int totalDisassembleTime;

    private double totalElevatorTime;

    private int totalExtraWorkTime;

    private int totalHangupTime;

    private double totalManHours;

    private double totalManHours_each;

    private double totalMovingTime;

    private int totalMovingTrucks;

    private int totalPackingTime;

    private int totalPickupTime;

    private int totalSmallMovingTrucks;

    private int totalTrailers;

    private double totalTravelTime;

    private int totalUnpackingTime;

    private int totalWalkingTime;

    private int movingDiscount;

    private int packingUnpackingDiscount;

    private int assembleDisassembleDiscount;

    private int hangupPickupDiscount;

    private Set<MovingDayRequest> movingDays;
}
