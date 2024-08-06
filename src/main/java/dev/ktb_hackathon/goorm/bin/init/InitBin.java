package dev.ktb_hackathon.goorm.bin.init;

import com.opencsv.CSVReader;
import dev.ktb_hackathon.goorm.bin.entity.BinEntity;
import dev.ktb_hackathon.goorm.bin.repository.BinJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;

@Component
@RequiredArgsConstructor
public class InitBin implements CommandLineRunner {

    private final BinJpaRepository binJpaRepository;

    @Override
    public void run(String... args) throws Exception {
        CSVReader reader = new CSVReader(new InputStreamReader(new ClassPathResource("jeju_bin.csv").getInputStream()));

        String[] nextLine;

        reader.readNext(); // Skip header

        while ((nextLine = reader.readNext()) != null) {
            binJpaRepository.save(
                    new BinEntity(
                            nextLine[0],
                            Double.parseDouble(nextLine[7]),
                            Double.parseDouble(nextLine[8]),
                            nextLine[5],
                            nextLine[2],
                            nextLine[3])
            );
        }
    }
}
