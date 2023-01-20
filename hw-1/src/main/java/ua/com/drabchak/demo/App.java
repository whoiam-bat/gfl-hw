package ua.com.drabchak.demo;

import ua.com.drabchak.demo.service.EquanationService;

public class App {
    public static void main(String[] args) {
        EquanationService service = new EquanationService();
        service.run(0.0, 2.0, 0.002);
    }
}
