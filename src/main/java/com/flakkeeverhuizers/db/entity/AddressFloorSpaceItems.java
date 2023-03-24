package com.flakkeeverhuizers.db.entity; 


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "address_floor_space_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressFloorSpaceItems {


	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "address_floor_space_item_id", updatable = false, nullable = false, columnDefinition = "char(36)")
	@Type(type = "uuid-char")
	private UUID addressFloorSpaceItem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_floor_space_id")
	private AddressFloorSpaces addressFloorSpace;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Items item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "storage_address_id")
	private Addresses storageAddress;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unload_address_id")
	private Addresses unloadAddress;

	@Column(name = "relocating")
	private Boolean relocating;

	@Column(name = "volume")
	private BigDecimal volume;

	@Column(name = "fragile")
	private Boolean fragile;

	@Column(name = "amount")
	private int amount;

	@Column(name = "assemble_time")
	private int assembleTime;

	@Column(name = "assemble")
	private Boolean assemble;

	@Column(name = "disassemble")
	private Boolean disassemble;

	@Column(name = "notes")
	@Lob
	private String notes;

	@Column(name = "disassemble_time")
	private int disassembleTime;

	@Column(name = "pick_up")
	private Boolean pickUp;

	@Column(name = "pick_up_time")
	private int pickUpTime;

	@Column(name = "hang_up")
	private Boolean hangUp;

	@Column(name = "hang_up_time")
	private int hangUpTime;

	@Column(name = "created_dt")
	private LocalDateTime createdDt;

	@Column(name = "label")
	private String label;

	@Column(name = "sequence")
	private int sequence;

	/*************/

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "addressFloorSpaceItem")
	private Set<AddressFloorSpaceBoxes> addressFloorSpaceBoxes;

}