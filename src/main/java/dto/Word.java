package dto;

import lombok.Data;

@Data
public class Word {
    private int id;
    private String word;
    private int count;

    public Word() {
    }

    public Word(int id, String word, int count) {
        this.id = id;
        this.word = word;
        this.count = count;
    }
}
