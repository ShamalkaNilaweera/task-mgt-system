package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.controller.model.CreateUserRequest;
import org.example.controller.model.LoginRequest;
import org.example.controller.model.UpdateUserRequest;
import org.example.controller.model.UserResponse;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Operation(summary = "Create a user")
    @ApiResponse(responseCode = "201", description = "User added sucessfully", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponse.class))
    })
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                userService.createUser(request));
    }

    @Operation(summary = "Update a user")
    @ApiResponse(responseCode = "201", description = "User updated sucessfully", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponse.class))
    })
    @PutMapping(path = "/{userId}")
    public ResponseEntity<?> editUser(@PathVariable("userId") Integer userId, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                userService.updateUser(userId, request));
    }

    @Operation(summary = "Delete a user")
    @ApiResponse(responseCode = "201", description = "User deleted sucessfully", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Integer.class))
    })
    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.deleteUser(userId));
    }

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "201", description = "Users retrieved sucessfully", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Integer.class))
    })
    @GetMapping
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.getUsers());
    }

    @Operation(summary = "Get user by Id")
    @ApiResponse(responseCode = "201", description = "User retrieved sucessfully", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Integer.class))
    })
    @GetMapping(path = "/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.getUser(userId));
    }
}
