package UI;

import java.io.FileNotFoundException;
import java.util.*;
import Delfinen.*;
import Medlem.*;

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
                    6. Angiv indtægt for alle medlemmer
                    7. Sorter konkurrencesvømmere
                    9. Afslut""");

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
                case 6 -> kontigentFormatPrint();
                case 7 -> sorterMedlemmer();
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

        if (erAktiv){
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
                    KonkurrenceMedlem km = new KonkurrenceMedlem(navn, fødselsdato, email, true, køn, disciplin); //Opretter konkurrencemedlem
                    delfinen.database.tilfoejKonkurrenceMedlem(km); //Tilføjer konkurrencemedlem
                    System.out.println(km);
                } catch (IllegalArgumentException e) {
                    System.out.println("Disciplintypen findes ikke. \nMedlem kunne ikke oprettes.");
                }
            }
            else {
                Medlem m = new Medlem(navn, fødselsdato, email, true); //Opretter aktiv medlem som ikke er konkurrencemedlem
                delfinen.database.tilfoejMedlem(m);
                System.out.println(m);
            }
        }
        else {
            Medlem m = new Medlem(navn, fødselsdato, email, false); //Opretter passiv medlem
            delfinen.database.tilfoejMedlem(m);
            System.out.println(m);
        }
    }

    public void visMedlemmer() {
        try {
            formatPrint(delfinen.læsData()); //System.out.println(delfinen.læsData()); //Viser alle medlemmer fra csv filen
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
                        System.out.println("Er medlemmet aktiv?");
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


    public void sorterMedlemmer() {
        System.out.println("""
                        Hvad vil du ændre?
                        1. Alder
                        2. Ikke lavet endnu
                        """);
        scanner.nextLine();
        int brugerInput = readInt();
        switch (brugerInput) {
            case 1 -> alderFormatPrint(delfinen.sorterAlder(delfinen.læsData())); //System.out.println(delfinen.sorterAlder(delfinen.læsData()));
            case 2 -> System.out.println("Ikke implementeret endnu");
        }
    }


    private int readInt() {
        while (!scanner.hasNextInt()) {
            String text = scanner.next();
            System.out.println(text + " " + "Invalid data, enter a valid integer.");
        }
        //int i = scanner.nextInt();
        //scanner.nextLine();
        //return i;
        return scanner.nextInt();
    }

    private boolean readBool() {
        boolean bool = false;
        while (!scanner.nextLine().matches("ja|nej") /*!svar.equalsIgnoreCase("ja") || !svar.equalsIgnoreCase("nej")*/){
            System.out.println(scanner.nextLine() + " " + "Indtast venligst et passende svar.");
        }
        if (scanner.nextLine().equalsIgnoreCase("ja")) {
            bool = true;
        }
        return bool;
    }

    private void formatPrint(ArrayList<Medlem> sorteringList) {
        System.out.printf("┃ %-20s │ %-15s │ %-25s │ %-12s │ %-15s ┃ %-15s ┃%n", "Navn", "Fødselsdato", "E-mail", "Aktivitet", "Køn", "Svømmedisciplin");
        for (Medlem m : sorteringList) {
            if (m instanceof KonkurrenceMedlem km){
                System.out.printf("┃ %-20s │ %-15s │ %-25s │ %-12s │ %-15s ┃ %-15s ┃%n", km.getNavn(), km.getFødselsdato(),
                        km.getEmail(), km.getErAktiv(), km.getKøn(), km.getDiscipliner());
            }
            else {
                System.out.printf("┃ %-20s │ %-15s │ %-25s │ %-12s │ %-15s ┃ %-15s ┃%n", m.getNavn(), m.getFødselsdato(), m.getEmail(), m.getErAktiv(), "", "");
            }
        }
    }

    private void kontigentFormatPrint () {
        for (Medlem m : delfinen.læsData()){
            System.out.printf("%-15s %15s %20s %n", "Navn: " + m.getNavn(), "Alder: " + m.getAlder(), "Kontigent: " + m.beregnKontigent());
        }
        System.out.printf("%-20s %n%n", "Samlet årlig kontigent: " + delfinen.database.beregnSamletKontigent());
    }

    private void alderFormatPrint(ArrayList<KonkurrenceMedlem> sorteringList) {
        System.out.printf("┃ %-20s │ %-15s │ %-25s │ %-12s │ %-15s ┃ %-15s ┃%n", "Navn", "Fødselsdato", "E-mail", "Aktivitet", "Køn", "Svømmedisciplin");
        for (KonkurrenceMedlem km : sorteringList) {
            if (km.getAlder() < 18){
                System.out.printf("┃ %-20s │ %-15s │ %-25s │ %-12s │ %-15s ┃ %-15s ┃%n", "Junior: " + km.getNavn(), km.getFødselsdato(),
                        km.getEmail(), km.getErAktiv(), km.getKøn(), km.getDiscipliner());
            }
            else if (km.getAlder() > 18){
                System.out.printf("┃ %-20s │ %-15s │ %-25s │ %-12s │ %-15s ┃ %-15s ┃%n", "Senior: " + km.getNavn(), km.getFødselsdato(),
                        km.getEmail(), km.getErAktiv(), km.getKøn(), km.getDiscipliner());
            }
        }
    }

}




