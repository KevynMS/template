package com.flakkeeverhuizers.controller.model.response;

import com.flakkeeverhuizers.db.entity.MovingDays;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScenarioResponse {


	private String id;

	private QuotationResponse quotation;

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

	private Set<MovingDayResponse> movingDays;
}
