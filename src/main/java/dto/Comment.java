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

    public Comment(int id, int filmId, int userId, String description, Date datetime) {

        this.id = id;
        this.filmId = filmId;
        this.userId = userId;
        this.description = description;
        this.datetime = datetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id &&
                filmId == comment.filmId &&
                userId == comment.userId &&
                Objects.equals(description, comment.description) &&
                Objects.equals(datetime, comment.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filmId, userId, description, datetime);
    }
}
