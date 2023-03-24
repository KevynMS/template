package com.flakkeeverhuizers.db.entity; 


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "extended_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedUser {


	@Id
	@Column(name = "user_id", updatable = false, nullable = false, columnDefinition = "char(36)")
	@Type(type = "uuid-char")
	private UUID id; //user id

	@Column(name = "agepe_id")
	private int agepeId;

}