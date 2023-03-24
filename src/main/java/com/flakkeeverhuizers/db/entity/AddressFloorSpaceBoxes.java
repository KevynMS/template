package com.flakkeeverhuizers.db.entity; 


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "address_floor_space_boxes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressFloorSpaceBoxes {


	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "address_floor_space_box_id", updatable = false, nullable = false, columnDefinition = "char(36)")
	@Type(type = "uuid-char")
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "box_id")
	private Boxes box;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_floor_space_id")
	private AddressFloorSpaces addressFloorSpace;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "storage_address_id")
	private Addresses storageAddress;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unload_address_id")
	private Addresses unloadAddress;

	@Column(name = "fragile")
	private Boolean fragile;

	@Column(name = "volume")
	private BigDecimal volume;

	@Column(name = "amount")
	private int amount;

	@Column(name = "created_dt")
	private LocalDateTime createdDt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_floor_space_item_id")
	private AddressFloorSpaceItems addressFloorSpaceItem;

	@Column(name = "relocating")
	private Boolean relocating;

	@Column(name = "sequence")
	private int sequence;

}