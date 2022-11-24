import java.util.ArrayList;

public class Database {

    private ArrayList<Medlem> medlemDB = new ArrayList<>();

    public ArrayList<Medlem> getMedlemDB() {
        return getMedlemDB();
    }

    public void tilfoejMedlem(String navn, int alder, String køn, Medlem.typeMedlem medlemstype) {
        Medlem medlem = new Medlem(navn, alder, køn, medlemstype);
        medlemDB.add(medlem);
    }

    public Medlem findMedlem(String navn){
        for (Medlem medlem : medlemDB){
            if (medlem.getNavn().equals(navn)){
                return medlem;
            }
        }
        return null;
    }

    public void redigerMedlem (String navn, int alder, String køn, Medlem.typeMedlem medlemstype) {
        Medlem medlem = new Medlem(navn, alder, køn, medlemstype);
    }

    public void sletMedlem (String navn){
        for (Medlem medlem : medlemDB){
            if (medlem.getNavn().equals(navn)){
                medlemDB.remove(medlem);
            }
        }
    }


}
