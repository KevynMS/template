package com.flakkeeverhuizers.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressFloorSpaceItemResponse {


	private String id;

	private AddressFloorSpaceResponse addressFloorSpace;

	private ItemCategoriesResponse item;

	private AddressResponse storageAddress;

	private AddressResponse unloadAddress;

	private Boolean relocating;

	private BigDecimal volume;

	private Boolean fragile;

	private int amount;

	private int assembleTime;

	private Boolean assemble;

	private Boolean disassemble;

	private String notes;

	private int disassembleTime;

	private Boolean pickUp;

	private int pickUpTime;

	private Boolean hangUp;

	private int hangUpTime;

	private LocalDateTime createdDt;

	private String label;

	private int sequence;

	private Set<AddressFloorSpaceBoxResponse> addressFloorSpaceBoxes;

}
