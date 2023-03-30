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
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "street")
    private String street;

    @Column(name = "housenumber")
    private String houseNumber;

    @Column(name = "postalcode")
    private String postalCode;

    @Column(name = "city")
    private String city;

    @Column(name = "email")
    private String email;

    @Column(name = "password_digest")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Roles role;

    @Column(name = "phone")
    private String phone;

    @Column(name = "auth_token")
    private String authToken;

    @Column(name = "type")
    private String type;

    @Column(name = "password_reset_token")
    private String passwordResetToken;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "activate_token")
    private String activateToken;

    @Column(name = "password_reset_sent_at")
    private LocalDateTime passwordResetSentAt;

    //private Integer plan_id;
    //private Integer region_id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "radius")
    private Integer radius;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "franchise_region_id")
    private Integer franchiseRegionId;

    @Column(name = "newsletter")
    private Boolean newsletter;


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
