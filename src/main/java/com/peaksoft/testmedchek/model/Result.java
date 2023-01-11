package com.peaksoft.testmedchek.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String order_number;

    @ManyToOne(cascade = {MERGE,REFRESH,DETACH,PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


    public Result(String order_number, String order_image, User user) {
        this.order_number = order_number;
        this.user = user;
    }
}
