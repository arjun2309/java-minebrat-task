package com.userManagement.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userManagement.dto.LoginRequest;
import com.userManagement.dto.UserRegisterRequest;
import com.userManagement.dto.UserResponse;
import com.userManagement.model.Address;
import com.userManagement.model.User;
import com.userManagement.repository.AddressRepository;
import com.userManagement.repository.UserRepository;

@Service
public class UserService {

    @Autowired 
    private UserRepository userRepo;

    @Autowired 
    private AddressRepository addrRepo;

    public UserResponse register(UserRegisterRequest req) {
        User user = new User();
        user.setUsername(req.getUsername());   // ✅ using getter
        user.setPassword(req.getPassword());
        user.setEmail(req.getEmail());
        user.setRegistrationDate(LocalDate.now());

        Address addr = new Address();
        addr.setStreet(req.getStreet());
        addr.setCity(req.getCity());
        addr.setPinCode(req.getPinCode());
        addr.setRegistrationDate(LocalDate.now());
        addr.setUser(user);

        user.setAddress(addr);
        userRepo.save(user);

        return toResponse(user);
    }

    public String login(LoginRequest req) {
        return userRepo.findByUsernameAndPassword(req.getUsername(), req.getPassword())  // ✅ using getter
                .isPresent() ? "Login success" : "Invalid credentials";
    }

    public Page<UserResponse> search(String name, String pin, LocalDate start,
            LocalDate end, Pageable pageable) {
return userRepo.search(name, pin, start, end, pageable)
.map(this::toResponse); // converts User → UserResponse
}

    private UserResponse toResponse(User u) {
        UserResponse res = new UserResponse();
        res.setUsername(u.getUsername());
        res.setEmail(u.getEmail());
        res.setRegistrationDate(u.getRegistrationDate());
        res.setPinCode(u.getAddress().getPinCode());
        return res;
    }
}
