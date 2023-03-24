package com.flakkeeverhuizers.controller.model.response;

import com.flakkeeverhuizers.db.entity.MovingActivities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovingDayResponse {


	private String id;

	private ScenarioResponse scenario;

	private int sequence;

	private String name;

	private int amountMen;

	private int manHours;

	private int manHoursEach;

	private int movingTrucks;

	private int smallMovingTrucks;

	private int trailers;

	private Set<MovingActivityResponse> movingActivities;

}
