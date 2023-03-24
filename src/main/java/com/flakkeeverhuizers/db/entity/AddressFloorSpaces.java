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
@Table(name = "address_floor_spaces")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressFloorSpaces {


	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "address_floor_space_id", updatable = false, nullable = false, columnDefinition = "char(36)")
	@Type(type = "uuid-char")
	private UUID addressFloorSpace;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_floor_id")
	private AddressFloors addressFloor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "space_id")
	private Spaces space;

	@Column(name = "label")
	private String label;

	@Column(name = "created_dt")
	private LocalDateTime createdDt;

	@Column(name = "relocating")
	private Boolean relocating;

	@Column(name = "sequence")
	private int sequence;


	/*************/

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "addressFloorSpace")
	@OrderBy("createdDt ASC")
	private Set<AddressFloorSpaceItems> addressFloorSpaceItems;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "addressFloorSpace")
	@OrderBy("createdDt ASC")
	private Set<AddressFloorSpaceBoxes> addressFloorSpaceBoxes;

}