package com.example.studybuddy.Room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public List<Room> getList() {
        return this.roomRepository.findAll();
    }

    //스터디룸 생성
    public void create(String roomName, String roomContent) {
        Room r = new Room();
        r.setRoomId((int)(Math.random() * 899999) + 100000);    //랜덤 ID부여
        r.setRoomName(roomName);
        r.setRoomContent(roomContent);
        this.roomRepository.save(r);
    }
}
