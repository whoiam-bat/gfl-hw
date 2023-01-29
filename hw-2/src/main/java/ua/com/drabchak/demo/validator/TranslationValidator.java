package ua.com.drabchak.demo.validator;

import ua.com.drabchak.demo.model.Translational;

import java.time.LocalTime;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class TranslationValidator {
    public boolean isEpisodeCanBeHeldInTranslation(LocalTime translationDuration, LocalTime episodeDuration) {
        return translationDuration.toSecondOfDay() - episodeDuration.toSecondOfDay() >= 0;
    }

    public boolean isPaidContentDoesNotExceedFree(Queue<Translational> episodes) {
        AtomicInteger i = new AtomicInteger();
        episodes.forEach(it -> {
            if(it.isPaid()) i.getAndIncrement();
            else i.getAndDecrement();
        });
        return i.get() <= episodes.size();
    }
}
