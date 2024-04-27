package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Класс CsvReader предоставляет методы для чтения данных из CSV файла.
 */
public class CsvReader {

  private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

  /**
   * Читает данные из CSV файла в список объектов Person.
   * @param path  путь к файлу CSV
   * @param regex разделитель для столбцов в строке CSV
   * @return список объектов Person, содержащий данные из CSV файла
   * @throws FileNotFoundException если указанный файл не был найден
   * @throws RuntimeException если возникают ошибки ввода-вывода или если файл не найден
   */
  public static List<Person> ReadCsvIntoPersonList(String path, String regex) {
    List<Person> personList = new ArrayList<>();

    try (InputStream in =  CsvReader.class.getClassLoader().getResourceAsStream(path)) {
      BufferedReader reader = in == null ? null : new BufferedReader(new InputStreamReader(in));

      if (reader == null)
        throw new FileNotFoundException(path);

      reader.readLine();
      String nextLine;

      while ((nextLine = reader.readLine()) != null) {
        personList.add(personMapper(nextLine,regex));
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return personList;
  }

  /**
   * Преобразует строку CSV в объект Person.
   * @param line  строка CSV, содержащая данные о персоне
   * @param regex разделитель для столбцов в строке CSV
   * @return объект Person, содержащий данные из строки CSV
   */
  private static Person personMapper(String line, String regex){
    Random random = new Random();
    Map<String, Gender> gender = new HashMap<>(){
      {
        put("Male",Gender.MALE);
        put("Female",Gender.FEMALE);
      }
    };

    String[] struct = line.split(regex);
    return Person.builder()
        .id(Integer.valueOf(struct[0]))
        .name(struct[1])
        .gender(gender.get(struct[2]))
        .birthday(LocalDate.parse(struct[3], formatter))
        .division(Division.builder()
            .id(random.nextInt(10_000_000)+1)
            .name(struct[4])
            .build())
        .salary(Double.valueOf(struct[5]))
        .build();
  }
}
