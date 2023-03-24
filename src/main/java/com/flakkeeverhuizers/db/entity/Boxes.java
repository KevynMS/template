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
@Table(name = "boxes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Boxes {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "box_id", updatable = false, nullable = false, columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "volume")
    private BigDecimal volume;

    @Column(name = "fragile")
    private Boolean fragile;
}
