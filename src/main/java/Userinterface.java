import java.util.Scanner;

public class Userinterface {

   public void startProgram() {
       Scanner scanner = new Scanner(System.in);
       boolean kører = true;
       System.out.println("""
               """);

       while (kører) {
           System.out.println("enter command");
           String userInput = scanner.nextLine().toLowerCase();

           // Array og splitter

           String[] userInputs = userInput.split(" ");
           String command = userInputs[0];
           String userChoice = "";

           if(userInputs.length > 1) {
               userChoice = userInputs[1];
           }

           // switch case
           switch (command) {
               case "Tilføj, tilføj":
                   System.out.println("Navn på medlem:");
                   String navn = scanner.nextLine();

                   System.out.println("Alder på medlem:");
                   int alder = scanner.nextInt();

                   System.out.println("Køn på medlem:");
                   String køn = scanner.nextLine();
           }


       }
   }
}