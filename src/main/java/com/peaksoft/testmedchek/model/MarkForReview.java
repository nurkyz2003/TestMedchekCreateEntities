package com.peaksoft.testmedchek.model;

public enum MarkForReview {
    One(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5);

    public final Integer label;

    MarkForReview(Integer label) {
        this.label = label;
    }
}

