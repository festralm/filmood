package dao.implementation;

import dao.MySqlConnection;
import dao.interfaces.FilmDao;
import dto.*;
import service.FilmService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilmDaoMySql implements FilmDao {
    private final MySqlConnection connection = new MySqlConnection();

    @Override
    public Comment[] getCommentsByFilmId(int id) {
        List<Comment> comments = new ArrayList<>();
        try (Connection con = connection.getNewConnection()) {
            String sql = "select comment.film_id, comment.user_id," +
                    "comment.description " +
                    "from filmood.film " +
                    "inner join comment " +
                    "on film.id = comment.film_id " +
                    "where film.id = ? " +
                    "order by comment.datetime";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setInt(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int userId = resultSet.getInt(2);
                    String description = resultSet.getString(3);

                    comments.add(new Comment(id, userId, description));
                }
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return comments.toArray(new Comment[] {});
    }

    @Override
    public String[] getGenresByFilmId(int id) {
        List<String> genres = new ArrayList<>();
        try (Connection con = connection.getNewConnection()) {
            String sql = "select genre.name " +
                    "from filmood.film " +
                    "inner join film_genre " +
                    "on film.id = film_genre.film_id " +
                    "inner join genre " +
                    "on genre.id = film_genre.genre_id " +
                    "where film.id = ? " +
                    "order by genre.name";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setInt(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    genres.add(resultSet.getString(1));
                }
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return genres.toArray(new String[] {});
    }

    @Override
    public String[] getWordsByFilmId(int id) {
        List<String> words = new ArrayList<>();
        try (Connection con = connection.getNewConnection()) {
            String sql = "select word.name " +
                    "from filmood.film " +
                    "inner join film_word " +
                    "on film.id = film_word.film_id " +
                    "inner join word " +
                    "on word.id = film_word.word_id " +
                    "where film.id = ? " +
                    "order by word.name";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setInt(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    words.add(resultSet.getString(1));
                }
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return words.toArray(new String[] {});
    }

    @Override
    public String[] getCountriesByFilmId(int id) {
        List<String> countries = new ArrayList<>();
        try (Connection con = connection.getNewConnection()) {
            String sql = "select country.name " +
                    "from filmood.film " +
                    "inner join film_country " +
                    "on film.id = film_country.film_id " +
                    "inner join country " +
                    "on country.id = film_country.country_id " +
                    "where film.id = ? " +
                    "order by country.name";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setInt(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    countries.add(resultSet.getString(1));
                }
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return countries.toArray(new String[] {});
    }

    @Override
    public Film[] getAllFilms() {
        List<Film> films = new ArrayList<>();
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id, name, photo_path, start_year, description, " +
                    "finish_year " +
                    "from filmood.film " +
                    "order by film.name";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Film film = getFilmFromResultSet(resultSet);

                    if (film != null) {
                        films.add(film);
                    }
                }
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return films.toArray(new Film[] {});
    }

    @Override
    public Film getFilmByWord(String inputWord, int userId) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select film.id, film.name, film.photo_path, " +
                    "film.start_year, film.description, film.finish_year " +
                    "from filmood.film " +
                    "inner join film_word " +
                    "on film.id = film_word.film_id " +
                    "inner join word " +
                    "on film_word.word_id = word.id ";
            if (userId != -1) {
                sql += "where not exists (select film_user_watched.user_id as user_id" +
                        "from film_user_watched.user_id " +
                        "where user_id = ?) ";
            }
            sql +=  "where word.name = ? " +
                    "order by film_word.count desc " +
                    "limit 1";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setString(1, inputWord);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return getFilmFromResultSet(resultSet);
                }
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return null;
    }

    public Film getFilmFromResultSet(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String photoPath = resultSet.getString(3);
        int startYear = resultSet.getInt(4);
        String description = resultSet.getString(5);
        int finishYear = resultSet.getInt(6);

        return new Film(id, name, photoPath, startYear, finishYear, description);
    }
}
