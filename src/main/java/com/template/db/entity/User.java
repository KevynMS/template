package com.template.db.entity;

import com.template.db.enums.RolesName;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;


// TODO This is a generic user class, so please adapt this class to your specific project

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

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Roles role;

    public SimpleGrantedAuthority getAuthorityByRole() {
        if (this.role.getName().equals(RolesName.administrator)) {
            return new SimpleGrantedAuthority("ROLE_" + RolesName.administrator.name().toUpperCase(Locale.ROOT));
        } else if (this.role.getName().equals(RolesName.franchiser)) {
            return new SimpleGrantedAuthority("ROLE_" + RolesName.franchiser.name().toUpperCase(Locale.ROOT));
        } else if (this.role.getName().equals(RolesName.advertiser)) {
            return new SimpleGrantedAuthority("ROLE_" + RolesName.advertiser.name().toUpperCase(Locale.ROOT));
        }
        return new SimpleGrantedAuthority("");
    }

}
