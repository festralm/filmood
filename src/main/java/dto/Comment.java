package dto;

import exception.IncorrectPhotoPathException;
import exception.IncorrectUserException;
import exception.IncorrectUserIdException;
import exception.IncorrectUsernameException;
import exception.main.IncorrectDataException;
import lombok.Data;
import lombok.SneakyThrows;
import service.UserService;

import java.sql.Date;
import java.util.Objects;

@Data
public class Comment {
    private int id;
    private int filmId;
    private int userId;
    private String description;
    private Date datetime;
    private String photoPath;
    private String username;

    public Comment() {
    }

    public Comment(int filmId, int userId, String description) {
        this.filmId = filmId;
        this.userId = userId;
        this.description = description;
    }

    public Comment(int id, int filmId, int userId, String description, Date datetime) {

        this.id = id;
        this.filmId = filmId;
        this.userId = userId;
        this.description = description;
        this.datetime = datetime;
    }

    public Comment(int userId, String description) {
        this.userId = userId;
        this.description = description;
    }

    @SneakyThrows
    public void addPhotoPathAndUsername(int userId) {
        if (userId < 1) {
            throw new IncorrectUserIdException();
        }
        UserService userService = new UserService();
        User user = userService.getUserByUserId(userId);
        if (user == null) {
            throw new IncorrectUserException();
        }
        String photoPath = user.getPhotoPath();
        String username = user.getUsername();

        if (photoPath == null) {
            this.photoPath = "";
        } else {
            this.photoPath = photoPath;
        }
        if (username == null) {
            throw new IncorrectUsernameException();
        }
        this.username = username;
    }
}
