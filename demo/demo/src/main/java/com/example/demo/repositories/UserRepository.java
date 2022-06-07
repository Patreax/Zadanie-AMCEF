package com.example.demo.repositories;

import com.example.demo.exceptions.UserIdNotFoundException;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private RestTemplate restTemplate;

    private List<User> userList = new LinkedList<>();

    private String userUrl = "https://jsonplaceholder.typicode.com/users";

    /**
     * Loads all users from external API adn stores them in list
     * @return
     */
    public List<User> loadAllUsers() {
        User[] users = restTemplate.getForObject(userUrl, User[].class);

        this.userList = Arrays.stream(users).toList();
        return userList;
    }

    /**
     * Checks if user with given id exists
     * @param userId
     * @throws UserIdNotFoundException
     */
    public void checkForUserId(int userId) throws UserIdNotFoundException {
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
