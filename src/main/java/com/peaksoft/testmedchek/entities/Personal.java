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
@Table(name = "personals")
public class Personal {

    @Id
    @SequenceGenerator(name = "personal_gen", sequenceName = "personal_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personal_gen")
    private Long id;

    private String personalFirstName;

    private String personalLastName;

    private String personalPosition;

    private Boolean personalStatus;

    private String personalImage;

    private String personalInformation;

    @ManyToOne(cascade = {DETACH, MERGE, REFRESH})
    private ClinicService clinicService;

    @OneToMany(cascade = ALL, mappedBy = "personal")
    private List<Schedule> schedules;

    @OneToMany(cascade = ALL, mappedBy = "personal")
    private List<OnlineEntry> onlineEntries;

}
