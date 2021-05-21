package com.crud.movies.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    private int userId;
    private String userName;
    private String userEmail;
    private String password;
    private String userTokenKey;
    private boolean status;
    private List<Rent> rents =new ArrayList<>();

    public User(int userId, String userName, String userEmail, String password, String userTokenKey, boolean status) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.userTokenKey = userTokenKey;
        this.status = status;
    }
}
