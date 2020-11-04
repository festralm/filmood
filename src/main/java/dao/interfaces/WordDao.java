package dao.interfaces;

import dto.*;

public interface WordDao {
    String[] getAllWords();

    boolean isWordExist(String name);
}