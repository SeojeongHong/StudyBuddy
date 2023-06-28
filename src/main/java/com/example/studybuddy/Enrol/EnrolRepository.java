package com.example.studybuddy.Enrol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EnrolRepository extends JpaRepository<Enrol, Integer> {
    @Query("SELECT e FROM Enrol e WHERE e.roomId = :roomId AND e.userId = :siteUser")
    Optional<Enrol> getAuth(@Param("roomId") Integer roomId, @Param("siteUser") String siteUser);
}
