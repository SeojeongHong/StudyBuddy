package com.example.studybuddy.Room;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Room {
    @Id
    private Integer roomId;

    @Column(length = 200)
    private String roomName;

    @Column(columnDefinition = "TEXT")
    private String roomContent;

}
