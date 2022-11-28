public class KonkurrenceMedlem {
    public class KonkurrenceInfo extends Medlem {

        public enum discipliner {
            butterfly,
            crawl,
            rygcrawl,
            brystsvømning
        }

        private String køn;

        private discipliner disciplin;

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

        public KonkurrenceInfo(String navn, int alder, String køn, boolean erAktiv) {
            super(navn, alder, køn, erAktiv);
        }
    }

}
