package interfaces;

public interface UserInterface {
    int getId();
    String getLogin();
    String getEmail();
    int getPasswordHash();
//    Role getRole();
    void setId(int id);
    void setLogin(String login);
    void setEmail(String email);
    void setPasswordHash(int passwordHash);
//    void setRole(Role role);
}
