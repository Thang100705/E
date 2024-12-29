package com.example.test.controller;
import com.example.test.models.Posts;
import com.example.test.models.Users;
import com.example.test.services.PostService;
import com.example.test.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "http://localhost:3001") // Cho phép frontend truy cập
@RequestMapping("/api")
@AllArgsConstructor
public class ShowController {
    @Autowired
    private PostService postService;
    private UserService userService;


    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        Optional<Users> user = userService.getUserId(id);
        if (user.isPresent()) {
           userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/users")
    public ResponseEntity<Users> getUseByUserName(@RequestParam String username) {
        Optional<Users> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Users> getUseByUserName( @PathVariable(required = false) Long id) {
        Optional<Users> users = userService.getUserId(id);
        if (users.isPresent()) {
            return new ResponseEntity<>(users.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
    @PutMapping("/users/{id}")
    public ResponseEntity<Users>UpdateUsers(@Valid @PathVariable Long id, @RequestBody Users user) {
        try {
            Users update = userService.update(id,user);
            return ResponseEntity.ok(update);
        } catch (Exception e) {
            return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR)).build();
        }
    }


}



