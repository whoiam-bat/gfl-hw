package ua.com.drabchak.demo.repository;

import ua.com.drabchak.demo.model.Presenter;
import ua.com.drabchak.demo.model.Stream;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StreamRepository {
    private static StreamRepository repository;

    private final Map<Presenter, List<Stream>> translations;

    private StreamRepository() {
        this.translations = new HashMap<>();
    }

    public void addNewTranslation(Presenter presenter, Stream stream) {
        List<Stream> streamList = translations.get(presenter);

        if (streamList == null) streamList = new LinkedList<>();
        streamList.add(stream);

        translations.put(presenter, streamList);
    }

    public List<Stream> findByPresenter(Presenter presenter) {
        return translations.get(presenter);
    }

    public Map<Presenter, List<Stream>> findAll() {
        return translations;
    }

    public int getIncomeFromStream(Stream stream) {
        Stream temp = translations.values().stream().
                flatMap(it -> it.stream().filter(el -> el.equals(stream))).toList().get(0);
        System.out.println(temp);
        return temp.income();
    }

    public int size() {
        return translations.size();
    }

    public static StreamRepository getInstance() {
        if(repository == null) repository = new StreamRepository();
        return repository;
    }
}
