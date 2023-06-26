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

    @GetMapping("/create")
    public String create() {
        return "makeroom";
    }
    //모든 게시글 조회
    @GetMapping("/room/list")
    public  String list(Model model){
        List<Room> roomList = this.roomService.getList();
        model.addAttribute("roomList", roomList);
        return "room_list";
    }
    @PostMapping("/room/create")
    public String roomCreate(@Valid RoomForm roomForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "room_form";
        }
        this.roomService.create(roomForm.getRoomName(), roomForm.getRoomContent());
        return "redirect:/room/list";
    }

}
