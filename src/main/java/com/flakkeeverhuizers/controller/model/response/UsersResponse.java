package com.flakkeeverhuizers.controller.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponse {

	private List<UserResponse> user;
}