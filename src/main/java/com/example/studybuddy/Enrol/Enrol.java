package com.example.studybuddy.Enrol;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@IdClass(EnrolId.class)
public class Enrol implements Serializable {
    @Id
    @Column(name = "roomId")
    private Integer roomId;

    @Id
    @Column(name = "userId")
    private String userId;
    private Integer auth;
}
