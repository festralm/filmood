package dao.interfaces;

import dto.Comment;

public interface CommentDao {
    boolean addComment(Comment comment);

    Comment[] geAllComments();
}
