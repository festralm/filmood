package service;

import dao.*;
import dto.Comment;
import dto.User;
import exception.CouldntAddComment;
import exception.CouldntAddUser;
import exception.PasswordIsEmpty;
import exception.UsernameIsEmpty;

public class CommentService {
    private CommentDao commentDao = new CommentDaoMySql();

    public boolean addComment(int film_id, int user_id, String description) throws CouldntAddComment {
        Comment comment = new Comment(film_id, user_id, description);

        if (!commentDao.addComment(comment)) {
            throw new CouldntAddComment();
        }
        return true;
    }

    public Comment[] geAllComments() {
        return commentDao.geAllComments();
    }
}
