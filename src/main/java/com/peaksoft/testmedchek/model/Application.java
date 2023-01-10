package com.peaksoft.testmedchek.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.PERSIST;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customer_phone_number, customer_last_name;

    @ManyToOne(cascade = {MERGE,REFRESH,DETACH,PERSIST}, fetch = FetchType.EAGER)
    private User user;
}
