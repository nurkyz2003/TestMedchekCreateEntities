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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private MarkForReview markForReview;

    @ManyToOne(cascade = {MERGE,REFRESH,DETACH,PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Review(String text, MarkForReview markForReview) {
        this.text = text;
        this.markForReview = markForReview;
    }
}
