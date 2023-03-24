package com.flakkeeverhuizers.controller.model.request;

import com.flakkeeverhuizers.controller.model.response.AddressFloorResponse;
import com.flakkeeverhuizers.controller.model.response.AddressFloorSpaceBoxResponse;
import com.flakkeeverhuizers.controller.model.response.AddressFloorSpaceItemResponse;
import com.flakkeeverhuizers.controller.model.response.SpaceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressFloorSpaceRequest {

    private AddressFloorRequest addressFloor;

    private SpaceRequest space;

    private String label;

    private LocalDateTime createdDt;

    private Boolean relocating;

    private int sequence;

    private Set<AddressFloorSpaceItemRequest> addressFloorSpaceItems;

    private Set<AddressFloorSpaceBoxRequest> addressFloorSpaceBoxes;
}
