package com.flakkeeverhuizers.db.entity; 


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Table(name = "quotations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quotations {


	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "quotation_id", updatable = false, nullable = false, columnDefinition = "char(36)")
	@Type(type = "uuid-char")
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scenario_id")
	private Scenarios scenarios;

	@Column(name = "total_price")
	private BigDecimal totalPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relocation_id")
	private Relocations relocation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vat_rate_id")
	private VatRates vatRate;

	@Column(name = "total_travel_time")
	private int totalTravelTime;

	@Column(name = "hourly_rate")
	private BigDecimal hourlyRate;

	@Column(name = "intern_hourly_rate")
	private BigDecimal internHourlyrate;

	@Column(name = "movers")
	private int movers;

	@Column(name = "interns")
	private int interns;

	@Column(name = "trailer_price")
	private BigDecimal trailerPrice;

	@Column(name = "travel_time_factor")
	private BigDecimal travelTimeFactor;

	@Column(name = "assemble_factor")
	private BigDecimal assembleFactor;

	@Column(name = "hangup_factor")
	private BigDecimal hangupFactor;

	@Column(name = "pack_factor")
	private BigDecimal packFactor;

	@Column(name = "load_factor")
	private BigDecimal loadFactor;

}