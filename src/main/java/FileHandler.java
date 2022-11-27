import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class FileHandler {


    public ArrayList<Medlem> læsData () {
        ArrayList<Medlem> readList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("Delfinen.csv")); //Læser csv filen med et scanner objekt
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] attributes = line.split(";"); //Splitter alle ";" af strenge i arrayet
                Medlem readMedlem = new Medlem(
                        attributes[0], //Denne attribut er navn
                        Integer.parseInt(attributes[1]), //Alder
                        attributes[2], //Køn
                        Medlem.typeMedlem.valueOf(attributes[3])); //Enum, type medlemsskab
                readList.add(readMedlem);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return readList;
    }

    public void gemData (ArrayList <Medlem> medlemmer) {
        try { //Try catch exception for the file handling
            PrintStream ps = new PrintStream(new File("Delfinen.csv"));
            for (Medlem m : medlemmer) { //Printer alle attributter for medlemmerne i csv filen
                ps.print(m.getNavn());
                ps.print(";");
                ps.print(m.getAlder());
                ps.print(";");
                ps.print(m.getKøn());
                ps.print(";");
                ps.print(m.getMedlemType());
                ps.println();
            }
            ps.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
