import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.example.CsvReader;
import org.example.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CsvReaderTest {
  @Test
  public void readerCsvTest() {
    try {
      List<Person> persons = CsvReader.ReadCsvIntoPersonList("foreign_names.csv",";");

      for (Person p : persons) {
        Assertions.assertNotNull(p.getGender());
        Assertions.assertNotNull(p.getDivision());
        Assertions.assertNotNull(p.getBirthday());
        Assertions.assertNotEquals(p.getId(), 0);
        Assertions.assertNotEquals(p.getSalary(), 0.0);
      }
    }
    catch (Exception ignored) {
    }
  }
  @Test
  public void fileNotFoundTest() {
    Assertions.assertThrows(RuntimeException.class, () -> CsvReader.ReadCsvIntoPersonList("path",";"));
  }
}
