package com.example.AtharamanBackend1.dto;


import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
//    private Role role;
//    private List<Token> tokens;

}
