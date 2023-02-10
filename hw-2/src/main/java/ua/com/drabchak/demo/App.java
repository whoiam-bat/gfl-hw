package ua.com.drabchak.demo;

import ua.com.drabchak.demo.util.StreamService;

public class App {
    public static void main(String[] args) {
        StreamService streamService = StreamService.getInstance();

        streamService.run();
    }
}
