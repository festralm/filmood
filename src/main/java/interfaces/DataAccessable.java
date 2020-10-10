package interfaces;

public interface DataAccessable {
    UserInterface getById(int id);
//    Role getRoleByLoginPassword(final String login, final String passwordHash);
    UserInterface getUserByLoginPassword(final String login, final int passwordHash);
    boolean add(UserInterface user);
    boolean delete(UserInterface user);
    boolean isUserExist(final String login, final int passwordHash);
    boolean isLoginExist(final String login);
}
