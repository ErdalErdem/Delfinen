public class Medlem {
    public enum typeMedlem {
        junior,
        senior,
        passiv,
        aktiv
    }

    private String navn;
    private int alder;
    private String køn;
    private typeMedlem medlemType;

    public Medlem(String navn, int alder, String køn, typeMedlem medlemType) {
        this.navn = navn;
        this.alder = alder;
        this.køn = køn;
        this.medlemType = medlemType;
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

    public String toString() {
        return "Navn:" + " " + navn + "\nAlder:" + " " + alder + "\nKøn:" + køn;
    }

}
