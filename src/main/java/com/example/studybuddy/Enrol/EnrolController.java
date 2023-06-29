package com.example.studybuddy.Enrol;

import com.example.studybuddy.Post.PostForm;
import com.example.studybuddy.Room.Room;
import com.example.studybuddy.Room.RoomForm;
import com.example.studybuddy.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class EnrolController {
    private final EnrolService enrolService;
    private final UserService userService;
    @PostMapping("/joinRoom/{id}")
    public String joinRoom(@PathVariable("id") int roomId,Principal principal) {
        String siteUser = this.userService.getUser(principal.getName());
        try {
            this.enrolService.joinRoom(roomId, siteUser);
            return "redirect:/searchRoom/{id}";
        } catch (DuplicateKeyException e) {
            //중복 가입 시
            return "/err";
        }
    }
}
