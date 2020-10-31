package dto;

import lombok.Data;

import java.util.Objects;

@Data
public class Word {
    private int id;
    private String word;
    private int count;

    public Word() {
    }

    /** existing film from DB */
    public Word(int id, String word, int count) {
        this.id = id;
        this.word = word;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return id == word1.id &&
                count == word1.count &&
                word.equals(word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, count);
    }
}
