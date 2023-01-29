package ua.com.drabchak.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.drabchak.demo.validator.TranslationValidator;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Stream {

    private static final Logger LOGGER = LoggerFactory.getLogger(Stream.class);

    private static final TranslationValidator VALIDATOR = new TranslationValidator();

    private UUID translationId;

    private Queue<Translational> episodes;

    private LocalTime durationOfTranslation;

    private final Date translationDate;


    public Stream(LocalTime durationOfTranslation) {
        this.translationId = UUID.randomUUID();
        this.durationOfTranslation = durationOfTranslation;
        this.episodes = new ArrayDeque<>();
        this.translationDate = Date.valueOf(LocalDate.now());
    }

    public void addEpisode(Translational episode) {
        if (VALIDATOR.isPaidContentDoesNotExceedFree(episodes) &&
                VALIDATOR.isEpisodeCanBeHeldInTranslation(durationOfTranslation, episode.getDuration()))
            episodes.add(episode);

        else LOGGER.error("There is no more free time for this episode");
    }

    public int income() {
        return episodes.stream().mapToInt(Translational::getPrice).sum();
    }

    @Override
    public String toString() {
        return "\nSTREAM [" + translationId +
                "]: episodes ==> " + episodes +
                ", durationOfTranslation ==> " + durationOfTranslation +
                ", translationDate ==> " + translationDate;
    }
}
