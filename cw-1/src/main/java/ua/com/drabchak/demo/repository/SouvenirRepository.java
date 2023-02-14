package ua.com.drabchak.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.drabchak.demo.util.mapper.SouvenirMapper;
import ua.com.drabchak.demo.model.Souvenir;
import ua.com.drabchak.demo.util.FileManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SouvenirRepository {

    private static final String REPO = "repo/souvenirs.txt";

    private final FileManager fileManager;

    private final SouvenirMapper souvenirMapper;

    @Autowired
    public SouvenirRepository(FileManager fileManager, SouvenirMapper souvenirMapper) {
        this.fileManager = fileManager;
        this.souvenirMapper = souvenirMapper;
    }


    public Optional<Souvenir> findOne(int id) {
        Optional<Souvenir> souvenir = Optional.empty();

        try (BufferedReader reader = fileManager.getFileFromResourcesForReading(REPO)) {
            souvenir = reader.lines()
                    .map(it -> it.split("\\t"))
                    .map(souvenirMapper::arrayToObject)
                    .filter(it -> it.getId() == id)
                    .findFirst();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return souvenir;
    }

    public Optional<Souvenir> findOne(String name) {
        Optional<Souvenir> souvenir = Optional.empty();

        try (BufferedReader reader = fileManager.getFileFromResourcesForReading(REPO)) {
            souvenir = reader.lines()
                    .map(it -> it.split("\\t"))
                    .map(souvenirMapper::arrayToObject)
                    .filter(it -> it.getSouvenirName().equals(name))
                    .findFirst();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return souvenir;
    }

    public List<Souvenir> findAll(String manufacturerName) {
        List<Souvenir> souvenirs = new ArrayList<>();
        try (BufferedReader reader = fileManager.getFileFromResourcesForReading(REPO)) {

            souvenirs = reader.lines()
                    .map(it -> it.split("\\t"))
                    .map(souvenirMapper::arrayToObject)
                    .filter(it -> it.getManufacturer().getCompanyName().equals(manufacturerName))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return souvenirs;
    }

    public List<Souvenir> findAll() {
        List<Souvenir> souvenirs = new ArrayList<>();
        try (BufferedReader reader = fileManager.getFileFromResourcesForReading(REPO)) {

            souvenirs = reader.lines()
                    .map(it -> it.split("\\t"))
                    .map(souvenirMapper::arrayToObject)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return souvenirs;
    }

    public void save(Souvenir souvenir) {
        try (BufferedWriter writer = fileManager.getFileFromResourcesForWriting(REPO, true)) {

            String record = souvenirMapper.objectToString(souvenir);

            writer.write(record);

            Thread.sleep(300);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Souvenir souvenir) {
        souvenir.setId(id);
        List<Souvenir> souvenirs = findAll();
        fileManager.cleanUpFile(REPO);

        try (BufferedWriter writer = fileManager.getFileFromResourcesForWriting(REPO, true)) {

            souvenirs.forEach(it -> {
                try {
                    if (it.getId() == souvenir.getId())
                        writer.write(souvenirMapper.objectToString(souvenir));
                    else writer.write(souvenirMapper.objectToString(it));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread.sleep(300);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void remove(int id) {
        List<Souvenir> records = findAll();
        fileManager.cleanUpFile(REPO);
        try (BufferedWriter writer = fileManager.getFileFromResourcesForWriting(REPO, true)) {
            records.forEach(it -> {
                try {
                    if (it.getId() != id) writer.write(souvenirMapper.objectToString(it));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread.sleep(300);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void remove(String manufacturerName) {
        List<Souvenir> records = findAll();
        fileManager.cleanUpFile(REPO);
        try (BufferedWriter writer = fileManager.getFileFromResourcesForWriting(REPO, true)) {
            records.forEach(it -> {
                try {
                    if (!it.getManufacturer().getCompanyName().equals(manufacturerName))
                        writer.write(souvenirMapper.objectToString(it));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread.sleep(300);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}











