package dev.ktb_hackathon.goorm.post.controller;

import dev.ktb_hackathon.goorm.post.entity.PostEntity;
import dev.ktb_hackathon.goorm.post.request.RegisterPostRequest;
import dev.ktb_hackathon.goorm.post.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post/{pageNumber}")
    public List<PostEntity> searchByPageNumber(@PathVariable int pageNumber) {
        return postService.searchByPageNumber(pageNumber);
    }

    @PostMapping("/post")
    public void register(RegisterPostRequest request) {
        postService.register(request.memberEmail(), request.latitude(), request.longitude(), request.image(),
                request.content(), request.location());
    }
}
