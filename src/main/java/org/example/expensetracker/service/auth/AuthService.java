package org.example.expensetracker.service.auth;

import org.example.expensetracker.dto.user.RegisterUserRequest;
import org.example.expensetracker.dto.user.UserResponse;
import org.example.expensetracker.exception.EmailAlreadyExistsException;
import org.example.expensetracker.model.User;
import org.example.expensetracker.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    public UserResponse signUp(RegisterUserRequest request) {
        User user = new User();
        if(userRepository.existsByEmail(request.email())){
            throw new EmailAlreadyExistsException(request.email());
        }
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);

        return toResponse(user);
    }


}
