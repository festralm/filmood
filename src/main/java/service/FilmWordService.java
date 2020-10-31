package service;

import dao.*;
import dto.*;

public class FilmWordService {
    private FilmWordDao filmWordDao = new FilmWordDaoMySql();

    public Film getFilmByWord(String inputWord) {
        WordService wordService = new WordService();

        Word word = wordService.getWordByWord(inputWord);

        int word_id = word.getId();

        return filmWordDao.getFilmByWordId(word_id);
    }
    public Word[] getWordsByFilmId(int id) {
        return filmWordDao.getWordsByFilmId(id);
    }
}
