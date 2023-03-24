package com.flakkeeverhuizers.db.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "items")
@Data
public class Items {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "item_id", updatable = false, nullable = false, columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_category_id")
    private ItemCategories itemCategories;

    @Column(name = "volume")
    private BigDecimal volume;

    @Column(name = "assemble_time")
    private int assembleTime;

    @Column(name = "assemble")
    private Boolean assemble;

    @Column(name = "disassemble")
    private Boolean disassemble;

    @Column(name = "disassemble_time")
    private int disassembleTime;

    @Column(name = "pick_up")
    private Boolean pickUp;

    @Column(name = "pick_up_time")
    private int pickUpTime;

    @Column(name = "hang_up")
    private Boolean hangUp;

    @Column(name = "hang_up_time")
    private int hangUpTime;

    @Column(name = "notes")
    @Lob
    private String notes;

    @Column(name = "custom")
    private Boolean custom = Boolean.FALSE;
}
