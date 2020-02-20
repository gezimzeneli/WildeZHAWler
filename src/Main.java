import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private List<Library> libraries;
    private List<Bock> books;
    private int bookCount;
    private int libraryCount;
    private int days;

    public static void main(String[] args) {
        Main main = new Main();
        main.read();

    }

    private void read(){
        String file ="";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String[] items;
            //Read first line
            if((line = br.readLine()) != null) {
                items = line.split(" ");
                bookCount = Integer.parseInt(items[0]);
                libraryCount = Integer.parseInt(items[1]);
                days = Integer.parseInt(items[2]);
            }

            //Create books with scores
            if((line = br.readLine()) != null){
                items = line.split(" ");
                books = new ArrayList<Bock>(bookCount);
                for(int i = 0; i < bookCount; i++){
                    books.add(new Bock(Integer.parseInt(items[i]), i));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
