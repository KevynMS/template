package com.flakkeeverhuizers.controller.model.request;

import com.flakkeeverhuizers.controller.model.response.AddressResponse;
import com.flakkeeverhuizers.controller.model.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressCommentRequest {

    private AddressRequest address;

    private String content;

    private LocalDateTime createdDt;

    private UserRequest user;
}
