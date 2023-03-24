package com.flakkeeverhuizers.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressFloorSpaceResponse {


	private String id;

	private AddressFloorResponse addressFloor;

	private SpaceResponse space;

	private String label;

	private LocalDateTime createdDt;

	private Boolean relocating;

	private int sequence;

	private Set<AddressFloorSpaceItemResponse> addressFloorSpaceItems;

	private Set<AddressFloorSpaceBoxResponse> addressFloorSpaceBoxes;

}
