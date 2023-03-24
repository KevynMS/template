package com.flakkeeverhuizers.controller.model.request;

import com.flakkeeverhuizers.controller.model.response.RelocationResponse;
import com.flakkeeverhuizers.controller.model.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteRequest {

    private RelocationRequest relocation;

    private UserRequest user;

    private String content;

    private LocalDateTime createdDt;
}
