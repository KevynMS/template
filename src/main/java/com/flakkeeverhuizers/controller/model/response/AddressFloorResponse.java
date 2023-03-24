package com.flakkeeverhuizers.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressFloorResponse {


	private String id;

	private AddressResponse address;

	private FloorResponse floor;

	private String label;

	private int number;

	private LocalDateTime createdDt;

	private Boolean relocating;

	private int sequence;

	private Set<AddressFloorSpaceResponse> addressFloorSpaces;

}
