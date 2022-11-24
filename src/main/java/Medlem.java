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

    public Medlem(String navn, int alder, String køn) {
        this.navn = navn;
        this.alder = alder;
        this.køn = køn;
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
