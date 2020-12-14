import java.util.Scanner;

public class PakuriProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Pakudex: Tracker Extraordinaire!");

        String input;
        int maxCap = 0;
        // Keep the user inputting values until it is valid
        while (maxCap < 1) {
            System.out.print("Enter max capacity of the Pakudex: ");
            input = scanner.next();
            try {
                maxCap = Integer.parseInt(input);
            }
            catch (Exception e) {
                System.out.println("Please enter a valid size.");
                continue;
            }
            if (maxCap < 1) {
                System.out.println("Please enter a valid size.");
            }
        }
        Pakudex pakudex = new Pakudex(maxCap);
        System.out.println("The Pakudex can hold " + maxCap + " species of Pakuri.");
        System.out.println("");

        int select = 0;
        // Keep the Pakudex running until the user inputs 6 to quit
        while (select != 6) {
            System.out.println("Pakudex Main Menu");
            System.out.println("-----------------");
            System.out.println("1. List Pakuri");
            System.out.println("2. Show Pakuri");
            System.out.println("3. Add Pakuri");
            System.out.println("4. Evolve Pakuri");
            System.out.println("5. Sort Pakuri");
            System.out.println("6. Exit");
            System.out.println("");
            System.out.print("What would you like to do? ");

            // If the user inputs a string or invalid number, it will keep asking for input
            input = scanner.next();
            try {
                select = Integer.parseInt(input);
            }
            catch (Exception e) {
                select = -1;
            }

            // if input is less than one or greater than six
            if (select < 1 || select > 6) {
                System.out.println("Unrecognized menu selection!");
            }
            // print array of species if pakudex has at least one pakuri
            else if (select == 1) {
                if (pakudex.getSize() == 0) {
                    System.out.println("No Pakuri in Pakudex yet!");
                }
                else {
                    System.out.println("Pakuri In Pakudex:");
                    String[] nameList = pakudex.getSpeciesArray();
                    for (int i = 0; i < nameList.length; i++) {
                        System.out.println((i + 1) + ". " + nameList[i]);
                    }
                }
            }
            // finds if the species is in the pakudex and prints the stats
            else if (select == 2) {
                System.out.print("Enter the name of the species to display: ");
                String species = scanner.next();
                try {
                    int[] stats = pakudex.getStats(species);
                    if (stats[0] == 0) {
                        System.out.println("Error: No such Pakuri!");
                    }
                    else {
                        System.out.println("");
                        System.out.println("Species: " + species);
                        System.out.println("Attack: " + stats[0]);
                        System.out.println("Defense: " + stats[1]);
                        System.out.println("Speed: " + stats[2]);
                    }
                }
                catch (Exception e) {
                    System.out.println("Error: No such Pakuri!");
                }
            }

            // trys to add the pakuri to the pakudex if it isn't full or a duplicate pakuri
            else if (select == 3) {
                if (pakudex.getSize() == pakudex.getCapacity()) {
                    System.out.println("Error: Pakudex is full!");
                    System.out.println("");
                    continue;
                }
                System.out.print("Enter the name of the species to add: ");
                String species = scanner.next();
                if (pakudex.pakuriIndex(species) > -1) {
                    System.out.println("Error: Pakudex already contains this species!");
                }
                else {
                    pakudex.addPakuri(species);
                    System.out.println("Pakuri species " + species + " successfully added!");
                }
            }

            // evolves pakuri if it exists
            else if (select == 4) {
                System.out.print("Enter the name of the species to evolve: ");
                String species = scanner.next();
                if (pakudex.pakuriIndex(species) == -1) {
                    System.out.println("Error: No such Pakuri!");
                }
                else {
                    pakudex.evolveSpecies(species);
                    System.out.println(species + " has evolved!");
                }
            }
            // sorts the pakuri by letter
            else if (select == 5) {
                pakudex.sortPakuri();
                System.out.println("Pakuri have been sorted!");
            }
            // quits the program
            else if (select == 6) {
                System.out.println("Thanks for using Pakudex! Bye!");
            }

            System.out.println("");
        }
    }
}