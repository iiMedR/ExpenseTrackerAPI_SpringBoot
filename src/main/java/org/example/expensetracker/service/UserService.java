package org.example.expensetracker.service;

import org.example.expensetracker.dto.user.UserResponse;
import org.example.expensetracker.exception.UserNotFounException;
import org.example.expensetracker.model.User;
import org.example.expensetracker.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email =  authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFounException(email));
    }
}
