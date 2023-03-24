package com.flakkeeverhuizers.db.entity; 


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notes {


	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "note_id", updatable = false, nullable = false, columnDefinition = "char(36)")
	@Type(type = "uuid-char")
	private UUID note;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relocation_id")
	private Relocations relocation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "content")
	@Lob
	private String content;

	@Column(name = "created_dt")
	private LocalDateTime createdDt;

}