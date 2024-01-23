package org.example.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Integer userId;
    private String firstName;
    private String lastName;
    private Integer age;
    private String role;
    private String userName;
}
