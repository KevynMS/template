package com.flakkeeverhuizers.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchAddressResponse {


	private Integer id;

	private CountryResponse country;

	private String street;

	private String houseNumber;

	private String zipcode;

	private String city;

	private String addressTypeCode;

}
