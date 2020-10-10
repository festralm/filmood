package interfaces;

public interface UserInterface {
    int getId();
    String getLogin();
    int getPasswordHash();
//    Role getRole();
    void setId(int id);
    void setLogin(String login);
    void setPasswordHash(int passwordHash);
//    void setRole(Role role);
}
