package com.flakkeeverhuizers.db.entity; 


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "moving_days")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovingDays {


	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "moving_day_id", updatable = false, nullable = false, columnDefinition = "char(36)")
	@Type(type = "uuid-char")
	private UUID movingDay;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scenario_id")
	private Scenarios scenario;

	@Column(name = "sequence")
	private int sequence;

	@Column(name = "name")
	private String name;

	@Column(name = "amount_men")
	private int amountMen;

	@Column(name = "man_hours")
	private int manHours;

	@Column(name = "man_hours_each")
	private int manHoursEach;

	@Column(name = "moving_trucks")
	private int movingTrucks;

	@Column(name = "small_moving_trucks")
	private int smallMovingTrucks;

	@Column(name = "trailers")
	private int trailers;

	/*************/

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movingDay")
	private Set<MovingActivities> movingActivities;
}