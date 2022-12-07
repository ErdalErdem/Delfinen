package Medlem;

import java.time.LocalDate;
import java.util.ArrayList;

public class KonkurrenceMedlem extends Medlem {

    public enum Discipliner {
        BUTTERFLY,
        CRAWL,
        RYGCRAWL,
        BRYSTSVØMNING
    }

    private String køn;

    private int træningsresultat;
    private String dato;
    private String konkurrence;
    private String stævne;
    private int placering;
    private int tid;
    private Discipliner disciplin;

    public KonkurrenceMedlem(String navn, String fødselsdato, String email, boolean erAktiv, String køn, Discipliner disciplin) {
        super(navn, fødselsdato, email, erAktiv);
        this.køn = køn;
        this.disciplin = disciplin;
        this.træningsresultat = 0;
        this.dato = "";
        this.konkurrence = "";
        this.stævne = "";
        this.placering = 0;
        this.tid = 0;
    }

    public String getKøn() {
        return køn;
    }

    public void setKøn(String køn) {
        this.køn = køn;
    }

    public Discipliner getDiscipliner(){
        return disciplin;
    }

    public void setDisciplin(Discipliner disciplin) {
        this.disciplin = disciplin;
    }

    public int getTræningsresultat() {
        return træningsresultat;
    }

    public void setTræningsresultat(int tr) {
        if (this.træningsresultat > tr || træningsresultat == 0){ //Overwriter kun træningsresultat hvis det nye resultat er 'bedre' end den forrige.
            this.træningsresultat = tr;
        }
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String s) {
        this.dato = s;
    }

    public String getKonkurrence() {
        return konkurrence;
    }

    public void setKonkurrence(String k) {
        this.konkurrence = k;
    }

    public String getStævne() {
        return stævne;
    }

    public void setStævne(String s) {
        this.stævne = s;
    }

    public int getPlacering() {
        return placering;
    }

    public void setPlacering(int p) {
        this.placering = p;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int t) {
        this.tid = t;
    }


    public String konverterTid(int sekunder) {
        int min = (sekunder % 3600) / 60;
        int sek = sekunder % 60;
        String sm = (min < 10 && min > 0 ? "0" : "") + (min > 0 ? (sek == 0 ? String.valueOf(min) : String.valueOf(min) + " " + "min") : "");
        String ss = (sek == 0 && (min > 0) ? "" : (sek < 10 && (min > 0) ? "0" : "") + String.valueOf(sek) + " " + "sek");
        return sm + (min > 0 ? " " : "") + ss;
    }

    @Override
    public String toString() {
        return "ID: " + getID() + " \nNavn: " + getNavn() + " \nFødselsdato: " + getFødselsdato() + " \nE-mail: " + getEmail() + " \nAlder: " + getAlder() +
                " \nAktivitet: " + getErAktiv() + " \nKøn: " + køn + " \nBedste træningsresultat: " + konverterTid(træningsresultat) + " \nDisciplin: " + disciplin;
    }

}
