package ua.com.drabchak.demo;

import ua.com.drabchak.demo.service.EquanationService;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        EquanationService service = new EquanationService();

        service.solve(BigDecimal.valueOf(0.0), BigDecimal.valueOf(2.0), BigDecimal.valueOf(0.002));
    }
}
