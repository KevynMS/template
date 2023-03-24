package com.flakkeeverhuizers.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotationResponse {


	private String id;

	private ScenarioResponse scenarios;

	private BigDecimal totalPrice;

	private RelocationResponse relocation;

	private VatRateResponse vatRate;

	private int totalTravelTime;

	private BigDecimal hourlyRate;

	private BigDecimal internHourlyrate;

	private int movers;

	private int interns;

	private BigDecimal trailerPrice;

	private BigDecimal travelTimeFactor;

	private BigDecimal assembleFactor;

	private BigDecimal hangupFactor;

	private BigDecimal packFactor;

	private BigDecimal loadFactor;

}
