package com.flakkeeverhuizers.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovingActivityResponse {


	private String id;

	private MovingDayResponse movingDay;

	private int sequence;

	private AddressResponse address;

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

	private BranchAddressResponse branchAddress;

}
