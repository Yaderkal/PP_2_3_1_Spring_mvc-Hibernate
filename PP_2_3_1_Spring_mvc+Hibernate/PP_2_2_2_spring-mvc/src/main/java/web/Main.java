package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.awt.Desktop;
import java.net.URI;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Метод для запуска через java -jar
    @EventListener(ApplicationReadyEvent.class)
    public void openBrowser() {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI("http://localhost:8080/users"));
                System.out.println("Браузер открыт по адресу: http://localhost:8080/users");
            } else {
                System.out.println("Перейдите по ссылке: http://localhost:8080/users");
            }
        } catch (Exception e) {
            System.err.println("Не удалось открыть браузер автоматически: " + e.getMessage());
            System.out.println("Перейдите вручную по ссылке: http://localhost:8080/users");
        }
    }
}