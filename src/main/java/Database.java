import java.util.ArrayList;

public class Database {

    private ArrayList<Medlem> medlemDB = new ArrayList<>();
    public Database() {
        ArrayList<Medlem> medlemDB;
    }
    public ArrayList<Medlem> getMedlemDB() {
        return medlemDB;
    }


    public void tilfoejMedlem(String navn, int alder, String køn, Medlem.typeMedlem medlemstype) {
        medlemDB.add(new Medlem(navn, alder, køn, medlemstype ));

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
