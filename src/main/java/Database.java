import java.util.ArrayList;

public class Database {

    private ArrayList<Medlem> medlemDB = new ArrayList<>();

    public ArrayList<Medlem> getMedlemDB() {
        return medlemDB;
    }


/*    public void tilfoejMedlem(String navn, int alder, String køn, Medlem.typeMedlem medlemstype) {
        medlemDB.add(new Medlem(navn, alder, køn, medlemstype ));
    }*/


    public void tilfoejMedlem(String navn, String fødselsdato, boolean erAktiv) {
        medlemDB.add(new Medlem(navn, fødselsdato, erAktiv));
    }

    public void tilfoejKonkurrenceMedlem (String navn, String fødselsdato, boolean erAktiv, String køn, KonkurrenceMedlem.discipliner disciplin) {
        medlemDB.add(new KonkurrenceMedlem(navn, fødselsdato, erAktiv, køn, disciplin));
    }

    public Medlem findMedlem(String navn){
        for (Medlem m : medlemDB){
            if (m.getNavn().equals(navn)){
                return m;
            }
        }
        return null;
    }


    public void sletMedlem (String navn){
        for (int i = 0; i < medlemDB.size(); i++){
            Medlem medlem = medlemDB.get(i);
            if (medlem.getNavn().equals(navn)){
                medlemDB.remove(medlem);
            }
        }
    }

    public int beregnSamletKontigent() { //Kasserer metode
        int samletKontigent = 0;
        for (Medlem m : medlemDB){
            samletKontigent += m.beregnKontigent();
        }
        return samletKontigent;
    }

}
