package Database;

import Medlem.*;
import Comparator.*;
import java.util.*;

public class Database {

    private ArrayList<Medlem> medlemDB = new ArrayList<>();

    public ArrayList<Medlem> getMedlemDB() {
        return medlemDB;
    }

    public void tilfoejMedlem(Medlem m) {
        medlemDB.add(m);
    }

    public void tilfoejKonkurrenceMedlem (KonkurrenceMedlem km) {
        medlemDB.add(km);
    }


    public Medlem findMedlem(String ID){
        for (Medlem m : medlemDB){
            if (m.getID().equals(ID)){
                return m;
            }
        }
        return null;
    }

    public void sletMedlem (String ID){
        for (int i = 0; i < medlemDB.size(); i++){
            Medlem medlem = medlemDB.get(i);
            if (medlem.getID().equals(ID)){
                medlemDB.remove(medlem);
            }
        }
    }

    public int beregnSamletBalance() {
        int balance = 0;
        for (Medlem m : medlemDB){
            if (!m.getHarGæld())
                balance += m.beregnKontigent();
        }
        return balance;
    }

    //Sortering

    public ArrayList<KonkurrenceMedlem> sorterAlder(ArrayList<Medlem> usorteretListe) {
        ArrayList<KonkurrenceMedlem> alderListe = new ArrayList<>();
        for (Medlem  m : usorteretListe) {
            if (m instanceof KonkurrenceMedlem) {
                KonkurrenceMedlem km = (KonkurrenceMedlem) m;
                alderListe.add(km);
            }
        }
        Collections.sort(alderListe, new AlderComparator());
        return alderListe;
    }

/*    public ArrayList<KonkurrenceMedlem> sorterResultater(ArrayList<KonkurrenceMedlem> usorteretListe) {
        ArrayList<KonkurrenceMedlem> resultatListe = new ArrayList<>();
        for (Medlem  m : usorteretListe) {
            if (m instanceof KonkurrenceMedlem) {
                KonkurrenceMedlem km = (KonkurrenceMedlem) m;
                resultatListe.add(km);
            }
        }
        Collections.sort(resultatListe, new ResultatComparator());
        return resultatListe;
    }*/

    public void sorterResultater(ArrayList<KonkurrenceMedlem> usorteretListe) {
        usorteretListe.sort(new ResultatComparator());
    }


    public ArrayList<KonkurrenceMedlem> bedsteSvømmere(String disciplinnavn) {
        ArrayList<KonkurrenceMedlem> bedsteSvømmereListe = new ArrayList<>();
        for (Medlem m : medlemDB){
            if (m instanceof KonkurrenceMedlem km){
                for (KonkurrenceMedlem.Discipliner d : KonkurrenceMedlem.Discipliner.values()){ //Looper igennem konstanterne i Discipliner
                    if (d.name().equals(disciplinnavn) && km.getDiscipliner().equals(d.valueOf(disciplinnavn))){
                        bedsteSvømmereListe.add(km);
                        sorterResultater(bedsteSvømmereListe);
                        for (int i = 0; i < bedsteSvømmereListe.size(); i++){
                            if (bedsteSvømmereListe.get(i).getTræningsresultat() == 0) { //Fjerner medlemmer med træningsresultat 0
                                bedsteSvømmereListe.remove(i);
                            }
                            if (i > 4){ //Hvis listen er større end 5
                                bedsteSvømmereListe.remove(i);
                            }
                        }
                    }
                }
            }
        }
        return bedsteSvømmereListe;
    }
}
