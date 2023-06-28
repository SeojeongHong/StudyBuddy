package com.example.studybuddy.Room;

import com.example.studybuddy.User.SiteUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Room {
    @Id
    private Integer roomId;
    private String hostId;
    @Column(length = 200)
    private String roomName;

    @Column(columnDefinition = "TEXT")
    private String roomContent;

    @Column
    private Integer maximum;

}
