package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    // REGISTER (SIGNUP)
    @PostMapping("/signup")
    public String signup(@RequestBody User user) {

    	
    	    Optional<User> existing = repo.findByUsername(user.getUsername());

    	    if(existing.isPresent()){
    	        return "User already exists";
    	    }

    	    repo.save(user); // role comes from frontend
    	    return "User registered successfully";
    	
    	
    }

    // LOGIN
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:5173")
    public String login(@RequestBody User user) {

        Optional<User> existing = repo.findByUsername(user.getUsername());

        if(existing.isEmpty()){
            return "User not found";
        }

        User dbUser = existing.get();

        if(!dbUser.getPassword().equals(user.getPassword())){
            return "Invalid password";
        }

        return "Login successful - Role: " + dbUser.getRole();
    }

    // GET ALL USERS (optional)
    @GetMapping("/all")
    public java.util.List<User> getUsers(){
        return repo.findAll();
    }
}