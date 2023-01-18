package com.peaksoft.testmedchek.security.user;

import com.peaksoft.testmedchek.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  @Query("select u from User u where u.userEmail = :email")
  Optional<User> findByEmail(String email);
}
