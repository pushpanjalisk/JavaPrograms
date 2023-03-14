import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class dateProgram {
    public static void main(String[] args) {
        DateTimeFormatter objectDateFormatter = DateTimeFormatter.ofPattern("YYYY-dd-MMM");
        LocalDateTime objectDate = LocalDateTime.now();
        System.out.println((Object)objectDate.getClass().getSimpleName());
        System.out.println(objectDateFormatter.format(objectDate));
    }
}
