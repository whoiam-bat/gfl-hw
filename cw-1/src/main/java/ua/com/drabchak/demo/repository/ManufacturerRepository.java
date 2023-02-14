package ua.com.drabchak.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.drabchak.demo.model.Manufacturer;
import ua.com.drabchak.demo.util.FileManager;
import ua.com.drabchak.demo.util.mapper.ManufacturerMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ManufacturerRepository {
    private static final String REPO = "repo/manufacturers.txt";

    private final FileManager fileManager;

    private final ManufacturerMapper manufacturerMapper;

    @Autowired
    public ManufacturerRepository(FileManager fileManager, ManufacturerMapper manufacturerMapper) {
        this.fileManager = fileManager;
        this.manufacturerMapper = manufacturerMapper;
    }


    public Optional<Manufacturer> findOne(int id) {
        Optional<Manufacturer> res = Optional.empty();
        try (BufferedReader reader = fileManager.getFileFromResourcesForReading(REPO)) {

            res = reader.lines()
                    .map(it -> it.split("\\t"))
                    .filter(thisId -> Integer.parseInt(thisId[0]) == id)
                    .map(manufacturerMapper::arrayToObject).findFirst();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public Optional<Manufacturer> findOne(String name) {
        Optional<Manufacturer> res = Optional.empty();
        try (BufferedReader reader = fileManager.getFileFromResourcesForReading(REPO)) {

            res = reader.lines()
                    .map(it -> it.split("\\t"))
                    .filter(thisName -> thisName[1].equals(name))
                    .map(manufacturerMapper::arrayToObject).findFirst();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public List<Manufacturer> findAll() {
        List<Manufacturer> res = new ArrayList<>();
        try (BufferedReader reader = fileManager.getFileFromResourcesForReading(REPO)) {

            res = reader.lines()
                    .map(it -> it.split("\\t"))
                    .map(manufacturerMapper::arrayToObject).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public void save(Manufacturer manufacturer) {
        try (BufferedWriter writer = fileManager.getFileFromResourcesForWriting(REPO, true)) {

            String record = manufacturerMapper.objectToString(manufacturer);

            writer.write(record);
            Thread.sleep(300);

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Manufacturer manufacturer) {
        manufacturer.setId(id);
        List<Manufacturer> temp = findAll();
        fileManager.cleanUpFile(REPO);
        try (BufferedWriter writer = fileManager.getFileFromResourcesForWriting(REPO, true)) {
            temp.forEach(it -> {
                try {
                    if (it.getId() == manufacturer.getId())
                        writer.write(manufacturerMapper.objectToString(manufacturer));
                    else writer.write(manufacturerMapper.objectToString(it));

                    Thread.sleep(300);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove(int id) {
        List<Manufacturer> records = findAll();
        fileManager.cleanUpFile(REPO);
        try (BufferedWriter writer = fileManager.getFileFromResourcesForWriting(REPO, true)) {
            records.forEach(it -> {
                try {
                    if (it.getId() != id) writer.write(manufacturerMapper.objectToString(it));

                    Thread.sleep(300);

                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove(String name) {
        List<Manufacturer> records = findAll();
        fileManager.cleanUpFile(REPO);
        try (BufferedWriter writer = fileManager.getFileFromResourcesForWriting(REPO, true)) {
            records.forEach(it -> {
                try {
                    if (!it.getCompanyName().equals(name))
                        writer.write(manufacturerMapper.objectToString(it));

                    Thread.sleep(300);

                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
