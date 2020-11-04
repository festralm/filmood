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
    private String photoPath;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(int id, String username, String email,
                Date birthdate, String fullname, String photoPath) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.birthdate = birthdate;
        this.fullname = fullname;
        this.photoPath = photoPath;
    }
}
