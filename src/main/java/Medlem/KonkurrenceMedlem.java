package Medlem;

public class KonkurrenceMedlem extends Medlem {

        public enum Discipliner {
            BUTTERFLY,
            CRAWL,
            RYGCRAWL,
            BRYSTSVØMNING
        }

    private String køn;

    private Discipliner disciplin;

        public KonkurrenceMedlem(String navn, String fødselsdato, String email, boolean erAktiv, String køn, Discipliner disciplin) {
            super(navn, fødselsdato, email, erAktiv);
            this.køn = køn;
            this.disciplin = disciplin;
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



    @Override
    public String toString() {
        return "Navn: " + getNavn() + " \nFødselsdato: " + getFødselsdato() + " \nE-mail: " + getEmail() + " \nAlder: " + getAlder() +
                " \nAktivitet: " + getErAktiv() + " \nKøn: " + køn + " \nDisciplin: " + disciplin;
    }

}
