package com.peaksoft.testmedchek.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "results")
public class Result {

    @Id
    @SequenceGenerator(name = "result_gen", sequenceName = "result_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_gen")
    private Long id;

    private String resultOrderNumber;

    private String resultFile;

    @ManyToOne(cascade = {DETACH, MERGE, REFRESH})
    private User user;
}
