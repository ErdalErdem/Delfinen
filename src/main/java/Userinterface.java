import java.util.Scanner;

public class Userinterface {
    private Delfinen delfinen = new Delfinen();

    Scanner scanner;

    public void startProgram() {
        scanner = new Scanner(System.in);
        int menuValg = 0;

        System.out.println("""
                Velkommen til Delfin svømme klubben.
                """);
        do {
            System.out.println("""
                    1. Opret medlem
                    2. Vis alle medlemmer
                    3. Søg efter medlems navn
                    4. Rediger medlems info
                    5. Slet medlem
                    9. afslut""");

            menuValg = readInt();

            switch (menuValg) {
                case 1 ->
                    tilfoejMedlem();
                case 2 ->
                    visMedlemmer();
                case 3 ->
                    findMedlem();
                case 4 ->
                    redigerMedlem();
                case 5 ->
                    sletMedlem();
            }
        } while (menuValg != 9);
    }

    public void tilfoejMedlem() {
        scanner = new Scanner(System.in);
        System.out.println("Navn:");
        String navn = scanner.nextLine();

        System.out.println("\nAlder: ");
        int alder = scanner.nextInt();

        scanner = new Scanner(System.in);
        System.out.println("Køn:");
        String køn = scanner.nextLine();

        System.out.println("Type medlemsskab");
        Medlem.typeMedlem medlemstype = null;
        try {
            medlemstype = Medlem.typeMedlem.valueOf(scanner.nextLine().toLowerCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Medlemstypen findes ikke. \nMedlem kunne ikke oprettes.");
        }

        if (medlemstype != null) {
            delfinen.database.tilfoejMedlem(navn, alder, køn, medlemstype /*Medlem.typeMedlem.junior*/);
            System.out.println("\nNavn: " + navn + " \nAlder: " + alder  + " \nKøn: "  + køn + " \nType medlemsskab: " + medlemstype);
        }
    }

    public void visMedlemmer() {
        if (delfinen.database.getMedlemDB().isEmpty()) {
            System.out.println("Intet medlem fundet");
        } else {
            for (Medlem m : delfinen.database.getMedlemDB()) {
                //System.out.println("\nNavn: " + m.getNavn() + " \nAlder: " + m.getAlder()  + " \nKøn: "  + m.getKøn() + " \nType medlemsskab: " + m.getMedlemType());
                System.out.println(m);
            }
        }
    }

    public void findMedlem() {
        System.out.println("Indtast navn på medlem");
        scanner.nextLine(); //Scanner bug
        String navn = scanner.nextLine();
        try {
            Medlem m = delfinen.database.findMedlem(navn);
            if (m != null){
                System.out.println(m);
                //System.out.println("\nNavn: " + m.getNavn() + " \nAlder: " + m.getAlder() + " \nKøn: " + m.getKøn() + " \nType medlemsskab: " + m.getMedlemType());
            }
            else {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException e){
            System.out.println("Medlem kunne ikke findes ");
        }
    }

    public void redigerMedlem() {
        System.out.println("Indtast navn på medlem");
        scanner.nextLine();
        String navn = scanner.nextLine();
        try {
            Medlem m = delfinen.database.findMedlem(navn);
            if (m == null) {
                throw new NullPointerException();
            }
            else {
                System.out.println("""
                        Hvad vil du ændre?
                        1. Navn
                        2. Alder
                        3. Køn
                        4. Medlemstype
                        """);
                int brugerValg = readInt();
                switch (brugerValg) {
                    case 1 -> {
                        System.out.println("Indtast det nye navn");
                        scanner.nextLine();
                        String nytNavn = scanner.nextLine();
                        m.setNavn(nytNavn);
                    }
                    case 2 -> {
                        System.out.println("Indtast den nye alder");
                        scanner.nextLine();
                        int nyAlder = readInt();
                        m.setAlder(nyAlder);
                    }
                    case 3 -> {
                        System.out.println("Indtast det nye køn");
                        scanner.nextLine();
                        String nytKøn = scanner.nextLine();
                        m.setKøn(nytKøn);
                    }
                    case 4 -> {
                        System.out.println("Indtast den nye type medlemsskab");
                        scanner.nextLine();
                        String nytMedlemsskab = scanner.nextLine();
                        m.setMedlemType(Medlem.typeMedlem.valueOf(nytMedlemsskab));
                    }
                }
            }
        }
        catch (NullPointerException e){
            System.out.println("Medlem kunne ikke findes ");
        }
    }

    public void sletMedlem(){
        System.out.println("Indtast navn på medlem du vil slette");
        scanner.nextLine();
        String navn = scanner.nextLine();
        try {
            Medlem m = delfinen.database.findMedlem(navn);
            System.out.println("\nSlettet: " + m.getNavn());
            delfinen.database.getMedlemDB().remove(m);
        }
        catch (NullPointerException e){
            System.out.println("Medlem kunne ikke findes ");
        }
    }


    public int readInt() {
        while (!scanner.hasNextInt()) {
            String text = scanner.next();
            System.out.println(text + " " + "Invalid data, enter a valid integer.");
        }
        return scanner.nextInt();
    }

}




