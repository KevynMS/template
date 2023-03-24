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
@Table(name = "addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Addresses {


	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "address_id", updatable = false, nullable = false, columnDefinition = "char(36)")
	@Type(type = "uuid-char")
	private UUID address;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relocation_id")
	private Relocations relocation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_type_id")
	private AddressTypes addressType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "house_type_id")
	private HouseTypes houseType;

	@Column(name = "street")
	private String street;

	@Column(name = "house_number")
	private String houseNumber;

	@Column(name = "zipcode")
	private String zipcode;

	@Column(name = "city")
	private String city;

	@Column(name = "mobile_platform_needed")
	private Boolean mobilePlatformNeeded;

	@Column(name = "moving_lift")
	private String movingLift;

	@Column(name = "walking_distance")
	private int walkingDistance;

	@Column(name = "indoor_elevator")
	private Boolean indoorElevator;

	@Column(name = "assemble")
	private Boolean assemble;

	@Column(name = "exemption")
	private Boolean exemption;

	@Column(name = "exemption_handled_by_customer")
	private Boolean exemptionHandledByCustomer;

	@Column(name = "relocating")
	private Boolean relocating;

	@Column(name = "sequence")
	private int sequence;

	@Column(name = "floor_number")
	private int floorNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lift_id")
	private LiftOptions lift;

	@Column(name = "local_address_id")
	private String localAddress;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Countries country;

	@Column(name = "address_no")
	private String addressNo;

	@Column(name = "packing_speed")
	private int packingSpeed;

	@Column(name = "unpacking_speed")
	private int unpackingSpeed;

	@Column(name = "loading_factor")
	private int loadingFactor;

	@Column(name = "unloading_factor")
	private int unloadingFactor;

	/*************/

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
	@OrderBy("createdDt ASC")
	private Set<AddressFloors> addressFloors;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
	private Set<AddressComments> AddressComments;

}