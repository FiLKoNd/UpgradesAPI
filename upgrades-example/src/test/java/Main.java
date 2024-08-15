import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("Hello");
        strings.add("World");
        strings.stream().filter(s -> s.startsWith("H")).forEach(System.out::println);
    }
}
