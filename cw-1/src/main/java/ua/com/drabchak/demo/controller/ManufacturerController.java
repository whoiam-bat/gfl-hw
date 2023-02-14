package ua.com.drabchak.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.drabchak.demo.model.Manufacturer;
import ua.com.drabchak.demo.service.ManufacturerService;
import ua.com.drabchak.demo.service.SouvenirService;
import ua.com.drabchak.demo.util.validator.ManufacturerValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    private final SouvenirService souvenirService;

    private final ManufacturerValidator manufacturerValidator;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService,
                                  SouvenirService souvenirService,
                                  ManufacturerValidator manufacturerValidator) {
        this.manufacturerService = manufacturerService;
        this.souvenirService = souvenirService;
        this.manufacturerValidator = manufacturerValidator;
    }

    @GetMapping
    public String manufacturerList(Model model) {
        model.addAttribute("manufacturerList", manufacturerService.findAll());

        return "manufacturers/manufacturer-list";
    }

    @GetMapping("/{id}")
    public String getManufacturer(@PathVariable("id") int id, Model model) {
        Manufacturer manufacturer = manufacturerService.findOne(id).get();

        model.addAttribute("manufacturer", manufacturer);
        model.addAttribute("manufacturerSouvenirs",
                souvenirService.findAllByManufacturerName(manufacturer.getCompanyName()));

        return "manufacturers/manufacturer";
    }

    @GetMapping("/new")
    public String newManufacturer(Model model) {
        model.addAttribute("newManufacturer", new Manufacturer());

        return "manufacturers/manufacturer-new";
    }

    @PostMapping
    public String addNewManufacturer(@ModelAttribute("newManufacturer") @Valid Manufacturer newManufacturer,
                                     BindingResult bindingResult) {
        manufacturerValidator.validate(newManufacturer, bindingResult);

        if (bindingResult.hasErrors()) return "manufacturers/manufacturer-new";

        manufacturerService.save(newManufacturer);

        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editManufacturer(@PathVariable("id") int id, Model model) {
        Manufacturer manufacturer = manufacturerService.findOne(id).get();

        model.addAttribute("manufacturerToEdit", manufacturer);

        return "manufacturers/manufacturer-edit";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("manufacturerToEdit") @Valid Manufacturer manufacturerToEdit,
                       BindingResult bindingResult,
                       @PathVariable("id") int id) {

        if(bindingResult.hasErrors()) return "manufacturers/manufacturer-edit";

        manufacturerService.update(id, manufacturerToEdit);

        return "redirect:/";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        souvenirService.removeSouvenirsAndItsManufacturer(manufacturerService.findOne(id).get().getCompanyName());
        return "redirect:/";
    }

}
