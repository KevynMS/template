package com.flakkeeverhuizers.controller.model.request;

import com.flakkeeverhuizers.controller.model.response.RelocationResponse;
import com.flakkeeverhuizers.controller.model.response.ScenarioResponse;
import com.flakkeeverhuizers.controller.model.response.VatRateResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotationRequest {

    private ScenarioRequest scenarios;

    private BigDecimal totalPrice;

    private RelocationRequest relocation;

    private VatRateRequest vatRate;

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
