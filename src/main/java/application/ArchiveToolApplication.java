package application;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArchiveToolApplication {

    public static void main(String[] args) {
        Application.launch(JavafxApplication.class, args);
    }
}