import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    private List<Library> libraries;
    private List<Bock> bocks;
    public static void main(String[] args) {

        System.out.println(("Hello World"));
        try (BufferedReader br = new BufferedReader(new FileReader(""))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
