package com.flakkeeverhuizers.db.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "address_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressTypes {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "address_type_id", updatable = false, nullable = false, columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "code")
    private String code;

    @Column(name = "method")
    private String method;

    @Column(name = "sequence")
    private int sequence = 99;

}
