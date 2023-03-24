package com.flakkeeverhuizers.db.entity; 


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "branch_addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchAddresses {


	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "branch_address_id")
	private Integer branchAddress;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Countries country;

	@Column(name = "street")
	private String street;

	@Column(name = "house_number")
	private String houseNumber;

	@Column(name = "zipcode")
	private String zipcode;

	@Column(name = "city")
	private String city;

	@Column(name = "address_type_code")
	private String addressTypeCode;

}