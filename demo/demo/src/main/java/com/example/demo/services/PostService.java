package com.example.demo.services;

import com.example.demo.exceptions.PostIdAlreadyUsedException;
import com.example.demo.repositories.PostRepository;
import com.example.demo.exceptions.PostNotFoundException;
import com.example.demo.exceptions.UserIdNotFoundException;
import com.example.demo.models.Post;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    /**
     * Returns post with given id
     * Checks if the id is valid
     * @param id
     * @return
     */
    public Post getPostById(int id) {
        try {
            return postRepository.getPostById(id);
        } catch (PostNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Returns list of posts from user with given id
     * Checks if the user with given id is present
     * @param userId
     * @return
     */
    public List<Post> getPostByUserId(int userId) {
        try {
            return postRepository.getPostByUserId(userId);
        } catch (PostNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Updates title and body of the post with given id
     * Checks if the post with given id is present
     * @param id
     * @param newTitle
     * @param newBody
     * @return
     */
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

    /**
     * Searches for desired post and deletes it from the list of posts
     * @param id
     * @return
     */
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

    /**
     * Adds a new post to the list of posts
     * Checks if the user with given userName is present
     * Checks if posts with given id is not already present
     * @param newPost
     * @return
     */
    public Post addNewPost(Post newPost) {

        try {
            // checking for user
            userRepository.checkForUserId(newPost.getUserId());

            // checking for unique id
            postRepository.checkForExistingId(newPost);
        } catch (UserIdNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (PostIdAlreadyUsedException e){
            System.out.println(e.getMessage());
            return null;
        }

        postRepository.addNewPost(newPost);

        return newPost;
    }
}
