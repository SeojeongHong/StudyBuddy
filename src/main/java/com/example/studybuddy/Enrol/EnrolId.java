package com.example.studybuddy.Enrol;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EnrolId implements Serializable {
    @Column(name = "roomId")
    private Integer roomId;

    @Column(name = "userId")
    private String userId;
}
