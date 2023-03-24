package com.flakkeeverhuizers.controller.model.request;

import com.flakkeeverhuizers.controller.model.response.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelocationRequest {

    private ViaRequest via;

    private QuotationRequest quotation;

    private String applicantName;

    private String phone;

    private String mobile;

    private String extraPhones;

    private String email;

    private String extraEmails;

    private Boolean storage;

    private LocalDateTime relocationDate;

    private String iban;

    private String billingTo;

    private LocalDateTime createdDt;

    private String agepeImportXml;

    private String relationNo;

    private UserRequest user;

    private String extraMobilePhones;

    private String shortName;

    private String recordNo;

    private int movingTruckRate;

    private int small_movingTruckRate;

    private int trailerRate;

    private int movingRate;

    private int movingElevatorRate;

    private Set<AddressRequest> addresses;

    private Set<NoteRequest> notes;

    private Set<ScenarioRequest> scenarios;
}
