package com.peaksoft.testmedchek.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role_name;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private User user;
}
