package ua.com.drabchak.demo.util.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.com.drabchak.demo.model.Manufacturer;
import ua.com.drabchak.demo.service.ManufacturerService;

import java.util.Optional;

@Component
public class ManufacturerValidator implements Validator {

    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerValidator(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Manufacturer.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Manufacturer manufacturer = (Manufacturer) target;

        Optional<Manufacturer> manufacturerFromFile = manufacturerService.findOne(manufacturer.getId());

        if(manufacturerFromFile.isPresent()) {
            Manufacturer temp = manufacturerFromFile.get();

            if(manufacturer.getCompanyName().equals(temp.getCompanyName()))
                errors.rejectValue("companyName", "", "Such manufacturer already exists");
            else errors.rejectValue("id", "", "Record with such ID already exists");
        } else errors.rejectValue("id", "Record with such ID doesn't exist");
    }
}
