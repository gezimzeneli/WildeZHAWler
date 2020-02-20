import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(("Hello World"));

        // DUMMY
        getLibrariesToDo(new ArrayList<>(), 5);
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

        }


        return null;
    }
}
