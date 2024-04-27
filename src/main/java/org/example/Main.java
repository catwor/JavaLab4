package org.example;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<Person> persons = CsvReader.ReadCsvIntoPersonList("foreign_names.csv",";");
    for (Person p: persons) {
      System.out.println(p.toString());
    }
  }
}