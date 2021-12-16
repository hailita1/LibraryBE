package com.example.demo.controller;

import com.example.demo.model.auth.JwtResponse;
import com.example.demo.model.auth.User;
import com.example.demo.service.JwtService;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUserName(user.getUserName());

        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), currentUser.getPhone(), currentUser.getEmail(), currentUser.getUserName(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Iterable<User> listUser = userService.findAll();
        for (User currentUser : listUser) {
            if (currentUser.getUserName().equals(user.getUserName())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        if (user.getAvt() == null) {
            user.setAvt("defaul.jpg");
        }
        if (user.getCatalog().equals("Sinh viên")) {
            user.setUserName(user.getStudentCode());
        } else {
            user.setUserName(user.getEmail());
        }

        String password = "Abcd123";
        user.setPassword(passwordEncoder.encode(password));
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/accounts")
    public ResponseEntity<Iterable<User>> getAllUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }


    @PostMapping("/accounts")
    public ResponseEntity<User> getUser(@RequestBody Long user) {
        Optional<User> userOptional = userService.findById(user);
        return userOptional.map(user1 -> new ResponseEntity<>(user1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Optional<User> userOptional = userService.findById(user.getId());
        return userOptional.map(user1 -> {
            user1.setId(user1.getId());
            user1.setFullName(user.getFullName());
            user1.setCatalog(user.getCatalog());
            user1.setEmail(user.getEmail());
            user1.setPhone(user.getPhone());
            user1.setAddress(user.getAddress());
            user1.setGender(user.getGender());
            user1.setStudentCode(user.getStudentCode());
            user1.setAvt(user.getAvt());
            userService.save(user1);
            return new ResponseEntity<>(user1, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<User> updatePassword(@RequestBody User user) {
        Optional<User> userOptional = userService.findById(user.getId());
        return userOptional.map(user1 -> {
            user1.setId(user1.getId());
            user1.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user1);
            return new ResponseEntity<>(user1, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> {
            userService.remove(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/deleteList")
    public ResponseEntity deleteListCategory(@RequestBody List<Long> id) {
        userService.deleteList(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}