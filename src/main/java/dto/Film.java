package dto;

import lombok.Data;

import java.util.*;

@Data
public class Film {
    private int id;
    private String name;
    private String photoPath;
    private int startYear;
    private int finishYear;
    private String description;
    private Comment[] comments;
    private String[] countries;
    private String[] genres;
    private String[] words;

    public Film() {
    }

    public Film(int id, String name, String photoPath, int startYear,
                int finishYear, String description) {
        this.id = id;
        this.name = name;
        this.photoPath = photoPath;
        this.startYear = startYear;
        this.finishYear = finishYear;
        this.description = description;
    }
}
