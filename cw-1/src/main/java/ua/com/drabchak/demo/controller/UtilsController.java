package ua.com.drabchak.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.drabchak.demo.model.Souvenir;
import ua.com.drabchak.demo.service.ManufacturerService;
import ua.com.drabchak.demo.service.SouvenirService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/utils")
public class UtilsController {

    private final ManufacturerService manufacturerService;

    private final SouvenirService souvenirService;

    @Autowired
    public UtilsController(ManufacturerService manufacturerService, SouvenirService souvenirService) {
        this.manufacturerService = manufacturerService;
        this.souvenirService = souvenirService;
    }

    @GetMapping
    public String actions() {
        return "more-actions/action-menu";
    }

    @GetMapping("/souvenir_from_state")
    public String souvenirsFromState() {
        return "more-actions/souvenirs-from-state";
    }

    @GetMapping("/souvenir_from_state/find")
    public String getSouvenirsFromState(@RequestParam("state") String state, Model model) {
        List<Souvenir> souvenirs = souvenirService.findAllByProductionCountry(state);

        model.addAttribute("souvenirs", souvenirs);

        return "more-actions/souvenirs-from-state";
    }

    @GetMapping("/manufacturer_with_price")
    public String manufacturersWithPriseLessThanGiven() {
        return "more-actions/manufacturer-price-less-than-given";
    }

    @GetMapping("/manufacturer_with_price/find")
    public String getManufacturersWithPriseLessThanGiven(@RequestParam("price") BigDecimal price, Model model) {
        List<Souvenir> souvenirs = souvenirService.findAllManufacturersWherePriceLess(price);
        model.addAttribute("souvenirList", souvenirs);

        return "more-actions/manufacturer-price-less-than-given";
    }

    @GetMapping("/manufacturer_with_produced_souvenirs")
    public String manufacturersWithProducedSouvenirs(Model model) {
        model.addAttribute("manufacturersWithSouvenirs", souvenirService.groupSouvenirByManufacturer());

        return "more-actions/manufacturers-with-their-souvenirs";
    }

    @GetMapping("/manufacturer_by_given_souvenir_and_year")
    public String manufacturersBySouvenirAndYear() {
        return "more-actions/manufacturer-by-given-souvenir-and-year";
    }

    @GetMapping("/manufacturer_by_given_souvenir_and_year/find")
    public String getManufacturersBySouvenirAndYear(@RequestParam("souvenirName") String souvenirName,
                                                    @RequestParam("year") int year, Model model) {
        model.addAttribute("manufacturers",
                souvenirService.findAllManufacturersOfSouvenirByYearOfCreation(souvenirName, year));

        return "more-actions/manufacturer-by-given-souvenir-and-year";
    }

    @GetMapping("/souvenirs_grouped_by_year")
    public String souvenirsGroupedByYear(Model model) {
        model.addAttribute("souvenirs", souvenirService.groupSouvenirsByYear());

        return "more-actions/souvenirs-grouped-by-year";
    }



}
