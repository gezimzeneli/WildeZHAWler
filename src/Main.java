<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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
        System.out.println(("Hello World"));

        Main main = new Main();
        main.read();

        // DUMMY
        int D = 5;
        List<Library> librariesToScan = getLibrariesToDo(new ArrayList<>(), D);
        librariesToScan = getBooksToDo(librariesToScan, D);
    }

    private static List<Library> getLibrariesToDo(List<Library> libraries, int D){
        // 1. Kick all libraries with a signup time >= D
        List<Library> validLibraries = libraries.stream().filter(iter -> iter.getTimeToSignUp() < D).collect(Collectors.toList());

        List<Library> librariesOrdered = validLibraries.stream()
                .sorted(Comparator.comparingInt(Library::getTimeToSignUp))
                .collect(Collectors.toList());

        int days = 0;
        List<Library> librariesToScan = new ArrayList<>();

        for(Library l : librariesOrdered){
            days += l.getTimeToSignUp();
            if (days <= D){
                librariesToScan.add(l);
            }
        }

        return librariesToScan;
    }

    private static List<Library> getBooksToDo(List<Library> libraries, int D)
    {
        for (Library library : libraries)
        {
            library.setBocks(
                    library.getBocks().stream().sorted(Comparator.comparingInt(Bock::getScore)).collect(Collectors.toList())
            );
            //TODO: D is supposed to be left over time for scan
            int n = library.getScansPerDay() * D;

            List<Bock> bestBocks = library.getBocks().stream().limit(n).collect(Collectors.toList());

            library.setBocks(bestBocks);
        }

        return libraries;
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
