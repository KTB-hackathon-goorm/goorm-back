package dev.ktb_hackathon.goorm;

import dev.ktb_hackathon.goorm.ddl.CsvToH2Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoormApplication {

    public static void main(String[] args) {
        CsvToH2Database.insertData();
        SpringApplication.run(GoormApplication.class, args);
    }
}
