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
    private static long days;

    public static void main(String[] args) {
        System.out.println(("Hello World"));

        Main main = new Main();
        String[] files = {"a_example", "b_read_on","c_incunabula","d_tough_choices","e_so_many_books", "f_libraries_of_the_world"};
        for(String file : files){
            main.read(file + ".txt");

            // DUMMY
            long D = days;
            // List<Library> librariesToScan = getLibrariesToDo(libraries, D);
            /*List<Library> librariesToScan = getLibrariesWithFactor(libraries, D);
            removeDuplicateBooks(librariesToScan);
            librariesToScan = getBooksToDo(librariesToScan, D);*/

            List<Library> librariesToScan = anotherLibraryGetter(libraries, D);
            librariesToScan = removeEmptyLibraries(librariesToScan);

            Writer writer = new Writer("./out/"+file+"output.txt");
            writer.writeResult(librariesToScan);
        }

    }


    private static List<Library> anotherLibraryGetter(List<Library> libraries, long D){
        long daysLeft = D;
        List<Library> actualLibraries = new ArrayList<>();
        Set<Bock> addedBooks = new HashSet<>();

        while (daysLeft > 0 && libraries.size() > 0){
            for (Library l : libraries){
                // Remove already added books...
                Iterator<Bock> iter = l.getBocks().iterator();
                while (iter.hasNext()){
                    Bock b = iter.next();
                    if (addedBooks.contains(b)){
                        iter.remove();
                    }
                }
                getScoreForLibrary(l, daysLeft);
            }

            libraries = libraries.stream()
                    .sorted(Comparator.comparingLong(Library::getScore).reversed())
                    .collect(Collectors.toList());

            actualLibraries.add(libraries.get(0));

            // Add added books to already added ones...
            for (Bock b : libraries.get(0).getBocks()){
                addedBooks.add(b);
            }

            libraries.remove(0);

            daysLeft -= actualLibraries.get(0).getTimeToSignUp();
        }

        return actualLibraries;
    }

    private static void getScoreForLibrary(Library lib, long D){
        long daysForScanning = D - lib.getTimeToSignUp();

        if (daysForScanning > 0){
            sortBooksDesc(lib);

            List<Bock> books = getPossibleBooks(lib.getScansPerDay()*daysForScanning, lib.getBocks());

            lib.setScore(books.stream().filter(b -> b.getScore() > 0).mapToLong(b -> b.getScore()).sum());
        }
    }













    private static List<Library> removeEmptyLibraries(List<Library> libraries)
    {
        return libraries.stream().filter(iter -> iter.getBocks().size() > 0).collect(Collectors.toList());
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
            l.setFactor((n-l.getTimeToSignUp())*bookScoreSum);
        }

        List<Library> librariesOrdered = libraries.stream()
                .sorted(Comparator.comparingLong(Library::getFactor).reversed())
                .collect(Collectors.toList());

        List<Library> librariesToScan = new ArrayList<>();
        int lDays = 0;
        for(Library l : librariesOrdered){
            lDays += l.getTimeToSignUp();
            if (lDays <= D){
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

        int lDays = 0;
        List<Library> librariesToScan = new ArrayList<>();

        for(Library l : librariesOrdered){
            lDays += l.getTimeToSignUp();
            if (lDays <= D){
                librariesToScan.add(l);
            }
        }

        return librariesToScan;
    }

    private static List<Library> getBooksToDo(List<Library> libraries, int D)
    {
        for (Library library : libraries)
        {
            sortBooksDesc(library);
            //TODO: D is supposed to be left over time for scan
            long n = (long) library.getScansPerDay() * D;

            List<Bock> bestBocks = library.getBocks();

            bestBocks = getPossibleBooks(n, bestBocks);

            library.setBocks(bestBocks);
        }

        return libraries;
    }

    private static List<Bock> getPossibleBooks(long n, List<Bock> bestBocks) {
        return bestBocks.stream().limit(n).collect(Collectors.toList());
    }

    private static void sortBooksDesc(Library library) {
        library.setBocks(
                library.getBocks().stream().sorted(Comparator.comparingInt(Bock::getScore).reversed()).collect(Collectors.toList())
        );
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
