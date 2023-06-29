package com.example.studybuddy.Enrol;


import com.example.studybuddy.Room.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EnrolService {

    private final EnrolRepository enrolRepository;
    public Optional<Enrol> getAuth(Integer roomId, String siteUser) {return this.enrolRepository.getAuth(roomId,siteUser);};
    public void joinRoom(Integer roomId, String userId) {
        Enrol e = new Enrol();
        e.setRoomId(roomId);
        e.setUserId(userId);
        e.setAuth(0);
        this.enrolRepository.save(e);
    }
}
