package com.userManagement.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {

    public Set<String> generatePermutations(String input) {
        return permute("", input, new HashSet<>());
    }

    private Set<String> permute(String prefix, String remaining, Set<String> result) {
        if (remaining.isEmpty()) result.add(prefix);
        else {
            for (int i = 0; i < remaining.length(); i++) {
                permute(prefix + remaining.charAt(i),
                        remaining.substring(0, i) + remaining.substring(i + 1),
                        result);
            }
        }
        return result;
    }
}
