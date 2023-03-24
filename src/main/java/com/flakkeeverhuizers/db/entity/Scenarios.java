package com.flakkeeverhuizers.db.entity; 


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "scenarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scenarios {


	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "scenario_id", updatable = false, nullable = false, columnDefinition = "char(36)")
	@Type(type = "uuid-char")
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quotation_id")
	private Quotations quotation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relocation_id")
	private Relocations relocation;

	@Column(name = "name")
	private String name;

	@Column(name = "boxes_loaded_per_hour")
	private int boxesLoadedPerHour;

	@Column(name = "boxes_unloaded_per_hour")
	private int boxesUnloadedPerHour;

	@Column(name = "warranty_variable")
	private Boolean warrantyVariable;

	@Column(name = "warranty_variable_amount")
	private int warrantyVariableAmount;

	@Column(name = "warranty_variable_factor")
	private int warrantyVariableFactor;

	@Column(name = "travel_time_assembly_disassembly")
	private double travelTimeAssemblyDisassembly;

	@Column(name = "travel_time_hangup_pickup")
	private double travelTimeHangupPickup;

	@Column(name = "travel_time_packing_unpacking")
	private double travelTimePackingUnpacking;

	@Column(name = "travel_time_moving")
	private double travelTimeMoving;

	@Column(name = "total_amount_men")
	private int totalAmountMen;

	@Column(name = "total_assemble_time")
	private int totalAssembleTime;

	@Column(name = "total_disassemble_time")
	private int totalDisassembleTime;

	@Column(name = "total_elevator_time")
	private double totalElevatorTime;

	@Column(name = "total_extra_work_time")
	private int totalExtraWorkTime;

	@Column(name = "total_hangup_time")
	private int totalHangupTime;

	@Column(name = "total_man_hours")
	private double totalManHours;

	@Column(name = "total_man_hours_each")
	private double totalManHours_each;

	@Column(name = "total_moving_time")
	private double totalMovingTime;

	@Column(name = "total_moving_trucks")
	private int totalMovingTrucks;

	@Column(name = "total_packing_time")
	private int totalPackingTime;

	@Column(name = "total_pickup_time")
	private int totalPickupTime;

	@Column(name = "total_small_moving_trucks")
	private int totalSmallMovingTrucks;

	@Column(name = "total_trailers")
	private int totalTrailers;

	@Column(name = "total_travel_time")
	private double totalTravelTime;

	@Column(name = "total_unpacking_time")
	private int totalUnpackingTime;

	@Column(name = "total_walking_time")
	private int totalWalkingTime;

	@Column(name = "moving_discount")
	private int movingDiscount;

	@Column(name = "packing_unpacking_discount")
	private int packingUnpackingDiscount;

	@Column(name = "assemble_disassemble_discount")
	private int assembleDisassembleDiscount;

	@Column(name = "hangup_pickup_discount")
	private int hangupPickupDiscount;

	/*************/

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "scenario")
	private Set<MovingDays> movingDays;
}