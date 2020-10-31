package dao;

import dto.*;

public interface WordDao {
    Word getWordByWord(String word);
    Word getWordByWordId(int id);
}