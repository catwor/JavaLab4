package org.example;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {
  private Integer id;
  private String name;
  private Gender gender;
  private Division division;
  private Double salary;
  private LocalDate birthday;
}
