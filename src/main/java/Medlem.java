import java.time.LocalDate;
import java.time.Period;

public class Medlem {
/*    public enum typeMedlem { //Brug enum til discipliner i stedet
        junior,
        senior,
        passiv,
        aktiv,
        motionist,
        konkurrencesvømmer
    }*/

    private String navn;
    private LocalDate fødselsdato;
    private int alder; //Ændr til fødselsdato
    //private String køn; //Kun relevant for konkurrencesvømmere
    private boolean erAktiv;
    private KonkurrenceInfo info; //Til konkurrenceinfo klassen. Hvis dette er null er medlemmet motionist.
    //    private typeMedlem medlemType;

    public Medlem(String navn, int fødselsår, int fødselsmåned, int fødselsdag, boolean erAktiv) {
        this.navn = navn;
        this.fødselsdato = LocalDate.of(fødselsår, fødselsmåned, fødselsdag);
        //this.alder = Period.between(fødselsdato, LocalDate.now());
        this.erAktiv = erAktiv;
    }

    public String getNavn() {
        return navn;
    }
    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getAlder() {
        return alder;
    }

    public void setAlder(int alder) {
        this.alder = alder;
    }

    public boolean getErAktiv() {
        return erAktiv;
    }

/*    public String getKøn() {
        return køn;
    }

    public void setKøn(String køn) {
        this.køn = køn;
    }*/

/*    public typeMedlem getMedlemType(){
        return medlemType;
    }

    public void setMedlemType(typeMedlem medlemType) {
        this.medlemType = medlemType;
    }*/

/*    public String toString() {
        return "Navn: " + navn + " \nAlder: " + alder + " \nKøn: " + køn + " \nType medlemsskab: " + medlemType;
    }*/

    public String toString() {
        return "Navn: " + navn + " \nAlder: " + alder + " \nAktivitet: " + erAktiv;
    }

    public int beregnKontigent() {
        if (alder < 18){
            return 1000;
        }
        else {
            return 1500;
        }
    }
}
