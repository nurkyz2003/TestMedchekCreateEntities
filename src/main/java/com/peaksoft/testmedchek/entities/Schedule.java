package com.peaksoft.testmedchek.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @SequenceGenerator(name = "schedule_gen", sequenceName = "schedule_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_gen")
    private Long id;

    private LocalDate date;

    private LocalDate time;

    @ManyToOne(cascade = {DETACH, MERGE, REFRESH})
    @JoinColumn(name = "personal_id")
    private Personal personal;

}
