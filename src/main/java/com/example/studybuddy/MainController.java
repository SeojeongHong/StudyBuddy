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
        return "post_list";
    }
    @GetMapping("/write")
    public String write() {
        return "write";
    }

    @GetMapping("/mystudy")
    public String mystudy() {
        return "mystudy";
    }

    @ResponseBody
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ResponseBody
    @GetMapping("/join")
    public String join() {
        return "join";
    }
}