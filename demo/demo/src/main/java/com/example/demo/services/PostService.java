package com.example.demo.services;

import com.example.demo.repositories.PostRepository;
import com.example.demo.exceptions.PostNotFoundException;
import com.example.demo.exceptions.UserIdNotFoundException;
import com.example.demo.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getPostById(int id) {
        try {
            return postRepository.getPostById(id);
        } catch (PostNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Post> getPostByUserId(int userId) {
        try {
            return postRepository.getPostByUserId(userId);
        } catch (PostNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Post updatePost(int id, String newTitle, String newBody) {
        try {
            Post post = postRepository.getPostById(id);
            post.setTitle(newTitle);
            post.setBody(newBody);
            return post;
        } catch (PostNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Post deletePostById(int id) {
        try {
            Post post = postRepository.getPostById(id);

            postRepository.removePost(post);

            Post deletedPost = post;
            deletedPost.setTitle("");
            deletedPost.setBody("");
            return deletedPost;
        } catch (PostNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Post addNewPost(Post newPost) {

        try {
            postRepository.chceckForUserId(newPost.getUserId());
        } catch (UserIdNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }

        postRepository.addNewPost(newPost);

        return newPost;
    }
}
