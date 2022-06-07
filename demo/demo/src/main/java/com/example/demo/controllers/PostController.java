package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.services.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/post")
@Api(value = "Post Resource for managing posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation(value = "Returns post with given id")
    @GetMapping("/id={id}")
    public Post getPostById(@PathVariable("id") int id) {
        return postService.getPostById(id);
    }

    @ApiOperation(value = "Returns list of posts from user with given id")
    @GetMapping("/userId={userId}")
    public List<Post> getPostByUserId(@PathVariable("userId") int userId) {
        return postService.getPostByUserId(userId);
    }

    @ApiOperation(value = "Loads new post from request body and adds it")
    @PostMapping
    public Post addNewPost(@RequestBody Post newPost) {
        return postService.addNewPost(newPost);
    }

    @ApiOperation(value = "Deletes post with given id")
    @DeleteMapping("id={id}")
    public Post deletePostById(@PathVariable("id") int id) {
        return postService.deletePostById(id);
    }

    @ApiOperation(value = "Updates a post with given id with passed parameters")
    @PutMapping("id={id}")
    public Post updatePostById(
            @PathVariable("id") int id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String body) {

        return postService.updatePost(id, title, body);
    }


}
