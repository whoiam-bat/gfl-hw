package ua.com.drabchak.demo.util.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.drabchak.demo.model.Manufacturer;
import ua.com.drabchak.demo.model.Souvenir;
import ua.com.drabchak.demo.service.ManufacturerService;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class SouvenirMapper {
    private final ManufacturerService manufacturerService;

    @Autowired
    public SouvenirMapper(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    public Souvenir arrayToObject(String[] souvenir) {
        if (souvenir.length == 5) {
            Manufacturer temp = manufacturerService.findOne(souvenir[2]).get();

            return new Souvenir(Integer.parseInt(souvenir[0]), souvenir[1], temp,
                    LocalDate.parse(souvenir[3]), BigDecimal.valueOf(Double.parseDouble(souvenir[4])), temp.getId());
        }
        return null;
    }

    public String objectToString(Souvenir souvenir) {
        return souvenir.getId() +
                "\t" +
                souvenir.getSouvenirName() +
                "\t" +
                souvenir.getManufacturer().getCompanyName() +
                "\t" +
                souvenir.getDate() +
                "\t" +
                souvenir.getPrice() + "\n";
    }

}
