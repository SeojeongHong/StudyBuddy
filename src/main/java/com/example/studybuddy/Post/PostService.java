package com.example.studybuddy.Post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getList() {
        return this.postRepository.findAll();
    }

    //게시글 작성
    public void create(String userId, String subject, String content) {
        Post q = new Post();
        q.setUserId(userId);
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        this.postRepository.save(q);
    }

    //게시글 수정
    public void modify(Post post, String subject, String content) {
        post.setSubject(subject);
        post.setContent(content);
        this.postRepository.save(post);
    }
    public Optional<Post> getPost(int id) {return this.postRepository.findById(id);}

    //게시글 삭제
    public void delete(Post post) {
        this.postRepository.delete(post);
    }
}