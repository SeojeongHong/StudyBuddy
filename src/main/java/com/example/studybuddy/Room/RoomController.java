package com.example.studybuddy.Room;

import com.example.studybuddy.Post.Post;
import com.example.studybuddy.Post.PostForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class RoomController {
    private final RoomService roomService;

    //나의 스터디 페이지
    @GetMapping("/mystudy")
    public  String list(Model model){
        List<Room> roomList = this.roomService.getList();
        model.addAttribute("roomList", roomList);
        return "mystudy";
    }

    //스터디룸 생성
    @GetMapping("/create")
    public String create() {
        return "makeroom";
    }
    @PostMapping("/room/create")
    public String roomCreate(@Valid RoomForm roomForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "room_form";
        }
        this.roomService.create(roomForm.getRoomName(), roomForm.getRoomContent(), roomForm.getMaximum());
        return "redirect:/mystudy";
    }

}
