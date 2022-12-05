package Medlem;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Medlem {

    private String navn;
    private int alder; //Ændr til fødselsdato
    private String fødselsdato;

    private String email;
    private boolean erAktiv;
    private boolean harGæld;

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

    public boolean getHarGæld() {
        return harGæld;
    }

    public void setHarGæld(boolean bool) {
        this.harGæld = bool;
    }

    public int beregnAlder(String fødselsdato) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu"); //Dato skal være af format f.eks. '03/02/2017'
        LocalDate fdato = LocalDate.parse(fødselsdato, formatter); //Parser får en instans af localdate
        return Period.between(fdato, LocalDate.now()).getYears();
    }

    public int getAlder() {
        return alder;
    }

    public String getErAktiv() {

        return boolToWord(erAktiv);
    }

    public void setErAktiv(boolean bool) {
        this.erAktiv = bool;
    }

    public int beregnKontigent() {
        int kontigent = 0;
        if (!harGæld){
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
        }
        return kontigent;
    }

    public int beregnGæld() {
        int gæld = 0;
        if (harGæld) {
            if (alder < 18) { //Junior
                gæld = -1000;
            } else if (alder > 60) { //Senior over 60 år
                gæld = -1200;
            } else if (!erAktiv) { //Passiv medlemskab
                gæld = -500;
            } else { //Aktiv senior medlem
                gæld = -1600;
            }
        }
        return gæld;
    }


    public String boolToWord(boolean bool){
        return (bool ? "Aktiv":"Passiv");
    }

    @Override
    public String toString() {
        return "Navn: " + navn + " \nFødselsdato: " + fødselsdato + " \nE-mail: " + email +  " \nAlder: " + alder + " \nAktivitet: " + boolToWord(erAktiv);
    }

}
