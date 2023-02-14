package ua.com.drabchak.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer {

    @Min(value = 1, message = "id must be greater than 1")
    private int id;

    @NotEmpty(message = "Company name shouldn't be empty!")
    private String companyName;

    @NotEmpty(message = "State shouldn't be empty!")
    private String state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manufacturer that = (Manufacturer) o;

        if (id != that.id) return false;
        if (!companyName.equals(that.companyName)) return false;
        return state.equals(that.state);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + companyName.hashCode();
        result = 31 * result + state.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{ COMPANY = " + companyName +
                " STATE = " + state + " }";
    }
}
