package com.example.shawn.service;

import com.example.shawn.entity.User;
import com.example.shawn.exception.BusinessException;
import com.example.shawn.repository.UserRepository;
import com.example.shawn.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtUtil jwtUtil = new JwtUtil();

    public User register(String username, String password) {
        userRepository.findByUsername(username).ifPresent(u -> {
            throw new BusinessException("用户名已存在");
        });
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("用户不存在"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BusinessException("密码错误");
        }
        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}
