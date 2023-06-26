package com.example.studybuddy.Room;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RoomForm {
    @NotEmpty(message="스터디룸 이름을 입력하세요")
    @Size(max=200)
    private String roomName;

    @NotEmpty(message="스터디룸 소개를 입력하세요")
    private String roomContent;
}
