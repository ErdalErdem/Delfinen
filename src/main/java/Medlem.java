import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

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
    private int alder; //Ændr til fødselsdato
    private String fødselsdato;
    //private String køn; //Kun relevant for konkurrencesvømmere
    private boolean erAktiv;
    private KonkurrenceMedlem info; //Til konkurrenceinfo klassen. Hvis dette er null er medlemmet motionist.
    //    private typeMedlem medlemType;

    public Medlem(String navn, String fødselsdato, boolean erAktiv) {
        this.navn = navn;
        //LocalDate fdato = LocalDate.parse(fødselsdato); //Parser får en instans af localdate, f.eks.: 1992-08-11
        this.fødselsdato = fødselsdato;
        this.alder = beregnAlder(this.fødselsdato); //Bruges til kontigent/statistik, bruges ikke i brugeroverfladen
        this.erAktiv = erAktiv;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getFødselsdato() {
        return fødselsdato;
    }

    public void setFødselsdato(String dato) {
        this.fødselsdato = dato;
    }

    public int beregnAlder(String fødselsdato) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM uuuu"); //Dato skal være af format f.eks. '03 Feb 2017'
        LocalDate fdato = LocalDate.parse(fødselsdato, formatter); //Parser får en instans af localdate
        return Period.between(fdato, LocalDate.now()).getYears();
    }

    public int getAlder() {
        return alder;
    }

    public boolean getErAktiv() {
        return erAktiv;
    }

    public void setErAktiv(boolean bool) {
        this.erAktiv = bool;
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

    public int beregnKontigent() {
        int kontigent;
        if (alder < 18){ //Junior
            kontigent = 1000;
        }
        else if (alder > 60) { //Senior over 60 år
            kontigent =  1200;
        }
        else if (!erAktiv) { //Passiv medlemskab
            kontigent = 500;
        }
        else { //Aktiv senior medlem
            kontigent = 1600;
        }
        return kontigent;
    }

    public String toString() {
        return "Navn: " + navn + " \nFødselsdato: " + fødselsdato + " \nAlder: " + alder + " \nAktivitet: " + erAktiv;
    }

}
