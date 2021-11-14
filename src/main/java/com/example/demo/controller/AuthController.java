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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

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
            user.setAvt("https://firebasestorage.googleapis.com/v0/b/demoupload-d290c.appspot.com/o/avatar.jpg?alt=media&token=9ac8b329-207a-4c5b-9581-98d5269b160d");
        }
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}