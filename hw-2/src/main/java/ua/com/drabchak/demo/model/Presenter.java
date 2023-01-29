package ua.com.drabchak.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Presenter {

    private String name;

    private int experience;

    private String resume;


    @Override
    public String toString() {
        return "Presenter{" +
                " name='" + name + '\'' +
                ", experience=" + experience +
                ", resume='" + resume + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Presenter presenter = (Presenter) o;

        if (experience != presenter.experience) return false;
        return name.equals(presenter.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + experience;
        result = 31 * result + (resume != null ? resume.hashCode() : 0);
        return result;
    }
}
