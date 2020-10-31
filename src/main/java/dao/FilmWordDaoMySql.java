package dao;

import dto.*;
import service.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FilmWordDaoMySql implements FilmWordDao {
    private final MySqlConnection connection = new MySqlConnection();

    @Override
    public Film getFilmByWordId(int wordId) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id, film_id, word_id, count " +
                    "from filmood.film_word " +
                    "where word_id = ? " +
                    "order by count desc " +
                    "limit 1";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setInt(1, wordId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int film_id = resultSet.getInt(2);

                    FilmService filmService = new FilmService();

                    return filmService.getFilmById(film_id);
                }
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Word[] getWordsByFilmId(int filmId) {
        List<Integer> wordIds = new ArrayList<>();
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id, film_id, word_id, count " +
                    "from filmood.film_word " +
                    "where film_id = ? ";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setInt(1, filmId);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int wordId = resultSet.getInt(3);

                    wordIds.add(wordId);
                }
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        if (wordIds.isEmpty()) {
            return null;
        } else {
            Word[] res = new Word[wordIds.size()];
            WordService wordService = new WordService();

            for (int i = 0; i < wordIds.size(); i++) {
                res[i] = wordService.getWordByWordId(wordIds.get(i));
            }

            return res;
        }

    }


}
