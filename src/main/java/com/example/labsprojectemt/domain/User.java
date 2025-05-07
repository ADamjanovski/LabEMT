package com.example.labsprojectemt.domain;


import com.example.labsprojectemt.domain.enumerations.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@Table(name = "app_user") // or any non-reserved word
public class User implements UserDetails {

    @Id
    private String username;
    private String password;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Where(clause = "status = 'CONFIRMED'")
    private List<Reservation> reservations;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    public User(String username, String password, String name, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public User() {

    }



    public User(UserDetails userDetails) {
        this.username = userDetails.getUsername();
        this.password = userDetails.getPassword();
    }

    public User(String username, String name, String role, List<Reservation> reservations) {
        this.username=username;
        this.name=name;
        this.role=Role.valueOf(role);
        this.reservations=reservations;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList((GrantedAuthority) role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }


    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
