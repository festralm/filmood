package service;

import dao.implementation.WordDaoMySql;
import dao.interfaces.WordDao;
import dto.*;
import exception.IncorrectWordException;
import lombok.SneakyThrows;

public class WordService {
    private WordDao wordDao = new WordDaoMySql();

    @SneakyThrows
    public String[] getAllWords() {
        String[] words = wordDao.getAllWords();
        if (words == null) {
            throw new IncorrectWordException();
        }
        return words;
    }

    @SneakyThrows
    public boolean isWordExist(String word) {
        if (word == null) {
            throw new IncorrectWordException();
        }
        boolean exists = wordDao.isWordExist(word
                .replaceAll("/  +/g", " ")
                .replace("/[^A-Za-zА-Яа-яЁё]/g", "")
                .trim()
                .toLowerCase());
        return exists;
    }

    @SneakyThrows
    public boolean incrementCount(int id, int count) {
        if (id < 1) {
            throw new IncorrectWordException();
        }

        return wordDao.incrementCount(id, count);
    }

    @SneakyThrows
    public Word getWordByName(String word) {
        if (word == null) {
            throw new IncorrectWordException();
        }

        Word wordObj = wordDao.getWordByName(word);

        if (wordObj == null) {
            throw new IncorrectWordException();
        }
        return wordObj;
    }
}
