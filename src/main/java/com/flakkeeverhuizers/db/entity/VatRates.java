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
@Table(name = "vat_rates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VatRates {


	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "vat_rate_id", updatable = false, nullable = false, columnDefinition = "char(36)")
	@Type(type = "uuid-char")
	private UUID id;

	@Column(name = "rate")
	private BigDecimal rate;

}