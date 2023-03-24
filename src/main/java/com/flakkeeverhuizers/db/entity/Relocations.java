package com.flakkeeverhuizers.db.entity; 


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "relocations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relocations {


	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "relocation_id", updatable = false, nullable = false, columnDefinition = "char(36)")
	@Type(type = "uuid-char")
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "via_id")
	private Vias viaId;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quotation_id")
	private Quotations quotationId;

	@Column(name = "applicantName")
	private String applicantName;

	@Column(name = "phone")
	private String phone;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "extra_phones")
	@Lob
	private String extraPhones;

	@Column(name = "email")
	private String email;

	@Column(name = "extra_emails")
	@Lob
	private String extraEmails;

	@Column(name = "storage")
	private Boolean storage;

	@Column(name = "relocation_date")
	private LocalDateTime relocationDate;

	@Column(name = "iban")
	private String iban;

	@Column(name = "billing_to", columnDefinition = "TEXT")
	//@Lob
	private String billingTo;

	@Column(name = "created_dt")
	private LocalDateTime createdDt;

	@Column(name = "agepe_import_xml")
	@Lob
	private String agepeImportXml;

	@Column(name = "relation_no")
	private String relationNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "extra_mobile_phones")
	@Lob
	private String extraMobilePhones;

	@Column(name = "short_name")
	private String shortName;

	@Column(name = "record_no")
	private String recordNo;

	@Column(name = "moving_truck_rate")
	private int movingTruckRate;

	@Column(name = "small_moving_truck_rate")
	private int small_movingTruckRate;

	@Column(name = "trailer_rate")
	private int trailerRate;

	@Column(name = "moving_rate")
	private int movingRate;

	@Column(name = "moving_elevator_rate")
	private int movingElevatorRate;

	/*************/

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "relocation")
	private Set<Addresses> addresses;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "relocation")
	private Set<Notes> notes;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "relocation")
	private Set<Scenarios> scenarios;

}