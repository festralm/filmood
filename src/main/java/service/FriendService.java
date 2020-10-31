package service;

import dao.*;
import dto.*;

public class FriendService {
    private FriendDao friendDao = new FriendDaoMySql();

    public User getFriendByUserId(int id) {
        return friendDao.getFriendByUserId(id);
    }
}
