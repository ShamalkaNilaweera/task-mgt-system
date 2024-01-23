package org.example.service;

import org.example.controller.model.CreateUserRequest;
import org.example.controller.model.UpdateUserRequest;
import org.example.controller.model.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);

    UserResponse updateUser(Integer userId, UpdateUserRequest request);

    Integer deleteUser(Integer userId);

    List<UserResponse> getUsers();

    UserResponse getUser(Integer userId);
}
