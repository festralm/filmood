package models;

import interfaces.UserInterface;

public class User implements UserInterface {
    private int id;
    private String login;
    private int passwordHash;
    private String email;

    public User() {
    }

    public User(int id, String login, String email, String passwordHash) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.passwordHash = passwordHash.hashCode();
    }

    public static User getNullUser() {
        User res = new User();
        res.id = -1;
        return res;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
       this.email = email;
    }

    public int getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(int passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof User)) return false;
        User o = (User) obj;
        return o.login.equals(this.login) && o.passwordHash == this.passwordHash;
    }
}
