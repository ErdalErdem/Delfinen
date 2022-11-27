import java.util.ArrayList;

public class Database {

    private ArrayList<Medlem> medlemDB = new ArrayList<>();

    public ArrayList<Medlem> getMedlemDB() {
        return medlemDB;
    }


    public void tilfoejMedlem(String navn, int alder, String køn, Medlem.typeMedlem medlemstype) {
        medlemDB.add(new Medlem(navn, alder, køn, medlemstype ));
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


}
