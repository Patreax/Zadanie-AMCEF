package com.example.demo.repositories;

import com.example.demo.exceptions.PostNotFoundException;
import com.example.demo.exceptions.UserIdNotFoundException;
import com.example.demo.models.Post;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.*;

//TODO make another repository for user endpoint
@Repository
public class PostRepository {
    @Autowired
    private RestTemplate restTemplate;
    private List<Post> postsList = new LinkedList<>();
    private List<User> userList = new LinkedList<>();
    private final String postUrl = "https://jsonplaceholder.typicode.com/posts";
    private final String userUrl = "https://jsonplaceholder.typicode.com/users";


    public List<Post> loadAllPosts() {
        Post[] posts = restTemplate.getForObject(postUrl, Post[].class);

//        this.postsList = Arrays.stream(posts).toList();
        List<Post> allPost = new LinkedList<>(Arrays.asList(posts));
        this.postsList = allPost;
        return postsList;
    }

    public List<User> loadAllUsers() {
        User[] users = restTemplate.getForObject(userUrl, User[].class);

        this.userList = Arrays.stream(users).toList();
        return userList;
    }

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

    public void addNewPost(Post newPost) {
        this.postsList.add(newPost);
    }

    public void removePost(Post post) {
        this.postsList.remove(post);
    }

    public void chceckForUserId(int userId) throws UserIdNotFoundException {
        List<User> users = loadAllUsers();
        boolean userIsPresent = false;

        for (User user : users) {
            if (user.getId() == userId)
                userIsPresent = true;
        }

        if (!userIsPresent)
            throw new UserIdNotFoundException("User for given post does not exist");
    }
}
