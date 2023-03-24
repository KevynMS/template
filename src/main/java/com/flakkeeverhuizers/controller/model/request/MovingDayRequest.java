package com.flakkeeverhuizers.controller.model.request;

import com.flakkeeverhuizers.controller.model.response.MovingActivityResponse;
import com.flakkeeverhuizers.controller.model.response.ScenarioResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovingDayRequest {

    private ScenarioRequest scenario;

    private int sequence;

    private String name;

    private int amountMen;

    private int manHours;

    private int manHoursEach;

    private int movingTrucks;

    private int smallMovingTrucks;

    private int trailers;

    private Set<MovingActivityRequest> movingActivities;

}
