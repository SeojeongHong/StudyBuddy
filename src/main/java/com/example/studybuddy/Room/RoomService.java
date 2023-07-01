package com.example.studybuddy.Room;

import com.example.studybuddy.Enrol.Enrol;
import com.example.studybuddy.Enrol.EnrolRepository;
import com.example.studybuddy.User.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final EnrolRepository enrolRepository;

    //스터디룸 목록 조회
    public List<Room> getList() {
        return this.roomRepository.findAll();
    }

    //가입한 스터디룸 목록 조회
    public List<Room> getMyList(String siteUser) {
        return this.roomRepository.findMyRoom(siteUser);
    }

    public Optional<Room> getRoom(Integer roomId){return this.roomRepository.findById(roomId);};


    //스터디룸 생성
    public void create(String roomName, String roomContent, Integer maximum, String hostId) {
        Room r = new Room();
        r.setRoomId((int)(Math.random() * 899999) + 100000);    //랜덤 ID부여
        r.setRoomName(roomName);
        r.setRoomContent(roomContent);
        r.setMaximum(maximum);
        r.setHostId(hostId);
        this.roomRepository.save(r);

        //룸 생성 - 호스트 권한 1
        Enrol e = new Enrol();
        e.setRoomId(r.getRoomId());
        e.setUserId(hostId);
        e.setAuth(1);
        this.enrolRepository.save(e);
    }
}
