package dao;

import dto.*;

public interface FriendDao {
    User getFriendByUserId(int user_id);
}