package com.peaksoft.testmedchek.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @SequenceGenerator(name = "role_gen", sequenceName = "role_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_gen")
    private Long id;

    private String roleName;

    @OneToMany(cascade = {MERGE,REFRESH,DETACH},mappedBy = "role")
    private List<User> users;
}
