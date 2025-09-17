package com.userManagement.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userManagement.service.UtilityService;

@RestController
@RequestMapping("/string")
public class UtilityController {
    @Autowired private UtilityService utilService;

    @PostMapping("/permutations")
    public Set<String> permutations(@RequestBody Map<String, String> body) {
        return utilService.generatePermutations(body.get("input"));
    }
}
