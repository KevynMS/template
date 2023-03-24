package com.flakkeeverhuizers.db.entity; 


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "address_floors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressFloors {


	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "address_floor_id", updatable = false, nullable = false, columnDefinition = "char(36)")
	@Type(type = "uuid-char")
	private UUID addressFloor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Addresses address;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "floor_id")
	private Floors floor;

	@Column(name = "label")
	private String label;

	@Column(name = "number")
	private int number;

	@Column(name = "created_dt")
	private LocalDateTime createdDt;

	@Column(name = "relocating")
	private Boolean relocating;

	@Column(name = "sequence")
	private int sequence;


	/*************/

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "addressFloor")
	@OrderBy("createdDt ASC")
	private Set<AddressFloorSpaces> addressFloorSpaces;



}