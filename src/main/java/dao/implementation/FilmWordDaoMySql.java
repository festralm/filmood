package dao.implementation;

import dao.MySqlConnection;
import dao.interfaces.FilmWordDao;
import dto.*;
import service.*;

import java.sql.*;

public class FilmWordDaoMySql implements FilmWordDao {
    private final MySqlConnection connection = new MySqlConnection();

    @Override
    public FilmWord getFilmWordByFilmIdAndWordId(int filmId, int wordId) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id, count " +
                    "from film_word " +
                    "where film_word.film_id = ? and film_word.word_id = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1, filmId);
                preparedStatement.setInt(2, wordId);

                ResultSet resultSet = preparedStatement.executeQuery();

                int filmWordId = resultSet.getInt(1);
                int filmWordCount = resultSet.getInt(2);

                FilmWord filmWord = new FilmWord(filmWordId, filmId, wordId, filmWordCount);
                return filmWord;
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean incrementCount(int filmWordId, int filmWordCount) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "update film_word " +
                    "set count = ? " +
                    "where id = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1, filmWordCount + 1);
                preparedStatement.setInt(2, filmWordId);

                preparedStatement.executeUpdate();
            }

        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addNewFilmWord(int filmId, int wordId) {
        try (Connection con = connection.getNewConnection()) {

            String sql = "insert into film_word " +
                    "(film_id, word_id, count) " +
                    "values(?, ?, 1)";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1, filmId);
                preparedStatement.setInt(2, wordId);

                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addWordUserByIds(int filmId, String word) {
        try (Connection con = connection.getNewConnection()) {
            WordService wordService = new WordService();
            Word wordObj = wordService.getWordByName(word);
            wordService.incrementCount(wordObj.getId(), wordObj.getCount());

            FilmWord filmWord = getFilmWordByFilmIdAndWordId(filmId, wordObj.getId());
            if (filmWord != null) {
                incrementCount(filmWord.getId(), filmWord.getCount());
            } else {
                addNewFilmWord(filmId, wordObj.getId());
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return false;
    }


}
