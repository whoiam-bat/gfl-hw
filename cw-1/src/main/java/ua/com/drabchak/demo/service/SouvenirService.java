package ua.com.drabchak.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.drabchak.demo.model.Manufacturer;
import ua.com.drabchak.demo.model.Souvenir;
import ua.com.drabchak.demo.repository.SouvenirRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SouvenirService {

    private final SouvenirRepository souvenirRepository;

    private final ManufacturerService manufacturerService;


    @Autowired
    public SouvenirService(SouvenirRepository souvenirRepository,
                           ManufacturerService manufacturerService) {
        this.souvenirRepository = souvenirRepository;
        this.manufacturerService = manufacturerService;
    }


    public Optional<Souvenir> findOne(int id) {
        return souvenirRepository.findOne(id);
    }

    public Optional<Souvenir> findOne(String name) {
        return souvenirRepository.findOne(name);
    }

    public List<Souvenir> findAll() {
        return souvenirRepository.findAll();
    }

    public List<Souvenir> findAllByManufacturerName(String manufacturerName) {
        return souvenirRepository.findAll(manufacturerName);
    }

    public List<Souvenir> findAllByProductionCountry(String country) {
        List<Souvenir> allRecords = souvenirRepository.findAll();
        return allRecords.stream().filter(it -> it.getManufacturer().getState().equals(country)).collect(Collectors.toList());
    }

    public List<Souvenir> findAllManufacturersWherePriceLess(BigDecimal price) {
        return souvenirRepository.findAll().stream().filter(it -> it.getPrice().compareTo(price) < 0)
                .collect(Collectors.toList());
    }

    public List<Manufacturer> findAllManufacturersOfSouvenirByYearOfCreation(String souvenirName, int year) {
        return souvenirRepository.findAll().stream()
                .filter(it -> it.getSouvenirName().equals(souvenirName))
                .filter(it -> it.getDate().getYear() == year)
                .map(Souvenir::getManufacturer)
                .collect(Collectors.toList());
    }

    public Map<Manufacturer, List<Souvenir>> groupSouvenirByManufacturer() {
        List<Souvenir> allRecords = souvenirRepository.findAll();
        return allRecords.stream().collect(Collectors.groupingBy(Souvenir::getManufacturer));
    }

    public Map<Integer, List<Souvenir>> groupSouvenirsByYear() {
        return souvenirRepository.findAll().stream()
                .collect(Collectors.groupingBy(it -> it.getDate().getYear()));
    }

    public void save(Souvenir souvenir) {
        souvenirRepository.save(souvenir);
    }

    public void update(int id, Souvenir souvenir) {
        souvenirRepository.update(id, souvenir);
    }

    public void remove(int id) {
        souvenirRepository.remove(id);
    }

    public void removeSouvenirsAndItsManufacturer(String manufacturerName) {
        souvenirRepository.remove(manufacturerName);
        manufacturerService.remove(manufacturerName);
    }
}
