package com.flakkeeverhuizers.controller.model.request;

import com.flakkeeverhuizers.controller.model.response.AddressFloorSpaceItemResponse;
import com.flakkeeverhuizers.controller.model.response.AddressFloorSpaceResponse;
import com.flakkeeverhuizers.controller.model.response.AddressResponse;
import com.flakkeeverhuizers.controller.model.response.BoxResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressFloorSpaceBoxRequest {

    private BoxRequest box;

    private AddressFloorSpaceRequest addressFloorSpace;

    private AddressRequest storageAddress;

    private AddressRequest unloadAddress;

    private Boolean fragile;

    private BigDecimal volume;

    private int amount;

    private LocalDateTime createdDt;

    private AddressFloorSpaceItemRequest addressFloorSpaceItem;

    private Boolean relocating;

    private int sequence;
}
