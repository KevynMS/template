package com.template.db.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;



// This is a generic user class, so please adapt this class to your specific project

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "user_id", updatable = false, nullable = false, columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created_dt")
    private LocalDateTime createdAt;

    @Column(name = "superAdmin")
    private Boolean superAdmin;

    public Boolean isAdmin(){
        // Change this method to return if the user is admin or not
        // by default it is using the super admin field, bt you can change to any field for the specific project

        if(this.superAdmin != null && this.superAdmin){
            return Boolean.TRUE;
        }
        return  Boolean.FALSE;
    }

}
