package dto;

import lombok.Data;

import java.sql.Date;
import java.util.Objects;

@Data
public class Comment {
    private int id;
    private int filmId;
    private int userId;
    private String description;
    private Date datetime;

    public Comment() {
    }

    public Comment(int filmId, int userId, String description) {
        this.filmId = filmId;
        this.userId = userId;
        this.description = description;
    }

    public Comment(int id, int filmId, int userId, String description) {

        this.id = id;
        this.filmId = filmId;
        this.userId = userId;
        this.description = description;
    }
}
