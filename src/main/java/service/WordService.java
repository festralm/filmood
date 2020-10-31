package service;

import dao.*;
import dto.*;

public class WordService {
    private WordDao wordDao = new WordDaoMySql();

    public Word getWordByWord(String word) {
        return wordDao.getWordByWord(word);
    }

    public Word getWordByWordId(int id) {

        return wordDao.getWordByWordId(id);
    }

}
