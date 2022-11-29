public class KonkurrenceMedlem extends Medlem {

        public enum discipliner {
            butterfly,
            crawl,
            rygcrawl,
            brystsvømning
        }

    private String køn;

    private discipliner disciplin;

        public KonkurrenceMedlem(String navn, String fødselsdato, boolean erAktiv, String køn, discipliner disciplin) {
            super(navn, fødselsdato, erAktiv);
            this.køn = køn;
            this.disciplin = disciplin;
        }

        public String getKøn() {
            return køn;
        }

        public void setKøn(String køn) {
            this.køn = køn;
        }

        public discipliner getDiscipliner(){
            return disciplin;
        }

        public void setDisciplin(discipliner disciplin) {
            this.disciplin = disciplin;
        }


}
