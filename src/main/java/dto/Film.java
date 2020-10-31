package dto;

import lombok.Data;
import service.FilmService;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Data
public class Film {
    private int id;
    private String name;
    private String photo_path;
    private int year;
    private String description;
    private Country[] countries;
    private Genre[] genres;
    private Word[] words;

    public Film() {
    }

    public Film(int id, String name, String photo_path, int year,
                String description, Country[] countries, Genre[] genres, Word[] words) {
        this.id = id;
        this.name = name;
        this.photo_path = photo_path;
        this.year = year;
        this.description = description;
        this.countries = countries;
        this.genres = genres;
        this.words = words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id == film.id &&
                year == film.year &&
                name.equals(film.name) &&
                Objects.equals(photo_path, film.photo_path) &&
                Objects.equals(description, film.description) &&
                Arrays.equals(countries, film.countries) &&
                Arrays.equals(genres, film.genres) &&
                Arrays.equals(words, film.words);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, photo_path, year, description);
        result = 31 * result + Arrays.hashCode(countries);
        result = 31 * result + Arrays.hashCode(genres);
        result = 31 * result + Arrays.hashCode(words);
        return result;
    }

    public static Film[] getFilmsByIds(List<Integer> ids) {
        Film[] res = new Film[ids.size()];
        FilmService filmService = new FilmService();

        for (int i = 0; i < ids.size(); i++) {
            res[i] = filmService.getFilmById(ids.get(i));
        }

        return res;
    }
}
