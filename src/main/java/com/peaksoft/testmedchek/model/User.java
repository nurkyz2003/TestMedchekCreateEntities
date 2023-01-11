package com.peaksoft.testmedchek.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name, last_name,email,password,phone_number;

    @OneToMany(cascade = ALL, fetch = FetchType.EAGER)
    private List<Result> resultList;

    public void addResultToUser(Result result)
    { if (resultList == null) {
        resultList = new ArrayList<>();
    }
        resultList.add(result);
        result.setUser(this);
    }

    @OneToMany(cascade = ALL, fetch = FetchType.EAGER,mappedBy = "user")
    private List<Application> applicationList;

    @OneToMany(cascade = ALL, fetch = FetchType.EAGER)
    private List<Review> reviewList;

    public void addReviewToUser(Review review)
    { if (reviewList == null) {
        reviewList = new ArrayList<>();
    }
        reviewList.add(review);
        review.setUser(this);
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_clinicService",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "clinicService_id")
    )
    private List<ClinicService> clinicServiceList;

   @OneToOne(cascade = ALL,fetch = FetchType.EAGER)
    private Role role;

}
