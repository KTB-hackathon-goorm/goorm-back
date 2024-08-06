package dev.ktb_hackathon.goorm.ddl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class CsvToH2Database {

    private static final String CSV_FILE_PATH = "/Users/haejangjang/Desktop/haisely/cloud/final/goormthon/goorm-back/src/main/java/dev/ktb_hackathon/goorm/ddl/제주특별자치도 제주시_클린하우스_20221030.csv";
    private static final String JDBC_URL = "jdbc:h2:mem:local";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void insertData() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            // Create table
            String createTableSQL = "CREATE TABLE DATA (" +
                    "데이터_코드 VARCHAR(255), " +
                    "구분_명 VARCHAR(255), " +
                    "읍면동_명 VARCHAR(255), " +
                    "단지_명 VARCHAR(255), " +
                    "유형_명 VARCHAR(255), " +
                    "도로명_주소 VARCHAR(255), " +
                    "지번_주소 VARCHAR(255), " +
                    "위도_좌표 DOUBLE, " +
                    "경도_좌표 DOUBLE, " +
                    "종량제_수거함_수 INT, " +
                    "재활용_수거함_수 INT, " +
                    "유리병_수거함_수 INT, " +
                    "스티로폼_수거함_수 INT, " +
                    "폐기_건전지_수거함_수 INT, " +
                    "폐기_형광등_수거함_수 INT, " +
                    "음식물_수거함_수 INT, " +
                    "음식물_계량_수거함_수 INT, " +
                    "CCTV_설치_수 INT, " +
                    "특이사항_내용 VARCHAR(255), " +
                    "사진_여부 VARCHAR(255), " +
                    "기타_내용 VARCHAR(255), " +
                    "등록_일시 VARCHAR(255)" +  // Assuming this is the 22nd column
                    ")";
            connection.createStatement().execute(createTableSQL);

            // Insert data
            String insertSQL = "INSERT INTO DATA VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(CSV_FILE_PATH), StandardCharsets.UTF_8)).withSkipLines(1).build();
                 PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

                String[] line;
                while ((line = reader.readNext()) != null) {
                    // Print the length of each line
//                    System.out.println("Line length: " + line.length);
                    System.out.println(line[4]);

                    if (line.length != 22) {
                        System.err.println("Incorrect number of columns in line: " + String.join(", ", line));
                        continue; // Skip this line
                    }

                    preparedStatement.setString(1, line[0]);
                    preparedStatement.setString(2, line[1]);
                    preparedStatement.setString(3, line[2]);
                    preparedStatement.setString(4, line[3]);
                    preparedStatement.setString(5, line[4]);
                    preparedStatement.setString(6, line[5]);
                    preparedStatement.setString(7, line[6]);
                    preparedStatement.setDouble(8, Double.parseDouble(line[7]));
                    preparedStatement.setDouble(9, Double.parseDouble(line[8]));
                    preparedStatement.setInt(10, Integer.parseInt(line[9]));
                    preparedStatement.setInt(11, Integer.parseInt(line[10]));
                    preparedStatement.setInt(12, Integer.parseInt(line[11]));
                    preparedStatement.setInt(13, Integer.parseInt(line[12]));
                    preparedStatement.setInt(14, Integer.parseInt(line[13]));
                    preparedStatement.setInt(15, Integer.parseInt(line[14]));
                    preparedStatement.setInt(16, Integer.parseInt(line[15]));
                    preparedStatement.setInt(17, Integer.parseInt(line[16]));
                    preparedStatement.setInt(18, Integer.parseInt(line[17]));
                    preparedStatement.setString(19, line[18]);
                    preparedStatement.setString(20, line[19]);
                    preparedStatement.setString(21, line[20]);
                    preparedStatement.setString(22, line[21]); // Assuming '등록_일시' is in the 22nd column
                    preparedStatement.executeUpdate();
                }
            } catch (CsvValidationException e) {
                System.err.println("CSV validation error: " + e.getMessage());
            }

            System.out.println("Data inserted successfully!");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
