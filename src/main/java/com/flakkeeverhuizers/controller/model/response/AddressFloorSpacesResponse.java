package com.flakkeeverhuizers.controller.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressFloorSpacesResponse {

	private List<AddressFloorSpaceResponse> addressFloorSpaces;
}