package ua.com.drabchak.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class AdvertiseTranslation extends Translational {
    private static final int PRICE_PER_SECOND = 5;

    private String productName;

    public AdvertiseTranslation(LocalTime duration, String productName) {
        super(duration, duration.toSecondOfDay() * PRICE_PER_SECOND);
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "ADVERTISE {" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                '}';
    }
}
