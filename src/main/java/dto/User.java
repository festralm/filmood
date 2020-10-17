package dto;

import lombok.*;

import java.sql.*;
import java.util.Objects;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private Date birthdate;
    private String fullname;

    public User() {
    }

    /** new user for DB */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /** existing user from DB */
    public User(int id, String username, String password, String email, Date birthdate, String fullname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthdate = birthdate;
        this.fullname = fullname;
    }

    public static User getNullUser() {
        User res = new User();
        res.id = -1;
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                password == user.password &&
                username.equals(user.username) &&
                email.equals(user.email) &&
                birthdate.equals(user.birthdate) &&
                fullname.equals(user.fullname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, birthdate, fullname);
    }
}
