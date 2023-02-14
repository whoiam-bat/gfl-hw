package ua.com.drabchak.demo.util.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.com.drabchak.demo.model.Manufacturer;
import ua.com.drabchak.demo.model.Souvenir;
import ua.com.drabchak.demo.service.ManufacturerService;
import ua.com.drabchak.demo.service.SouvenirService;

import java.util.Optional;

@Component
public class SouvenirValidator implements Validator {

    private final SouvenirService souvenirService;

    private final ManufacturerService manufacturerService;

    @Autowired
    public SouvenirValidator(SouvenirService souvenirService, ManufacturerService manufacturerService) {
        this.souvenirService = souvenirService;
        this.manufacturerService = manufacturerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Souvenir.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Souvenir souvenir = (Souvenir) target;

        Optional<Souvenir> souvenirFromFile = souvenirService.findOne(souvenir.getId());
        Optional<Manufacturer> manufacturerFromFile = manufacturerService.findOne(((Souvenir) target).getManufacturer().getCompanyName());

        if(souvenirFromFile.isEmpty()) {
            if(manufacturerFromFile.isEmpty())
                errors.rejectValue("manufacturer", "", "Such manufacturer doesn't exist, " +
                        "create manufacturer with such name");
        } else {
            Souvenir temp = souvenirFromFile.get();
            if(souvenir.getSouvenirName().equals(temp.getSouvenirName()) &&
                    souvenir.getManufacturer().getCompanyName().equals(temp.getManufacturer().getCompanyName()))
                errors.rejectValue("souvenirName", "", "Such souvenir already exists");
        }
    }
}
