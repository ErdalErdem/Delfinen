import java.io.FileNotFoundException;
import java.util.*;

public class Delfinen {

    public Database database = new Database();
    FileHandler fileHandler = new FileHandler();

    public ArrayList<Medlem> læsData() { //Indlæser data ved at kalde filehandlers readData metode
        return fileHandler.læsData();
    }


    public void opdaterData() {
        try {
            if (fileHandler.filEksisterer() && !læsData().isEmpty() && database.getMedlemDB().isEmpty()) {
                database.getMedlemDB().addAll(læsData());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void gemData() {
        fileHandler.gemData(database.getMedlemDB());
    }

}
