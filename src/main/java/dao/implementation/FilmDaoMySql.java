package dao.implementation;

import dao.MySqlConnection;
import dao.interfaces.FilmDao;
import dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDaoMySql implements FilmDao {
    private final MySqlConnection connection = new MySqlConnection();
    @Override
    public String[] getGenresByFilmId(int id) {
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

                List<String> genres = new ArrayList<>();
                while (resultSet.next()) {
                    genres.add(resultSet.getString(1));
                }
                return genres.toArray(new String[] {});
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Film[] getAllFilms() {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id, name, photo_path, start_year, description, " +
                    "finish_year " +
                    "from filmood.film " +
                    "order by film.name";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                ResultSet resultSet = preparedStatement.executeQuery();

                List<Film> films = new ArrayList<>();
                while (resultSet.next()) {
                    Film film = getFilmFromResultSet(resultSet);

                    if (film != null) {
                        films.add(film);
                    }
                }
                return films.toArray(new Film[] {});
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Film getFilmByWord(String inputWord) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select film.id, film.name, film.photo_path, " +
                    "film.start_year, film.description, film.finish_year " +
                    "from filmood.film " +
                    "inner join film_word " +
                    "on film.id = film_word.film_id " +
                    "inner join word " +
                    "on film_word.word_id = word.id " +
                    "and word.name = ? " +
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

    @Override
    public Film getFilmById(int filmId) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id, name, photo_path, start_year, description, finish_year " +
                    "from filmood.film " +
                    "where id = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setInt(1, filmId);

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

    @Override
    public String[] getCountriesByFilmId(int id) {
        try (Connection con = connection.getNewConnection()) {
            List<String> result = new ArrayList<>();
            String sql1 = "select country.name " +
                    "from filmood.country " +
                    "inner join film_country " +
                    "on country.id = film_country.country_id " +
                    "inner join film " +
                    "on film_country.film_id = film.id " +
                    "where film.id = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql1)){
                preparedStatement.setInt(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String name = resultSet.getString(1);

                    result.add(name);
                }
                return result.toArray(new String[] {});
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Comment[] geCommentsByFilmId(int id) {
        try (Connection con = connection.getNewConnection()) {
            List<Comment> result = new ArrayList<>();
            String sql1 = "select comment.user_id, comment.description, comment.datetime " +
                    "from filmood.comment " +
                    "where film_id = ? " +
                    "order by datetime desc";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql1)){
                preparedStatement.setInt(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int userId = resultSet.getInt(1);
                    String description = resultSet.getString(2);
                    Comment comment = new Comment(userId, description);
                    comment.addPhotoPathAndUsername(userId);
                    result.add(comment);
                }
                return result.toArray(new Comment[] {});
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Film getRandomFilm() {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select film.id, film.name, film.photo_path, " +
                    "film.start_year, film.description, film.finish_year " +
                    "from filmood.film " +
                    "order by rand() " +
                    "limit 1";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
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

    @Override
    public Film getFilmByWordAndUserId(String inputWord, int userId) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select film.id, film.name, film.photo_path, " +
                    "film.start_year, film.description, film.finish_year " +
                    "from filmood.film " +
                    "inner join filmood.film_word " +
                    "on film.id = film_word.film_id " +
                    "inner join filmood.word " +
                    "on film_word.word_id = word.id " +
                    "left join filmood.film_user_watched " +
                    "on film.id = film_user_watched.film_id " +
                    "where film_user_watched.user_id is null " +
                    "and word.name = ? " +
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
