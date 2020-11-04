package service;

import dao.implementation.WordDaoMySql;
import dao.interfaces.WordDao;
import dto.*;

public class WordService {
    private WordDao wordDao = new WordDaoMySql();

    public String[] getAllWords() {
        return wordDao.getAllWords();
    }

    public boolean isWordExist(String word) {
        return wordDao.isWordExist(word
                .replaceAll(" ", "")
                .toLowerCase());
    }

}
