package com.flakkeeverhuizers.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressCommentResponse {


	private String id;

	private AddressResponse address;

	private String content;

	private LocalDateTime createdDt;

	private UserResponse user;

}
