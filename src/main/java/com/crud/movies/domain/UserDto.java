package com.crud.movies.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {

    private int userId;
    private String userName;
    private String userEmail;
    private String password;
    private String userTokenKey;
    private boolean status;
}
