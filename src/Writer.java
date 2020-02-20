import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {

private BufferedWriter bufferedWriter;

    public Writer(String filename) {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(String string){
        try {
            bufferedWriter.append(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void newLine(){
        write("\n");
    }

    private void close(){
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeResult(ArrayList<Library> libraries){
        write(String.valueOf(libraries.size()));
        newLine();
        for (Library library : libraries){
            write(String.valueOf(library.getId()));
            write("");
            write(String.valueOf(library.getBocks().size()));

            newLine();

            for (Bock book : library.getBocks()){
                write(String.valueOf(book.getId()));
                write("");
            }
            newLine();

        }
        close();
    }
}
