package dto;

import lombok.Data;

@Data
public class FilmWord {
    private int id;
    private int filmId;
    private int wordId;
    private int count;

    public FilmWord(int id, int filmId, int wordId, int count) {
        this.id = id;
        this.filmId = filmId;
        this.wordId = wordId;
        this.count = count;
    }
}
