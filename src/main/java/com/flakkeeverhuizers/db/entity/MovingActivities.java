package com.flakkeeverhuizers.db.entity; 


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "moving_activities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovingActivities {


	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "moving_activity_id", updatable = false, nullable = false, columnDefinition = "char(36)")
	@Type(type = "uuid-char")
	private UUID movingActivity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "moving_day_id")
	private MovingDays movingDay;

	@Column(name = "sequence")
	private int sequence;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Addresses address;

	@Column(name = "travel_time")
	private double travelTime;

	@Column(name = "packing_time")
	private double packingTime;

	@Column(name = "unpacking_time")
	private double unpackingTime;

	@Column(name = "walking_time")
	private double walkingTime;

	@Column(name = "moving_time")
	private double movingTime;

	@Column(name = "elevator_time")
	private double elevatorTime;

	@Column(name = "extra_work")
	private double extraWork;

	@Column(name = "is_loading")
	private Boolean isLoading;

	@Column(name = "is_unloading")
	private Boolean isUnloading;

	@Column(name = "type")
	private String type;

	@Column(name = "arrival_time")
	private LocalDateTime arrivalTime;

	@Column(name = "departure_time")
	private LocalDateTime departureTime;

	@Column(name = "start_time")
	private LocalDateTime startTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_address_id")
	private BranchAddresses branchAddress;

}