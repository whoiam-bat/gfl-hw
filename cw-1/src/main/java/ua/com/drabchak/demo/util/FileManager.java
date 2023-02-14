package ua.com.drabchak.demo.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;

@Component
public class FileManager {

    public void cleanUpFile(String fileName) {
        try (BufferedWriter writer = getFileFromResourcesForWriting(fileName, false)) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedReader getFileFromResourcesForReading(String fileName) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        InputStream is = classPathResource.getInputStream();

        return new BufferedReader(new InputStreamReader(is));
    }

    public BufferedWriter getFileFromResourcesForWriting(String fileName, boolean append) throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);

        File file = new File(resource.getFile());

        return new BufferedWriter(new FileWriter(file, append));
    }
}
