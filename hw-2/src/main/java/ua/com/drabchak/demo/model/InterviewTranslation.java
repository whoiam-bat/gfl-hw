package ua.com.drabchak.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class InterviewTranslation extends Translational {

    public static final int PRICE_PER_MINUTE = 30;

    private String respondentName;

    public InterviewTranslation(LocalTime duration, String respondentName) {
        super(duration, duration.toSecondOfDay() * PRICE_PER_MINUTE);
        this.respondentName = respondentName;
    }

    @Override
    public String toString() {
        return "INTERVIEW {" +
                "respondentName='" + respondentName + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                '}';
    }
}
