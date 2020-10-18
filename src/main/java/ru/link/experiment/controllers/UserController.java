package ru.link.experiment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.link.experiment.persist.model.User;
import ru.link.experiment.persist.repo.UserRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class UserController {

    private UserRepository userRepository;
    private AtomicLong atomicLong = new AtomicLong();

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/all")
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/add")
    public void addUsers() {
        User user = new User();
        user.setId(atomicLong.incrementAndGet() + 100);
        user.setUserName("custom" + atomicLong);
        user.setPassword("password" + atomicLong);
        user.setEmail("user@mail.com");
        user.setFirstName("user");
        user.setLastName("resu");

        userRepository.save(user);
    }
}
