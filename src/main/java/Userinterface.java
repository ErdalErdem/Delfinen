import java.util.Scanner;

public class Userinterface {
    Scanner scanner = new Scanner(System.in);

    public void userChoice(int userChoice) {
        userChoice = -1;
        System.out.println("Velkommen til Delfinen! \n" +
                "_______________");

        while (userChoice != 9) {
            System.out.println("""                  
                    1. Opret medlemmer 
                    2. Liste af medlemmere
                    3. Redigering af medlemmere
                    4. Slette medlemmere
                    5. Tilføj medlemmere
                    9. Afslut program
                 
                    
                    """);

            userChoice = scanner.nextInt();
            scanner.nextLine(); // Håndtering af Scanner bug
            userChoice(userChoice);


        }
    }




}
