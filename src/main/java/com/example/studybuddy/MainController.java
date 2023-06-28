package com.example.studybuddy;

import com.example.studybuddy.Post.Post;
import com.example.studybuddy.Post.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    private PostService postService;

    @GetMapping("/recruit")
    public  String list(Model model){
        List<Post> postList = this.postService.getList();
        model.addAttribute("postList", postList);
        return "recruit";
    }
    @GetMapping("/write")
    public String write() {
        return "write";
    }

    @ResponseBody
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }
}