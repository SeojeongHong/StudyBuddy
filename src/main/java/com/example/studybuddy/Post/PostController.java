package com.example.studybuddy.Post;

import com.example.studybuddy.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.access.prepost.PreAuthorize;
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
            model.addAttribute("postId", post.getId());
            return "postDetail";
        } else {
            return "notFoundPage";
        }
    }

    //게시글 작성
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/post/create")
    public String postCreate(@Valid PostForm postForm, BindingResult bindingResult, Principal principal) {
        String siteUser = this.userService.getUser(principal.getName());

        if (bindingResult.hasErrors()) {
            return "write";
        }
        this.postService.create(siteUser, postForm.getSubject(), postForm.getContent());
        return "redirect:/post/list";
    }

    //게시글 수정
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/modify/{id}")
    public String postModify(PostForm postForm, @PathVariable("id") Integer id, Principal principal) {
        Optional<Post> post = this.postService.getPost(id);
        if(!post.get().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        postForm.setSubject(post.get().getSubject());
        postForm.setContent(post.get().getContent());
        return "write";
    }

    //게시글 삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/delete/{id}")
    public String postDelete(Principal principal, @PathVariable("id") Integer id) {
        Optional<Post> post = this.postService.getPost(id);
        if (!post.get().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.postService.delete(post.get());
        return String.format("redirect:/post/detail/%s", post.get().getId());
    }

}
