import java.util.ArrayList;

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
