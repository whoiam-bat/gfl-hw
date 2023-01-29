package ua.com.drabchak.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.drabchak.demo.model.*;
import ua.com.drabchak.demo.repository.StreamRepository;

import java.time.LocalTime;
import java.util.List;

public class StreamService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamService.class);
    private static StreamService streamService;

    private final StreamRepository repository;

    private final PresenterBuilder builder;


    private StreamService() {
        repository = StreamRepository.getInstance();
        builder = PresenterBuilder.getInstance();
    }

    private Presenter createNewPresenter() {
        builder.setName("Test-" + Math.random());
        builder.setExperience((int) Math.random());
        return builder.getPresenter();
    }

    private Presenter createNewTemporaryPresenter() {
        builder.setName("Test-" + Math.random());
        builder.setExperience((int) Math.random());
        builder.setResume("Hello Java");
        return builder.getPresenter();
    }

    private Stream createStream() {
        Stream stream1 = new Stream(LocalTime.of(2, 0, 0));
        stream1.addEpisode(new SongTranslation(LocalTime.of(0, 3, 35), "Marilyn Manson", "KILL4ME"));
        stream1.addEpisode(new AdvertiseTranslation(LocalTime.of(0, 2, 0), "LACALUTE"));
        stream1.addEpisode(new InterviewTranslation(LocalTime.of(1, 30, 50), "Elon Musk"));

        Stream stream2 = new Stream(LocalTime.of(1, 45));
        stream2.addEpisode(new SongTranslation(LocalTime.of(0, 5), "21 Pilots", "Stresed Out"));
        stream2.addEpisode(new InterviewTranslation(LocalTime.of(1, 15, 50), "Steve Harvey"));
        stream2.addEpisode(new AdvertiseTranslation(LocalTime.of(0, 3, 0), "Colgate"));

        Stream stream3 = new Stream(LocalTime.of(4, 0, 0));
        stream3.addEpisode(new SongTranslation(LocalTime.of(0, 5), "Linkin Park", "Numb"));
        stream3.addEpisode(new SongTranslation(LocalTime.of(0, 3, 23), "Linkin Park", "Numb"));
        stream3.addEpisode(new AdvertiseTranslation(LocalTime.of(0, 3, 0), "Colgate"));
        stream3.addEpisode(new SongTranslation(LocalTime.of(0, 2, 45), "The Prodigy", "Omen"));
        stream3.addEpisode(new InterviewTranslation(LocalTime.of(1, 15, 50), "Steve Harvey"));


        List<Stream> streams = List.of(stream1, stream2, stream3);

        return streams.get((int)(Math.random() * streams.size()));
    }

    private void addToRepository(Presenter presenter, Stream... streams) {
        for (Stream it : streams) {
            repository.addNewTranslation(presenter, it);
        }
    }

    private int getStreamIncome(Stream stream) {
        return repository.getIncomeFromStream(stream);
    }

    private void showAllStreams() {
        System.out.println(repository.findAll());
    }


    public void run() {
        Presenter presenter1 = createNewPresenter();
        LOGGER.info("PRESENTER CREATED {}", presenter1);

        Presenter presenter2 = createNewTemporaryPresenter();
        LOGGER.info("TEMPORARY PRESENTER CREATED {}", presenter2);

        Stream stream1 = createStream();
        LOGGER.info("STREAM CREATED {}", stream1);

        Stream stream2 = createStream();
        LOGGER.info("STREAM CREATED {}", stream2);

        Stream stream3 = createStream();
        LOGGER.info("STREAM CREATED {}", stream3);

        addToRepository(presenter1, stream1, stream3);
        LOGGER.info("PRESENTER-1 WITH STREAMS ADDED TO REPOSITORY");

        addToRepository(presenter2, stream2);
        LOGGER.info("PRESENTER-2 WITH STREAMS ADDED TO REPOSITORY");

        LOGGER.info("STREAM-1 INCOME == {}", getStreamIncome(stream1));

        showAllStreams();
    }


    public static StreamService getInstance() {
        if (streamService == null) streamService = new StreamService();
        return streamService;
    }
}
