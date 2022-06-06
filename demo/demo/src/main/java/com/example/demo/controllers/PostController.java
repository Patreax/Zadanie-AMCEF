package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/id={id}")
    public Post getPostById(@PathVariable("id") int id) {
        return postService.getPostById(id);
    }

    @GetMapping("/userId={userId}")
    public List<Post> getPostByUserId(@PathVariable("userId") int userId) {
        return postService.getPostByUserId(userId);
    }

    @PostMapping
    public Post addNewPost(@RequestBody Post newPost) {
        return postService.addNewPost(newPost);
    }

    @DeleteMapping("id={id}")
    public Post deletePostById(@PathVariable("id") int id) {
        return postService.deletePostById(id);
    }

    @PutMapping("id={id}")
    public Post updatePostById(
            @PathVariable("id") int id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String body) {

        return postService.updatePost(id, title, body);
    }


}
