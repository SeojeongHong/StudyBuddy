package com.example.studybuddy.Enrol;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Enrol {
    @Id
    private Integer roomId;

    private String userId;

    private Integer auth;
}
