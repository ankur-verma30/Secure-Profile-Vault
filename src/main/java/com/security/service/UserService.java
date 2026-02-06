package com.security.service;

import com.security.dto.request.UserLoginRequestDTO;
import com.security.dto.request.UserRegisterRequestDTO;
import com.security.entity.User;

public interface UserService {

    public void registerUser(UserRegisterRequestDTO userRegisterRequestDTO) throws RuntimeException;

    public void loginUser(UserLoginRequestDTO userLoginRequestDTO) throws RuntimeException;
}
