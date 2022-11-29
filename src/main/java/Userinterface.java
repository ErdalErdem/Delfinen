import java.io.FileNotFoundException;
import java.util.Scanner;

public class Userinterface {
    private Delfinen delfinen = new Delfinen();

    Scanner scanner;

    public void startProgram() {
        scanner = new Scanner(System.in);
        int menuValg;
        boolean dataÆndret = false; //Boolean som kun er true hvis man har ændret noget i databasen
        delfinen.opdaterData(); //Sørger for at programmet ikke overwriter en gammel csv fil

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
                case 1 -> {
                    tilfoejMedlem();
                    dataÆndret = true;
                }
                case 2 -> {
                    visMedlemmer();
                }
                case 3 ->{
                    søgMedlem();
                }
                case 4 -> {
                    redigerMedlem();
                    dataÆndret = true;
                }
                case 5 -> {
                    sletMedlem();
                    dataÆndret = true;
                }
            }
            if (dataÆndret) {
                delfinen.gemData();
            }
        } while (menuValg != 9);
    }

    public void tilfoejMedlem() {
        System.out.println("Navn:");
        scanner.nextLine();
        String navn = scanner.nextLine();

        System.out.println("Fødselsdato (Format: 'dd/MM/yyyy'): ");
        String fødselsdato = scanner.nextLine();

        System.out.println("E-mail addresse: ");
        String email = scanner.nextLine();

        System.out.println("Er du aktiv?: ");
        boolean erAktiv;
        erAktiv = readBool();

        System.out.println("Er du konkurrencesvømmer?: ");
        boolean konkurrenceSvømmer;
        konkurrenceSvømmer = readBool();
        if (konkurrenceSvømmer) {
            System.out.println("Hvad er dit køn?: ");
            String køn = scanner.nextLine();

            System.out.println("Hvilken svømmedisciplin konkurrerer du i?: ");
            KonkurrenceMedlem.Discipliner disciplin = null;
            try {
                disciplin = KonkurrenceMedlem.Discipliner.valueOf(scanner.nextLine().toUpperCase());
                KonkurrenceMedlem km = new KonkurrenceMedlem(navn, fødselsdato, email, erAktiv, køn, disciplin);
                delfinen.database.tilfoejKonkurrenceMedlem(km);
                System.out.println(km);
            } catch (IllegalArgumentException e) {
                System.out.println("Disciplintypen findes ikke. \nMedlem kunne ikke oprettes.");
            }
        }
        else {
            Medlem m = new Medlem(navn, fødselsdato, email, erAktiv);
            delfinen.database.tilfoejMedlem(m);
            System.out.println(m);
        }
    }

    public void visMedlemmer() {
        try {
            System.out.println(delfinen.læsData()); //Viser alle medlemmer fra csv filen
        }
        catch (Exception e) {
            System.out.println("Fil kunne ikke findes");
        }
    }


    public void søgMedlem() {
        System.out.println("Indtast navn på medlem");
        scanner.nextLine(); //Scanner bug
        String navn = scanner.nextLine();
        try {
            Medlem m = delfinen.database.findMedlem(navn);
            if (m != null){
                System.out.println(m);
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
                        2. Fødselsdato
                        3. E-mail
                        4. Aktivitet
                        5. Konkurrencesvømmer info
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
                        System.out.println("Indtast ny fødselsdato");
                        scanner.nextLine();
                        String nyFDato = scanner.nextLine();
                        m.setFødselsdato(nyFDato);
                    }
                    case 3 -> {
                        System.out.println("Indtast email");
                        scanner.nextLine();
                        String nyEmail = scanner.nextLine();
                        m.setEmail(nyEmail);
                    }
                    case 4 -> {
                        System.out.println("Indtast aktivitet");
                        scanner.nextLine();
                        boolean nyAktivitet = readBool();
                        m.setErAktiv(nyAktivitet);
                    }
                    case 5 -> {
                        System.out.println("""
                        Hvilke oplysninger vil du ændre?
                        1. Køn
                        2. Svømmedisciplin
                        """);
                        scanner.nextLine();
                        int kmValg = readInt();
                        if (m instanceof KonkurrenceMedlem km){
                            if (kmValg == 1) {
                                    System.out.println("Indtast køn");
                                    scanner.nextLine();
                                    String nytKøn = scanner.nextLine();
                                    km.setKøn(nytKøn);
                            }
                            else if (kmValg == 2) {
                                System.out.println("Indtast disciplin");
                                scanner.nextLine();
                                KonkurrenceMedlem.Discipliner nyDisciplin = KonkurrenceMedlem.Discipliner.valueOf(scanner.nextLine().toUpperCase());
                                km.setDisciplin(nyDisciplin);
                            }
                        }
                        else {
                            System.out.println("Medlemmet er ikke en konkurrencesvømmer");
                        }
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
            delfinen.database.sletMedlem(m.getNavn());
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

    public boolean readBool() {
        boolean bool = false;
        while (!scanner.nextLine().matches("ja|nej") /*!svar.equalsIgnoreCase("ja") || !svar.equalsIgnoreCase("nej")*/){
            System.out.println(scanner.nextLine() + " " + "Indtast venligst et passende svar.");
        }
        if (scanner.nextLine().equalsIgnoreCase("ja")) {
            bool = true;
        }
        return bool;
    }
}




