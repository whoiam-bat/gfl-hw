package ua.com.drabchak.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Souvenir {

    @Min(value = 1, message = "id must be greater than 1")
    private int id;

    @NotEmpty
    private String souvenirName;

    private Manufacturer manufacturer;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate date;

    @Min(value = 1, message = "price must be real")
    @NotNull
    private BigDecimal price;

    private int manufacturerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Souvenir souvenir = (Souvenir) o;

        if (id != souvenir.id) return false;
        if (manufacturerId != souvenir.manufacturerId) return false;
        if (!souvenirName.equals(souvenir.souvenirName)) return false;
        if (!Objects.equals(manufacturer, souvenir.manufacturer))
            return false;
        if (!date.equals(souvenir.date)) return false;
        return price.equals(souvenir.price);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + souvenirName.hashCode();
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + date.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + manufacturerId;
        return result;
    }

    @Override
    public String toString() {
        return "{ TITLE = " + souvenirName +
                " MANUFACTURER = " + manufacturer.getCompanyName() +
                " DATE = " + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                " PRICE = " + price + " }";
    }
}
