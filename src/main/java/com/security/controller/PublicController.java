package com.security.controller;


import com.security.dto.request.UserLoginRequestDTO;
import com.security.dto.request.UserRegisterRequestDTO;
import com.security.dto.response.UserRegisterResponseDTO;
import com.security.entity.User;
import com.security.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PublicController {

    private final UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> registerUser(@Valid @RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        try{
            log.info("User registration request received in PublicController register method");
            userService.registerUser(userRegisterRequestDTO);
            log.info("User registered successfully");
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new UserRegisterResponseDTO(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
            return new ResponseEntity<>(new UserRegisterResponseDTO("User registered successfully"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserRegisterResponseDTO> loginUser(@Valid @RequestBody UserLoginRequestDTO userLoginRequestDTO){
        try{
            userService.loginUser(userLoginRequestDTO);
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new UserRegisterResponseDTO(exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new UserRegisterResponseDTO("User logged in successfully"), HttpStatus.OK);
    }
}
