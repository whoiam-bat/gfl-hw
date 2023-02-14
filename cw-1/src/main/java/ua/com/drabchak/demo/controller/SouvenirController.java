package ua.com.drabchak.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.drabchak.demo.model.Manufacturer;
import ua.com.drabchak.demo.model.Souvenir;
import ua.com.drabchak.demo.service.ManufacturerService;
import ua.com.drabchak.demo.service.SouvenirService;
import ua.com.drabchak.demo.util.validator.SouvenirValidator;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/souvenirs")
public class SouvenirController {

    private final SouvenirService souvenirService;

    private final ManufacturerService manufacturerService;

    private final SouvenirValidator souvenirValidator;

    @Autowired
    public SouvenirController(SouvenirService souvenirService, ManufacturerService manufacturerService, SouvenirValidator souvenirValidator) {
        this.souvenirService = souvenirService;
        this.manufacturerService = manufacturerService;
        this.souvenirValidator = souvenirValidator;
    }

    @GetMapping
    public String souvenirList(Model model) {
        model.addAttribute("souvenirList", souvenirService.findAll());

        return "souvenirs/souvenir-list";
    }

    @GetMapping("/new")
    public String newSouvenir(Model model) {
        model.addAttribute("newSouvenir", new Souvenir());
        model.addAttribute("availableManufacturers", manufacturerService.findAll());

        return "souvenirs/souvenir-new";
    }

    @PostMapping
    public String addNewSouvenir(@ModelAttribute("newSouvenir") @Valid Souvenir newSouvenir, BindingResult bindingResult) {
        newSouvenir.setManufacturer(manufacturerService.findOne(newSouvenir.getManufacturerId()).get());

        souvenirValidator.validate(newSouvenir, bindingResult);

        if(bindingResult.hasErrors()) return "souvenirs/souvenir-new";

        souvenirService.save(newSouvenir);

        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editSouvenir(@PathVariable("id") int id, Model model) {
        Souvenir souvenir = souvenirService.findOne(id).get();
        souvenir.setManufacturer(manufacturerService.findOne(souvenir.getManufacturerId()).get());

        model.addAttribute("souvenirToEdit", souvenir);

        return "souvenirs/souvenir-edit";
    }

    @PatchMapping("/{id}")
    public String edit(@PathVariable("id") int id,
                       @ModelAttribute("souvenirToEdit") @Valid Souvenir souvenirToEdit,BindingResult bindingResult) {
        souvenirToEdit.setManufacturer(manufacturerService.findOne(souvenirToEdit.getManufacturerId()).get());

        if(bindingResult.hasErrors()) return "souvenirs/souvenir-edit";

        souvenirService.update(id, souvenirToEdit);

        return "redirect:/";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        souvenirService.remove(id);
        return "redirect:/";
    }
}
