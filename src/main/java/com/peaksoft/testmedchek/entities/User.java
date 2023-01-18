package com.peaksoft.testmedchek.entities;

import javax.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    private Long id;

    private String userFirstName;

    private String userLastName;

    private String userEmail;

    private String userPhoneNumber;

    private String userPassword;

    private  int code;

    @ManyToOne(cascade = {DETACH, MERGE, REFRESH,PERSIST})
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(cascade = {DETACH, MERGE, REFRESH})
    @JoinTable(name = "user_clinicService",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "clinicService_id"))
    private List<ClinicService> clinicServices;

    @OneToMany(cascade = ALL, mappedBy = "user")
    private List<Result> results;

    @OneToMany(cascade = ALL, mappedBy = "user")
    private List<OnlineEntry> onlineEntries;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        return grantedAuthorities;  }


    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return getUserFirstName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
