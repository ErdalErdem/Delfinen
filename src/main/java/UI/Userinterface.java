package UI;

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
                                   YAao,
                                    Y8888b,
                                  ,oA8888888b,
                            ,aaad8888888888888888bo,
                         ,d888888888888888888888888888b,
                       ,888888888888888888888888888888888b,
                      d8888888888888888888888888888888888888,
                     d888888888888888888888888888888888888888b
                    d888888P'                    `Y888888888888,
                    88888P'                    Ybaaaa8888888888l
                   a8888'                      `Y8888P' `V888888
                 d8888888a                                `Y8888
                AY/'' `\\Y8b                                 ``Y8b
                Y'      `YP                                    ~~
                         `'
                Velkommen til svømmeklubben Delfinen!
                Hvad kan jeg hjælpe dig med i dag?
                """);
        do {
            System.out.println("""
                    1. Vis medlem oplysninger (Formand)
                    2. Vis årlig indtægt (Kasserer)
                    3. Vis konkurrencesvømmer statistik (Træner)
                    9. Afslut""");

            menuValg = readInt();

            if (menuValg == 1) {
                System.out.println("""
                    1. Opret medlem
                    2. Vis alle medlemmer
                    3. Søg efter medlems navn
                    4. Redigér medlems info
                    5. Slet medlem
                    9. Afslut""");
                int underMenuValg = readInt();
                switch (underMenuValg) {
                    case 1 -> {
                        tilfoejMedlem();
                        dataÆndret = true;
                    }
                    case 2 -> {
                        visMedlemmer();
                    }
                    case 3 -> {
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
                    case 9 -> menuValg = 9;
                }
            }
            else if (menuValg == 2) {
                kontigentFormatPrint();
            }
            else if (menuValg == 3) {
                konkurrenceSvømmere();
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

        System.out.println("Er du aktiv? (ja/nej): ");
        String s = scanner.nextLine();
        boolean erAktiv = readBool(s);
        if (erAktiv){
            System.out.println("Er du konkurrencesvømmer? (ja/nej): ");
            String s2 = scanner.nextLine();
            boolean konkurrenceSvømmer = readBool(s2);
            if (konkurrenceSvømmer) {
                System.out.println("Hvad er dit køn?: ");
                String køn = scanner.nextLine();

                System.out.println("Hvilken svømmedisciplin konkurrerer du i? (butterfly, crawl, rygcrawl, brystsvømning): ");
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
            formatPrint(delfinen.database.getMedlemDB());//formatPrint(delfinen.læsData()); //Viser alle medlemmer fra csv filen
        }
        catch (Exception e) {
            System.out.println("Fil kunne ikke findes");
        }
    }


    public void søgMedlem() {
        System.out.println("Indtast ID på medlem");
        scanner.nextLine(); //Scanner bug
        String ID = scanner.nextLine();
        try {
            Medlem m = delfinen.database.findMedlem(ID);
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
        System.out.println("Indtast ID på medlem");
        scanner.nextLine();
        String ID = scanner.nextLine();
        try {
            Medlem m = delfinen.database.findMedlem(ID);
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
                        5. Medlems gæld
                        6. Konkurrencesvømmer info
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
                        String s = scanner.nextLine();
                        boolean nyAktivitet = readBool(s);
                        m.setErAktiv(nyAktivitet);
                    }
                    case 5 -> {
                        System.out.println("Er medlemmet i gæld?");
                        scanner.nextLine();
                        String s = scanner.nextLine();
                        boolean gæld = readBool(s);
                        m.setHarGæld(gæld);
                    }
                    case 6 -> {
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
        System.out.println("Indtast ID på medlemmet du vil slette");
        scanner.nextLine();
        String ID = scanner.nextLine();
        try {
            Medlem m = delfinen.database.findMedlem(ID);
            System.out.println("\nSlettet: " + m.getNavn());
            delfinen.database.sletMedlem(m.getID());
        }
        catch (NullPointerException e){
            System.out.println("Medlem kunne ikke findes ");
        }
    }


    public void konkurrenceSvømmere() {
        System.out.println("""
                        Hvad ønsker du at se?
                        1. Sorter efter alder
                        2. Vis oversigt af top 5 svømmere inden for hver svømmedisciplin
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
        return scanner.nextInt();
    }


    private boolean readBool(String s) {
        boolean bool = false;
        while (!s.equalsIgnoreCase("ja") && !s.equalsIgnoreCase("nej")/*s.matches("ja|nej")*/){
            System.out.println(s + " " + "Indtast venligst et passende svar.");
            s = scanner.nextLine();
        }
        if (s.equalsIgnoreCase("ja")) {
            bool = true;
        }
        return bool;
    }


    private void formatPrint(ArrayList<Medlem> sorteringList) {
        System.out.printf("┃ %-10s ┃ %-20s │ %-15s │ %-25s │ %-12s │ %-15s ┃ %-15s ┃%n", "ID", "Navn", "Fødselsdato", "E-mail", "Aktivitet", "Køn", "Svømmedisciplin");
        for (Medlem m : sorteringList) {
            if (m instanceof KonkurrenceMedlem km){
                System.out.printf("┃ %-10s ┃ %-20s │ %-15s │ %-25s │ %-12s │ %-15s ┃ %-15s ┃%n", km.getID(), km.getNavn(), km.getFødselsdato(),
                        km.getEmail(), km.getErAktiv(), km.getKøn(), km.getDiscipliner());
            }
            else {
                System.out.printf("┃ %-10s ┃ %-20s │ %-15s │ %-25s │ %-12s │ %-15s ┃ %-15s ┃%n", m.getID(), m.getNavn(), m.getFødselsdato(), m.getEmail(), m.getErAktiv(), "", "");
            }
        }
    }


    private void kontigentFormatPrint () {
        try {
            for (Medlem m : delfinen.database.getMedlemDB()){
                if (m.getHarGæld())
                    System.out.printf("%-15s %15s %20s %n", "Navn: " + m.getNavn(), "Alder: " + m.getAlder(), "Gæld: " + m.beregnGæld());
                else
                    System.out.printf("%-15s %15s %20s %n", "Navn: " + m.getNavn(), "Alder: " + m.getAlder(), "Kontigent: " + m.beregnKontigent());
            }
            System.out.printf("%-20s %n%n", "Samlet årlig kontigent: " + delfinen.database.beregnSamletBalance());
        }
        catch (Exception e) {
            System.out.println("Fil kunne ikke findes");
        }
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




