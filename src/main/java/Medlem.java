import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    private String email;
    private boolean erAktiv;


    private ArrayList<SortAlder> sortAlder = new ArrayList<SortAlder>();

    public ArrayList<SortAlder> sortAlders(){
        return sortAlders();
    }


    public Medlem(String navn, String fødselsdato, String email, boolean erAktiv) {
        this.navn = navn;
        this.fødselsdato = fødselsdato;
        this.email = email;
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

    public String getEmail () {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int beregnAlder(String fødselsdato) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu"); //Dato skal være af format f.eks. '03/02/2017'
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

   /* public void comparatorSort(String type) {
       ArrayList<SortAlder> SortAlder;

        Comparator<SortAlder> comparator = comparatorSelection(type);

        Collections.sort(SortAlder, comparator);
    }

    private Comparator<SortAlder> comparatorSelection(String type) {
    }

/*
    public void comparatorSort(String type, String sType) {

        Comparator<Superhero> comparator = comparatorSelection(type);
        Comparator<Superhero> comparatorS = comparatorSelection(sType);

        Collections.sort(database.getHeroDatabase(), comparator.thenComparing(comparatorS));
    }

 */

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

    @Override
    public String toString() {
        return "Navn: " + navn + " \nFødselsdato: " + fødselsdato + " \nE-mail: " + email +  " \nAlder: " + alder + " \nAktivitet: " + erAktiv;
    }


    public class SortAlder {

    }
}
