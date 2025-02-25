package com.ezpay.model.entity;

import com.ezpay.model.enums.Gender;
import com.ezpay.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Builder
@Data
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "dni")
    private String dni;

    @Column(name = "birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Mexico_City")
    private Date birthDate;

    @Column(name = "gender", columnDefinition = "VARCHAR(17) DEFAULT 'PREFIERO_NO_DECIR'")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "role", columnDefinition = "VARCHAR(5) DEFAULT 'USER'")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "created_At")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Mexico_City")
    private Timestamp createdAt;

    @Column(name = "user_enable", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean userEnabled = true;

    @OneToOne(mappedBy = "user")
    private Account account;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return "";
    }

    @Override
    @JsonIgnore
    public String getPassword(){
        return password;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
