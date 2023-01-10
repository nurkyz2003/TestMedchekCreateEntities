package com.peaksoft.testmedchek.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.PERSIST;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_Name, last_Name, position, image, information;
    private Boolean status;
    private LocalDate personal_date;
    private LocalTime personal_time;

    @ManyToOne(cascade = {MERGE,REFRESH,DETACH,PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "clinicService_id")
    private  ClinicService clinicService;

    public Personal(String first_Name, String last_Name, String position,
                    String image, String information, Boolean status, LocalDate personal_date, LocalTime personal_time) {
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.position = position;
        this.image = image;
        this.information = information;
        this.status = status;
        this.personal_date = personal_date;
        this.personal_time = personal_time;
    }
}
