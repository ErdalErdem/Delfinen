package DataSource;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import Medlem.Medlem;
import Medlem.KonkurrenceMedlem;

public class FileHandler {


    public boolean filEksisterer(){
        boolean filEksistens = false;
        File f = new File("Delfinen.csv");
        if (f.exists()){
            filEksistens = true;
        }
        return filEksistens;
    }

    public ArrayList<Medlem> læsData () {
        ArrayList<Medlem> readList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("Delfinen.csv")); //Læser csv filen med et scanner objekt
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] attributes = line.split(";"); //Splitter alle ";" af strenge i arrayet
                boolean success;
                try {
                    Medlem readMedlem = new KonkurrenceMedlem(
                            attributes[0], //Denne attribut er navn
                            attributes[1], //Fødselsår
                            attributes[2], //E-mail addresse
                            parseBoolean(attributes[3]),//Boolean.parseBoolean(attributes[3]), //Er medlemmet aktiv
                            attributes[4], //Medlemmets køn
                            KonkurrenceMedlem.Discipliner.valueOf(attributes[5])); //Medlemmets svømmedisciplin
                    readList.add(readMedlem);
                    success = true;
                }
                catch (Exception e) {success = false;}
                if (!success) {
                    try {
                        Medlem readMedlem = new Medlem(
                                attributes[0], //Denne attribut er navn
                                attributes[1], //Fødselsår
                                attributes[2], //E-mail addresse
                                Boolean.parseBoolean(attributes[3]));
                        readList.add(readMedlem);
                        success = true;
                    }
                    catch (Exception e) {success = false;}
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return readList;
    }

    public void gemData (ArrayList <Medlem> medlemmer) {
        try { //Try catch exception for the file handling
            PrintStream ps = new PrintStream("Delfinen.csv");
            for (Medlem m : medlemmer) { //Printer alle attributter for medlemmerne i csv filen
                if (m instanceof KonkurrenceMedlem km){
                    ps.print(km.getNavn());
                    ps.print(";");
                    ps.print(km.getFødselsdato());
                    ps.print(";");
                    ps.print(km.getEmail());
                    ps.print(";");
                    ps.print(km.getErAktiv());
                    ps.print(";");
                    ps.print(km.getKøn());
                    ps.print(";");
                    ps.print(km.getDiscipliner());
                    ps.println();
                }
                else {
                    ps.print(m.getNavn());
                    ps.print(";");
                    ps.print(m.getFødselsdato());
                    ps.print(";");
                    ps.print(m.getEmail());
                    ps.print(";");
                    ps.print(m.getErAktiv());
                    ps.println();
                }
            }
            ps.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean parseBoolean(String s) {
        //boolean bool = Boolean.parseBoolean(s);
        return s.equals("Aktiv");
    }

}
