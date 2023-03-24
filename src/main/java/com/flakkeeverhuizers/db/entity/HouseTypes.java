package com.flakkeeverhuizers.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "house_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseTypes {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "house_type_id", updatable = false, nullable = false, columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "name")
    private String name;
}
