package com.flakkeeverhuizers.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressFloorSpaceBoxResponse {


	private String id;

	private BoxResponse box;

	private AddressFloorSpaceResponse addressFloorSpace;

	private AddressResponse storageAddress;

	private AddressResponse unloadAddress;

	private Boolean fragile;

	private BigDecimal volume;

	private int amount;

	private LocalDateTime createdDt;

	private AddressFloorSpaceItemResponse addressFloorSpaceItem;

	private Boolean relocating;

	private int sequence;

}
