package com.peaksoft.testmedchek.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.PERSIST;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ClinicService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String service_name,description,text;
    private Double price;

    @OneToMany(cascade = ALL, fetch = FetchType.EAGER,mappedBy = "clinicService")
    private List<Personal> personalList;

    public void addPersonalToService(Personal personal)
    { if (personalList == null) {
        personalList = new ArrayList<>();
    }
        personalList.add(personal);
        personal.setClinicService(this);
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_clinicService",
            joinColumns = @JoinColumn(name = "clinicService_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id") )
    private List<User> userList;

    public ClinicService(String service_name, String description, String text, Double price) {
        this.service_name = service_name;
        this.description = description;
        this.text = text;
        this.price = price;
    }
}
