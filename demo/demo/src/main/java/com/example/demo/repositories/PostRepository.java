package com.example.demo.repositories;

import com.example.demo.exceptions.PostIdAlreadyUsedException;
import com.example.demo.exceptions.PostNotFoundException;
import com.example.demo.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Repository
public class PostRepository {
    @Autowired
    private RestTemplate restTemplate;

    private List<Post> postsList = new LinkedList<>();

    private final String postUrl = "https://jsonplaceholder.typicode.com/posts";

    /**
     * Loads all posts from external API and stores them in linkedList
     * @return
     */
    public List<Post> loadAllPosts() {
        Post[] posts = restTemplate.getForObject(postUrl, Post[].class);

        List<Post> allPost = new LinkedList<>(Arrays.asList(posts));
        this.postsList = allPost;
        return postsList;
    }

    /**
     * Returns post with given id
     * @param id
     * @return
     * @throws PostNotFoundException
     */
    public Post getPostById(int id) throws PostNotFoundException {
        List<Post> posts = loadAllPosts();
        boolean isPresent = false;

        for (Post p : posts) {
            if (p.getId() == id) {
                isPresent = true;
                return p;
            }
        }

        if (!isPresent)
            throw new PostNotFoundException("Post with given id not found");
        return null;
    }

    /**
     * Returns list of posts associated to user with given id
     * @param userId
     * @return
     * @throws PostNotFoundException
     */
    public List<Post> getPostByUserId(int userId) throws PostNotFoundException {
        List<Post> posts = loadAllPosts();
        ArrayList<Post> postsByUserId = new ArrayList<>();

        for (Post p : posts) {
            if (p.getUserId() == userId)
                postsByUserId.add(p);
        }

        if (postsByUserId.isEmpty())
            throw new PostNotFoundException("Post with given userId not found");
        else
            return postsByUserId;
    }

    /**
     * Adds a new post
     * @param newPost
     */
    public void addNewPost(Post newPost) {
        this.postsList.add(newPost);
    }

    /**
     * Removes a selected post
     * @param post
     */
    public void removePost(Post post) {
        this.postsList.remove(post);
    }

    /**
     * Checks if id of a new post is unique
     * @param newPost
     * @throws PostIdAlreadyUsedException
     */
    public void checkForExistingId(Post newPost) throws PostIdAlreadyUsedException {
        this.postsList = loadAllPosts();

        for (Post post : postsList) {
            if (post.getId() == newPost.getId())
                throw new PostIdAlreadyUsedException("Post with given id already exists");
        }
    }
}
