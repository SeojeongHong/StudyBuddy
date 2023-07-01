package com.example.studybuddy.Room;

import com.example.studybuddy.Enrol.Enrol;
import com.example.studybuddy.Enrol.EnrolService;
import com.example.studybuddy.Post.Post;
import com.example.studybuddy.Post.PostForm;
import com.example.studybuddy.User.SiteUser;
import com.example.studybuddy.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class RoomController {
    @Autowired
    private final RoomService roomService;
    @Autowired
    private final UserService userService;
    @Autowired
    private final EnrolService enrolService;
    @Autowired
    private final RoomRepository roomRepository;

    //나의 스터디 페이지
    @GetMapping("/mystudy")
    public  String list(Model model, Principal principal){
        String siteUser = this.userService.getUser(principal.getName());
        List<Room> roomList = this.roomService.getMyList(siteUser);
        model.addAttribute("roomList", roomList);
        return "mystudy";
    }

    @GetMapping("/studyroom/{id}")
    public String getStudyRoom(@PathVariable("id") int roomId, Model model, Principal principal) {
        String siteUser = this.userService.getUser(principal.getName());
        Optional<Room> optionalRoom = this.roomService.getRoom(roomId);
        Optional<Enrol> optionalEnrol = this.enrolService.getAuth(roomId,siteUser);
        if (optionalRoom.isPresent() && optionalEnrol.isPresent()) {
            Room room = optionalRoom.get();
            Enrol enrol = optionalEnrol.get();
            model.addAttribute("roomName", room.getRoomName());
            model.addAttribute("roomContent", room.getRoomContent());
            model.addAttribute("roomId", room.getRoomId());
            model.addAttribute("roomAuth", enrol.getAuth());
            return "roomMain";
        } else {
            return "notFoundPage";
        }
    }

    @GetMapping("/searchRoom/{id}")
    public String getRoomRes(@PathVariable("id") int roomId, Model model){
        Optional<Room> optionalRoom = this.roomService.getRoom(roomId);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            model.addAttribute("roomName", room.getRoomName());
            model.addAttribute("roomContent", room.getRoomContent());
            model.addAttribute("roomId", room.getRoomId());
            return "searchRoom";
        } else {
            return "notFoundPage";
        }
    }


    //스터디룸 생성
    @GetMapping("/create")
    public String create() {
        return "makeroom";
    }
    @PostMapping("/room/create")
    public String roomCreate(@Valid RoomForm roomForm, BindingResult bindingResult, Principal principal) {
        String siteUser = this.userService.getUser(principal.getName());

        if (bindingResult.hasErrors()) {
            return "room_form";
        }
        this.roomService.create(roomForm.getRoomName(), roomForm.getRoomContent(), roomForm.getMaximum(), siteUser);
        return "redirect:/mystudy";
    }

}
