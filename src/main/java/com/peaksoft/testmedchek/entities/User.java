package com.peaksoft.testmedchek.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    private Long id;

    private String userFirstName;

    private String userLastName;

    private String userEmail;

    private String userPhoneNumber;

    private String userPassword;

    @OneToOne(cascade = {DETACH, MERGE, REFRESH})
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

}
