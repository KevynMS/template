package com.flakkeeverhuizers.db.entity; 


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;


@Entity
@Table(name = "relocation_labels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelocationLabels implements Serializable {


	@EmbeddedId
	private RelocationLabelsKey id;

}