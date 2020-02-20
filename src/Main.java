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
        String file = "a_example.txt";
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
                libraries = new ArrayList<Library>(libraryCount);
                for(int i=0; i < libraryCount; i++){
                    if((line = br.readLine()) != null){
                        items = line.split(" ");
                        Library library = new Library();
                        library.setId(i);
                        int amountBook = Integer.parseInt(items[0]);
                        library.setTimeToSignUp(Integer.parseInt(items[1]));
                        library.setScansPerDay(Integer.parseInt(items[2]));
                        if((line = br.readLine()) != null) {
                            items = line.split(" ");
                            ArrayList<Bock> temp = new ArrayList<>(amountBook);
                            for (int j = 0; j < amountBook; j++) {
                                temp.add(books.get(Integer.parseInt(items[j])));
                            }
                            library.setBocks(temp);
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
