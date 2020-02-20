import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static List<Library> libraries;
    private static List<Bock> books;
    private static int bookCount;
    private static int libraryCount;
    private static int days;

    public static void main(String[] args) {
        System.out.println(("Hello World"));

        Main main = new Main();
        String[] files = {"a_example", "b_read_on","c_incunabula","d_tough_choices","e_so_many_books", "f_libraries_of_the_world"};
        for(String file : files){
            main.read(file + ".txt");

            // DUMMY
            int D = days;
            // List<Library> librariesToScan = getLibrariesToDo(libraries, D);
            List<Library> librariesToScan = getLibrariesWithFactor(libraries, D);
            removeDuplicateBooks(librariesToScan);
            librariesToScan = getBooksToDo(librariesToScan, D);

            Writer writer = new Writer("./out/"+file+"output.txt");
            writer.writeResult(librariesToScan);
        }

    }

    private static void removeDuplicateBooks(List<Library> libraries){
        Set<Bock> addedBocks = new HashSet<>();
        for (Library l : libraries){
            List<Bock> bestBocks = new ArrayList<>();

            for(Bock b : l.getBocks()){
                if (addedBocks.add(b)){
                    bestBocks.add(b);
                }
            }

            l.setBocks(bestBocks);
        }
    }

    private static List<Library> getLibrariesWithFactor(List<Library> libraries, int D){
        removeInvalidLibraries(libraries, D);

        for (Library l : libraries){
            long n = l.getTimeToSignUp() * D;
            long bookScoreSum = l.getBocks().stream().filter(b -> b.getScore() > 0).mapToLong(b -> b.getScore()).sum();
            l.setFactor((n*bookScoreSum)/l.getTimeToSignUp());
        }

        List<Library> librariesOrdered = libraries.stream()
                .sorted(Comparator.comparingLong(Library::getFactor))
                .collect(Collectors.toList());

        List<Library> librariesToScan = new ArrayList<>();
        for(Library l : librariesOrdered){
            days += l.getTimeToSignUp();
            if (days <= D){
                librariesToScan.add(l);
            }
        }

        return librariesToScan;
    }

    private static List<Library> removeInvalidLibraries(List<Library> libraries, int D) {
        return libraries.stream().filter(iter -> iter.getTimeToSignUp() < D).collect(Collectors.toList());
    }

    private static List<Library> getLibrariesToDo(List<Library> libraries, int D){
        List<Library> validLibraries = removeInvalidLibraries(libraries, D);

        // 2. Libraries with smallest signup time
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
                    library.getBocks().stream().sorted(Comparator.comparingInt(Bock::getScore).reversed()).collect(Collectors.toList())
            );
            //TODO: D is supposed to be left over time for scan
            long n = (long) library.getScansPerDay() * D;

            List<Bock> bestBocks = library.getBocks();

            bestBocks = bestBocks.stream().limit(n).collect(Collectors.toList());

            library.setBocks(bestBocks);
        }

        return libraries;
    }

    private static void read(String file){
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
                            libraries.add(library);
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
