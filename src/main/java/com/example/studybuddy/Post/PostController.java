package com.example.studybuddy.Post;

import com.example.studybuddy.User.UserService;
import lombok.RequiredArgsConstructor;
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
public class PostController {

    private final PostService postService;
    private final UserService userService;
    //모든 게시글 조회
    @GetMapping("/post/list")
    public  String list(Model model){
        List<Post> postList = this.postService.getList();
        model.addAttribute("postList", postList);
        return "recruit";
    }
    //게시글 아이디로 조회
    @GetMapping("/post/detail/{id}")
    public String getPostDetail(@PathVariable("id") int id, Model model){
        Optional<Post> optionalPost = this.postService.getPost(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("postUserId", post.getUserId());
            model.addAttribute("postSubject", post.getSubject());
            model.addAttribute("postContent", post.getContent());
            return "postDetail";
        } else {
            return "notFoundPage";
        }
    }

    //게시글 작성
    @PostMapping("/post/create")
    public String postCreate(@Valid PostForm postForm, BindingResult bindingResult, Principal principal) {
        String siteUser = this.userService.getUser(principal.getName());

        if (bindingResult.hasErrors()) {
            return "post_form";
        }
        this.postService.create(siteUser, postForm.getSubject(), postForm.getContent());
        return "redirect:/post/list";
    }

}
