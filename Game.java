/* ----------------------
 * Spicer Pagan
 * Code Completed 5/1/2023
 * ----------------------
 * Section Information:
 * CPSC 1060 - Section: M W F @ 2:25 PM
 * CPSC 1061 Section: T T @ 2:00 PM
 * ----------------------
 * Game.java File
 * ----------------------
 */

 // EXPLANATION
 // Code starts in oval office, you can leave and go to hallway, explore around and go to the cafeteria. You can then eat, by interacting with the Lunch Lady and spending the points
 // you earned from signing bills in the oval office.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// defines more privates, defines Lists, Scanners, Loops, Other files, etc.

public class Game {
    private Map map;
    private Character player;
    private boolean isRunning;
    private List<Bill> bills;
    private int currentBillIndex = 0;
    private Scanner scanner;

    // main method to start the game

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }

    // public game method (defines some key things, loops, etc.)

    public Game() {
        map = new Map();
        player = new Character("President");
        isRunning = true;
        bills = new ArrayList<>();
        loadBills(); // This is the file I/0 in the loadBills method, which reads a file and populates the bills arraylist!
        scanner = new Scanner(System.in);
    }

    // main run method

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome to the Presidential Adventure!"); // Starts the game

        while (isRunning) {
            Room currentRoom = map.getCurrentRoom();
            System.out.println("\nYou are now in the " + currentRoom.getName()+"\n"); // loop keeps game going without title
            System.out.println("What would you like to do?\n");
            printActions(currentRoom.getActions());

            String input = scanner.nextLine();
            handleInput(input);
            map.printMapWithPresident(); 
        }
        scanner.close();
    }

    // basic cases, handles some invalid inputs

    private void handleInput(String input) {
        Room currentRoom = map.getCurrentRoom();
        String action = currentRoom.getActions().get(input);
        if (action == null) {
            System.out.println("Invalid input. Please try again.");
            return;
        }
        switch (input) {
            case "m":
                move(scanner); // moves player
                break;
            case "sb":
                signBills(scanner); // sign bills
                break;
            case "e":
                eat(); // cafeteria only - eats / interact with lunch lady
                break;
            case "sp":
                System.out.println("\nYou currently have " + player.getPoints() + " points.\n"); // point amount
                break;
            case "exit":
                // ...
            default:
                System.out.println("Invalid action. Please try again.");
        }
    }

    // allows one to sign a bill, see if it is good or bad, etc

    private void signBills(Scanner scanner) {
        int goodBillsSigned = 0;
        int badBillsSigned = 0;
        boolean continueSigning = true;
    
        while (continueSigning) {
            for (Bill bill : bills) {
                System.out.println("Bill: " + bill.getName());
                System.out.println("Description: " + bill.getDescription());
                System.out.println("Do you want to sign this bill? (yes/no/exit)"); // simple yes/no/exit
    
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("yes")) {
                    if (bill.isGood()) {
                        goodBillsSigned++;
                        player.addPoints(10);
                    } else {
                        badBillsSigned++;
                        player.addPoints(-10);
                    }
                } else if (input.equalsIgnoreCase("no")) {
                    if (bill.isGood()) {
                        player.addPoints(-5);
                    }
                } else if (input.equalsIgnoreCase("exit")) {
                    continueSigning = false;
                    break;
                }
    
                if (badBillsSigned >= 3) {
                    System.out.println("\nYour mother comes in and beats you with a spoon. She says to make better choices.\n"); // if you are doing bad
                    player.addPoints(-50);
                    break;
                } else if (goodBillsSigned >= 3) {
                    System.out.println("\nYour mother comes in and gives you 50 points. She is very proud of your decision making!\n"); // if you are doing good
                    player.addPoints(50);
                }
            }
        }
    }

    // loads the bills from a file

    private void loadBills() {
        try {
            File file = new File("C:\\Java\\Project\\RPG Game\\DungeonGame\\src\\bills.txt"); // specific to files (change accordingly)
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                bills.add(new Bill(parts[0], parts[1], Integer.parseInt(parts[2].trim()) == 1));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

        // Shuffle the order of the bills
        Collections.shuffle(bills);
    }

    // Interaction with lunch lady, allows you to eat food.

    private void eat() {
        Room currentRoom = map.getCurrentRoom();
        if (!currentRoom.getName().equals("Cafeteria")) {
            System.out.println("You can only eat in the Cafeteria.");
            return;
        }
    
        HashMap<String, Integer> foods = currentRoom.getFoods();
    
        if (foods == null || foods.isEmpty()) {
            System.out.println("There's no food available.");
            return;
        }
    
        // Interact with the Cafeteria Lady
        System.out.println("\nCafeteria Lady: \"Hello! Welcome to the cafeteria. Here's our menu today:\"\n");
    
        for (String foodName : foods.keySet()) {
            System.out.println(foodName + " - " + foods.get(foodName) + " points");
        }
    
        System.out.println("\nCafeteria Lady: \"Enter the name of the food you would like to eat:\"\n");
        String selectedFood = scanner.nextLine();
    
        if (foods.containsKey(selectedFood)) {
            int foodPrice = foods.get(selectedFood);
            if (player.getPoints() >= foodPrice) {
                player.addPoints(-foodPrice);
                System.out.println("\nCafeteria Lady: \"Here's your " + selectedFood + ". Enjoy!\"\n");
                System.out.println("You now have " + player.getPoints() + " points left.\n");
            } else {
                System.out.println("Cafeteria Lady: \"Sorry, but you don't have enough points to buy that food.\"");
            }
        } else {
            System.out.println("Cafeteria Lady: \"I'm sorry, but that food is not on the menu.\"");
        }
    }

    // move method handles player movement

    private void move(Scanner scanner) {
        Room currentRoom = map.getCurrentRoom();
        System.out.println("\nWhere would you like to move? (north (n) /south (s) /east (e) /west (w))");
        String direction = scanner.nextLine();
        if (map.move(direction)) {
            if (direction.equals("s"))
            System.out.println("\nYou have moved south.\n");
            if (direction.equals("n"))
            System.out.println("\nYou have moved north.\n");
            if (direction.equals("e"))
            System.out.println("\nYou have moved east.\n");
            if (direction.equals("w"))
            System.out.println("\nYou have moved west.\n");
        } else {
            System.out.println("\nThere is no room in that direction.\n");
        }
    }

    // prints all available actions

    private void printActions(HashMap<String, String> actions) {
        for (String key : actions.keySet()) {
            System.out.println(key + ": " + actions.get(key));
        }
        Room currentRoom = map.getCurrentRoom();
    }
}
