package com.peaksoft.testmedchek.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "online_entries")
public class OnlineEntry {

    @Id
    @SequenceGenerator(name = "online_entry_gen", sequenceName = "online_entry_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "online_entry_gen")
    private Long id;

    private String onlineEntryStatus;

    @ManyToOne(cascade = {DETACH, MERGE, REFRESH})
    private User user;

    @OneToOne(cascade = {DETACH, MERGE, REFRESH})
    private ClinicService clinicService;

    @ManyToOne(cascade = {DETACH, MERGE, REFRESH})
    private Personal personal;
}
