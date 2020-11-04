//package service;
//
//import dao.implementation.CommentDaoMySql;
//import dao.interfaces.CommentDao;
//import dto.Comment;
//import exception.CouldntAddComment;
//
//public class CommentService {
//    private CommentDao commentDao = new CommentDaoMySql();
//
//    public boolean addComment(int film_id, int user_id, String description) throws CouldntAddComment {
//        Comment comment = new Comment(film_id, user_id, description);
//
//        if (!commentDao.addComment(comment)) {
//            throw new CouldntAddComment();
//        }
//        return true;
//    }
//
//    public Comment[] geAllComments() {
//        return commentDao.geAllComments();
//    }
//}
