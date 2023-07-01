package com.example.studybuddy.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query(value = "select * from enrol natural join room where user_id = ?1", nativeQuery = true)
    List<Room> findMyRoom(String siteUser);

}
