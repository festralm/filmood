package dao.interfaces;

import dto.*;

public interface WordDao {
    String[] getAllWords();

    boolean isWordExist(String name);

    boolean incrementCount(int id, int count);

    Word getWordByName(String word);
}