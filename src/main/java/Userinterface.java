import java.util.Scanner;

public class Userinterface {

    private Controller controller = new Controller();
    Scanner scanner = new Scanner(System.in);
    Boolean dataChanged = false;

    public void startProgram() throws FileNotFoundException {
        int userChoice = -1;

        System.out.println("Welcome to the hero collector\n" +
                "________________________________");

        while (userChoice != 9) {
            System.out.println("""                  
                    1. Add Superhero.
                    2. Access The Superhero List
                    3. Search For Superheroes
                    4. Edit Superhero
                    5. Delete hero
                    9. End Program
                    """);

            userChoice = scanner.nextInt();
            scanner.nextLine(); // Håndtering af Scanner bug
            UserChoice(userChoice);
            if (dataChanged) {
                controller.saveData();
            }
        }
    }

    // Brugerens valgmuligheder i menuen

    public void UserChoice(int userChoice) throws FileNotFoundException {
        if (userChoice == 1) {
            addSuperhero(); //Crud operation
            dataChanged = true;
        }
        else if (userChoice == 2) {
            System.out.println("""
                    Which attribute do you want your superheroes to be sorted by?
                    1. Name
                    2. Is Human
                    3. Superpower
                    4. Creation year
                    5. Strength
                    6. Sort by two attributes
                    7. Show unsorted list
                    """);
            int userChoiceSort = scanner.nextInt();
            handleSort(userChoiceSort);
        } else if (userChoice == 3)
            searchInput();
        else if (userChoice == 4) {
            editSuperhero();
            dataChanged = true;
        } else if (userChoice == 5) {
            deleteHero();
            dataChanged = true;
        } else if (userChoice == 9)
            System.out.println("Closing Superhero..");
    }

    // tilføje superhelte

    public void addSuperhero() {
        System.out.println("Enter the superhero's real name:");
        String name = scanner.nextLine();

        System.out.println("Is your superhero human?");
        boolean isHuman = false;
        int valg;
        do {
            System.out.println("\nType 1 for Yes\nType 2 for No");
            valg = scanner.nextInt();
            switch (valg) {
                case 1 -> isHuman = true;
                case 2 -> isHuman = false;
                default -> System.out.println("Invalid input, try again");
            }
        } while (valg != 1 && valg != 2);


        scanner.nextLine();

        System.out.println("Enter the superhero's power: ");
        String power = scanner.nextLine();

        //scanner.nextLine();

        System.out.println("Enter the superhero's year of publication: ");
        int year = scanner.nextInt();

        System.out.println("Enter the superhero's strength:");
        double strength = readDouble();
        controller.database.addSuperheroes(name, isHuman, power, year, strength);
    }
}