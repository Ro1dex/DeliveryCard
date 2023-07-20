import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataGenerate {
    public static String generate(){
            LocalDate currentDate = LocalDate.now().plusDays(6);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(currentDate);
    }
}
