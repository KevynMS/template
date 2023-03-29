package com.template.db.entity;

import com.template.db.enums.RolesName;
import lombok.Data;

import javax.persistence.*;

// TODO This is a generic user class, so please adapt this class to your specific project

@Entity
@Table(name = "roles")
@Data
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RolesName name;
}
