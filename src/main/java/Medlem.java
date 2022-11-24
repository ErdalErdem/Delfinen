public class Medlem {
    private String navn;
    private int alder;
    private String køn;
    private boolean typeMedlemskab;

    public Medlem(String navn, int alder, String køn, boolean typeMedlemskab) {
        this.navn = navn;
        this.alder = alder;
        this.køn = køn;
        this.typeMedlemskab = typeMedlemskab;
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

    public String getKøn() {
        return køn;
    }

    public void setKøn(String køn) {
        this.køn = køn;
    }
    public boolean getTypeMedlemskab() {
        return typeMedlemskab;
    }
    public void setTypeMedlemskab(boolean typeMedlemskab) {
        this.typeMedlemskab = typeMedlemskab;
    }


    public String toString() {
        return "Navn:" + " " + navn + "\nAlder:" + " " + alder + "\nKøn:" + køn + "\nType medlemskab:" + typeMedlemskab;
    }

}
