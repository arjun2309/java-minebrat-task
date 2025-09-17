package com.userManagement.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.userManagement.dto.LoginRequest;
import com.userManagement.dto.UserRegisterRequest;
import com.userManagement.dto.UserResponse;
import com.userManagement.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRegisterRequest req) {
        return service.register(req);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
        return service.login(req);
    }

    @GetMapping("/search")
    public Page<UserResponse> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String pinCode,
            @RequestParam(required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Pageable pageable) {
        return service.search(name, pinCode, startDate, endDate, pageable);
    }


}
