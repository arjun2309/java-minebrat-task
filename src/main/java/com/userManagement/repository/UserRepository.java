package com.userManagement.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.userManagement.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // for login
    Optional<User> findByUsernameAndPassword(String username, String password);

    // for search
    @Query("SELECT u FROM User u JOIN u.address a " +
           "WHERE (:name IS NULL OR u.username LIKE %:name%) " +
           "AND (:pin IS NULL OR a.pinCode = :pin) " +
           "AND (:start IS NULL OR u.registrationDate >= :start) " +
           "AND (:end IS NULL OR u.registrationDate <= :end)")
    Page<User> search(@Param("name") String name,
                      @Param("pin") String pin,
                      @Param("start") LocalDate start,
                      @Param("end") LocalDate end,
                      Pageable pageable);
}
