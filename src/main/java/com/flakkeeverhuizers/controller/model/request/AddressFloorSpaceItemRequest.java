package com.flakkeeverhuizers.controller.model.request;

import com.flakkeeverhuizers.controller.model.response.AddressFloorSpaceBoxResponse;
import com.flakkeeverhuizers.controller.model.response.AddressFloorSpaceResponse;
import com.flakkeeverhuizers.controller.model.response.AddressResponse;
import com.flakkeeverhuizers.controller.model.response.ItemCategoriesResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressFloorSpaceItemRequest {

    private AddressFloorSpaceRequest addressFloorSpace;

    private ItemCategoriesResponse item;

    private AddressRequest storageAddress;

    private AddressRequest unloadAddress;

    private Boolean relocating;

    private BigDecimal volume;

    private Boolean fragile;

    private int amount;

    private int assembleTime;

    private Boolean assemble;

    private Boolean disassemble;

    private String notes;

    private int disassembleTime;

    private Boolean pickUp;

    private int pickUpTime;

    private Boolean hangUp;

    private int hangUpTime;

    private LocalDateTime createdDt;

    private String label;

    private int sequence;

    private Set<AddressFloorSpaceBoxRequest> addressFloorSpaceBoxes;

}
