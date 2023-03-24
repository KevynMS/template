package com.flakkeeverhuizers.controller.model.request;

import com.flakkeeverhuizers.controller.model.response.AddressFloorSpaceResponse;
import com.flakkeeverhuizers.controller.model.response.AddressResponse;
import com.flakkeeverhuizers.controller.model.response.FloorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressFloorRequest {

    private AddressRequest address;

    private FloorRequest floor;

    private String label;

    private int number;

    private LocalDateTime createdDt;

    private Boolean relocating;

    private int sequence;

    private Set<AddressFloorSpaceRequest> addressFloorSpaces;
}
