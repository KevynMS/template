package com.flakkeeverhuizers.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponse {


	private String id;

	private RelocationResponse relocation;

	private UserResponse user;

	private String content;

	private LocalDateTime createdDt;

}
