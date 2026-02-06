package com.security.service.impl;


import com.security.dto.request.UserLoginRequestDTO;
import com.security.dto.request.UserRegisterRequestDTO;
import com.security.entity.User;
import com.security.repository.UserRepository;
import com.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserRegisterRequestDTO userRegisterRequestDTO) throws RuntimeException {
        if(userRepository.findByEmail(userRegisterRequestDTO.getEmail()).isPresent()) {
          throw new RuntimeException("USER_ALREADY_EXIST");
        }
        User user=userRegisterRequestDTO.convertToUser();
        user.setPassword(passwordEncoder.encode(userRegisterRequestDTO.getPassword()));
        log.info("User encoded password: {}", user.getPassword());
        userRepository.save(user);
    }

    @Override
    public void loginUser(UserLoginRequestDTO userLoginRequestDTO) throws RuntimeException {
        User user = userRepository.findByEmail(userLoginRequestDTO.getEmail()).orElseThrow(() -> new RuntimeException(
                "USER_DOES_NOT_EXIST"));
        if (!passwordEncoder.matches(
                userLoginRequestDTO.getPassword(),
                user.getPassword()
        )) {
            throw new RuntimeException("INVALID_PASSWORD");
        }
    }
}

