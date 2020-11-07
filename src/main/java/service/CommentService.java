package service;

import dao.implementation.CommentDaoMySql;
import dao.interfaces.CommentDao;
import dto.Comment;
import exception.CouldNotAddCommentException;
import exception.IncorrectCommentException;
import exception.IncorrectFilmIdException;
import exception.IncorrectUserIdException;
import lombok.SneakyThrows;

public class CommentService {
    private CommentDao commentDao = new CommentDaoMySql();

    @SneakyThrows
    public boolean addComment(int filmId, int userId, String description) {
        if (filmId < 1) {
            throw new IncorrectFilmIdException();
        }
        if (userId < 1) {
            throw new IncorrectUserIdException();
        }
        if (description == null) {
            throw new IncorrectCommentException();
        }
        Comment comment = new Comment(filmId, userId, description);

        if (!commentDao.addComment(comment)) {
            throw new CouldNotAddCommentException();
        }
        return true;
    }

    public Comment[] geAllComments() {
        return commentDao.geAllComments();
    }
}
