package ua.com.drabchak.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public abstract class Translational {
    protected LocalTime duration;

    protected int price;

    public boolean isPaid() {
        return price == 0;
    }
}
