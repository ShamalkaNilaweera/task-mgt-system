package org.example.service;

import org.example.controller.model.CreateUserRequest;
import org.example.controller.model.UpdateUserRequest;
import org.example.controller.model.UserResponse;
import org.example.repo.UserRepository;
import org.example.repo.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    ModelMapper modelMapper;

    public UserServiceImpl() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setSkipNullEnabled(true);

        modelMapper.typeMap(UpdateUserRequest.class, User.class)
                .addMappings(mapper -> {
                    mapper.skip(User::setFirstName);
                    mapper.skip(User::setLastName);
                });

    }
    @Override
    public UserResponse createUser(CreateUserRequest request) {
        User user = userRepository.save(modelMapper.map(request, User.class));
        UserResponse response = modelMapper.map(user,UserResponse.class);
        return response;
    }

    @Override
    public UserResponse updateUser(Integer userId, UpdateUserRequest request) {
        if (userRepository.findById(userId).isPresent()){
            User user = userRepository.findById(userId).get();
            modelMapper.map(request, user);
            userRepository.save(user);
            UserResponse response = modelMapper.map(user, UserResponse.class);
            return response;
        }else {
            return null;
        }
    }

    @Override
    public Integer deleteUser(Integer userId) {
        if (userRepository.findById(userId).isPresent()){
            userRepository.deleteById(userId);
            return userId;
        }else {
            return null;
        }
    }

    @Override
    public List<UserResponse> getUsers() {
        List<UserResponse> users = new ArrayList<>();
        userRepository.findAll().forEach(
                user -> {
                    users.add(modelMapper.map(user, UserResponse.class));
                }
        );
        if (!users.isEmpty()){
            return users;
        } else {
            return null;
        }
    }

    @Override
    public UserResponse getUser(Integer userId) {
        if (userRepository.findById(userId).isPresent()){
            UserResponse response = modelMapper.map(
                    userRepository.findById(userId).get(), UserResponse.class);
            return response;
        }else {
            return null;
        }
    }
}
