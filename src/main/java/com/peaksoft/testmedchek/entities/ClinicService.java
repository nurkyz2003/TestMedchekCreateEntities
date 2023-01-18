package com.peaksoft.testmedchek.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clinic_services")
public class ClinicService {

    @Id
    @SequenceGenerator(name = "clinic_service_gen", sequenceName = "clinic_service_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clinic_service_gen")
    private Long id;

    private String clinicServiceName;

    @OneToMany(cascade = {DETACH, MERGE, REFRESH}, mappedBy = "clinicService")
    private List<Personal> personals;

    @ManyToMany(cascade = {DETACH, MERGE, REFRESH}, mappedBy = "clinicServices")
    private List<User> users;
}
