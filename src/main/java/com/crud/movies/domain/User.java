package com.crud.movies.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQuery(name = "User.usersWithName",
        query = "SELECT * FROM USERS WHERE USER_NAME LIKE CONCAT('%', :KEYWORD , '%')",
        resultClass = User.class)

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

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "USER_ID", unique = true)
    public int getUserId() {
        return userId;
    }

    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    @Column(name = "USER_EMAIL")
    public String getUserEmail() {
        return userEmail;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    @Column(name = "USER_TOKEN")
    public String getUserTokenKey() {
        return userTokenKey;
    }

    @Column(name = "IS_STATUS")
    public boolean isStatus() {
        return status;
    }

    @OneToMany(targetEntity = Rent.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    public List<Rent> getRents() {
        return rents;
    }
}
