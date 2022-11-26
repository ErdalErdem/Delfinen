import java.util.Scanner;

public class Userinterface {
    private Database database = new Database();
    private Medlem medlem;

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
                    4. Rediger medlem info
                    5. Slet medlem
                    9. afslut""");

            menuValg = readInt();

            switch (menuValg) {
                case 1:
                    tilfoejMedlem();
                    break;
                case 2:
                    visMedlemmer();
                    break;


            }
        } while (menuValg != 9);
    }

    public void tilfoejMedlem() {
        scanner = new Scanner(System.in);
        System.out.println("Navn:");
        String medlem = scanner.nextLine();

        System.out.println("\nAlder: ");
        int alder = scanner.nextInt();

        scanner = new Scanner(System.in);
        System.out.println("Køn:");
        String køn = scanner.nextLine();

        System.out.println("Type medlem");
        medlem = scanner.nextLine();

        if (medlem == null) {
            database.tilfoejMedlem(medlem, alder, køn, Medlem.typeMedlem.junior);
            System.out.println("\nMedlem:" + medlem);
        }
    }

    public void visMedlemmer() {
        if (database.getMedlemDB().isEmpty()) {
            System.out.println("Intet medlem fundet");
        } else {
            for (Medlem medlem1 : database.getMedlemDB()) {
                System.out.println("Navn:" + medlem.getNavn());
                if (medlem.getNavn() == null) {
                    System.out.println("\n Intet navn");
                } else {
                    System.out.println("Alder:" + medlem.getAlder());
                }
                System.out.println("Køn:" + medlem.getKøn());
            }
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




