package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CsvReader {
  private static String regex = ";";
  private static String path = "src/main/resources/foreign_names.csv";

  public static List<Person> ReadCsvIntoPersonList(){
    List<Person> personList = new ArrayList<>();

    try (InputStream in = CsvReader.class.getClassLoader().getResourceAsStream(path)) {
      BufferedReader reader = in == null ? null : new BufferedReader(new InputStreamReader(in));

      if (reader == null)
        throw new FileNotFoundException(path);

      reader.readLine();
      String nextLine;

      while ((nextLine = reader.readLine()) != null) {
        personList.add(mapper(nextLine));
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return personList;
  }

  private static Person mapper(String line){
    Random random = new Random();
    String[] struct = line.split(regex);
    return Person.builder()
        .id(Integer.valueOf(struct[0]))
        .gender(Gender.valueOf(struct[1]))
        .birthday(LocalDate.parse(struct[2]))
        .division(Division.builder()
            .id(random.nextInt(10_000_000)+1)
            .name(struct[3])
            .build())
        .salary(Double.valueOf(struct[4]))
        .build();
  }
}
