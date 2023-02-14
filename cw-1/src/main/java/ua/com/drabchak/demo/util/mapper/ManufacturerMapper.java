package ua.com.drabchak.demo.util.mapper;

import org.springframework.stereotype.Component;
import ua.com.drabchak.demo.model.Manufacturer;

@Component
public class ManufacturerMapper {

    public Manufacturer arrayToObject(String[] souvenir) {
        if (souvenir.length == 3) return new Manufacturer(Integer.parseInt(souvenir[0]), souvenir[1], souvenir[2]);
        return null;
    }

    public String objectToString(Manufacturer manufacturer) {
        return manufacturer.getId() +
                "\t" +
                manufacturer.getCompanyName() +
                "\t" +
                manufacturer.getState() + "\n";
    }

}
