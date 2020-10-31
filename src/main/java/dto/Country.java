package dto;

import lombok.Data;

import java.util.Objects;

@Data
public class Country {
    private int id;
    private String country;

    public Country(int id, String country) {
        this.id = id;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country1 = (Country) o;
        return id == country1.id &&
                country.equals(country1.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country);
    }
}
