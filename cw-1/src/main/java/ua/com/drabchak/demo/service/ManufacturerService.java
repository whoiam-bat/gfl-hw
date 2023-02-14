package ua.com.drabchak.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.drabchak.demo.model.Manufacturer;
import ua.com.drabchak.demo.repository.ManufacturerRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ManufacturerService {
    private final ManufacturerRepository repository;

    @Autowired
    public ManufacturerService(ManufacturerRepository repository) {
        this.repository = repository;
    }


    public List<Manufacturer> findAll() {
        return repository.findAll();
    }

    public Optional<Manufacturer> findOne(int id) {
        return repository.findOne(id);
    }

    public Optional<Manufacturer> findOne(String name) {
        return repository.findOne(name);
    }

    public void save(Manufacturer manufacturer) {
        repository.save(manufacturer);
    }

    public void update(int id, Manufacturer manufacturer) {
        repository.update(id, manufacturer);
    }

    public void remove(int id) {
        repository.remove(id);
    }

    public void remove(String name) {
        repository.remove(name);
    }
}
