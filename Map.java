/* ----------------------
 * Spicer Pagan
 * Code Completed 5/1/2023
 * ----------------------
 * Section Information:
 * CPSC 1060 - Section: M W F @ 2:25 PM
 * CPSC 1061 Section: T T @ 2:00 PM
 * ----------------------
 * Map.java File
 * ----------------------
 */

 import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// Constructor to initialize the map and load it from a file

public class Map {
    private ArrayList<ArrayList<Room>> rooms;
    private int currentRow;
    private int currentCol;

    public Map() {
        rooms = new ArrayList<>();
        loadMap("map.txt"); // specific to files (change accordingly)
        currentRow = 1;
        currentCol = 2;

    }

    // Method to load the map from a file and set up the rooms and their actions

    private void loadMap(String fileName) {
        HashMap<String, Integer> cafeteriaFoods = new HashMap<>();

        // define all the available cafeteria foods add to hash
        
            cafeteriaFoods.put("Popcorn", 50);
            cafeteriaFoods.put("Salad", 75);
            cafeteriaFoods.put("French Fries", 100);
            cafeteriaFoods.put("Bagel with Cream Cheese", 100);
            cafeteriaFoods.put("Hot Dog", 125);
            cafeteriaFoods.put("Grilled Cheese Sandwich", 125);
            cafeteriaFoods.put("Burrito", 150);
            cafeteriaFoods.put("Pizza", 150);
            cafeteriaFoods.put("Lobster", 150);
            cafeteriaFoods.put("Beef Stir Fry", 175);
            cafeteriaFoods.put("Sushi", 175);
            cafeteriaFoods.put("Chicken Alfredo", 200);
            cafeteriaFoods.put("Steak", 200);
            cafeteriaFoods.put("Ribeye Steak", 225);
            cafeteriaFoods.put("Truffle", 200);
            cafeteriaFoods.put("Lobster Bisque", 250);
            cafeteriaFoods.put("Foie Gras", 300);
            cafeteriaFoods.put("Kobe Beef", 350);
            cafeteriaFoods.put("Caviar", 250);
            cafeteriaFoods.put("Gold Leaf Ice Cream", 500);

            // Read the map file line by line
            
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    ArrayList<Room> row = new ArrayList<>(); // Create a new array list to hold the rooms in this row
                    String[] roomCodes = line.split(" "); // Split the line into individual room codes
                    for (String roomCode : roomCodes) {
                        Room room = null;
                        switch (roomCode) {
                            case "OO": // If the room code is OO, it's the Oval Office
                                room = new Room("Oval Office", null, null);
                                room.addAction("sb", "Sign Bills");
                                room.addAction("sp", "Show Points");
                                room.addAction("m", "Move");
                                break;
                            case "CC": // If the room code is CC, it's the cafeteria
                                HashMap<String, String> actions = new HashMap<>(); // Create a new hash map to hold the actions for the cafeteria
                                actions.put("m", "Move");
                                actions.put("m", "Move");
                                actions.put("sp", "Show Points");
                                actions.put("e", "Eat");
        
                                room = new Room("Cafeteria", actions, cafeteriaFoods); // Create a new room object and set its name, actions, and foods
                                break;
                            default:
                                room = new Room("Hallway", null, null); // If the room code is anything else, it's a hallway
                                room.addAction("sp", "Show Points");
                                room.addAction("m", "Move");
                                break;
                        }
                        if (room != null) { // If a room was created, add it to the row
                            row.add(room);
                        }
                    }
                    rooms.add(row); // Add the row to the 2D array list of rooms
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public boolean move(String direction) {
        int newRow = currentRow;
        int newCol = currentCol;

        switch (direction.toLowerCase()) {
            case "n":
                newRow--;
                break;
            case "s":
                newRow++;
                break;
            case "e":
                newCol++;
                break;
            case "w":
                newCol--;
                break;
            default:
                return false;
        }

        if (isValidPosition(newRow, newCol)) {
            currentRow = newRow;
            currentCol = newCol;
            return true;
        } else {
            return false;
        }
    }

    public Room getCurrentRoom() {
        return rooms.get(currentRow).get(currentCol);
    }

    private boolean isValidPosition(int newRow, int newCol) {
        if (newRow < 0 || newRow >= rooms.size() || newCol < 0 || newCol >= rooms.get(newRow).size()) {
            return false;
        }
        Room room = rooms.get(newRow).get(newCol);
        return !room.getName().equals("Empty Room");
    }

    public void printMapWithPresident() {
        for (int row = 0; row < rooms.size(); row++) {
            for (int col = 0; col < rooms.get(row).size(); col++) {
                if (row == currentRow && col == currentCol) {
                    System.out.print(" P ");
                } else {
                    Room room = rooms.get(row).get(col);
                    if (!room.getName().isEmpty()) { // Update this condition
                        String roomCode = room.getName().substring(0, 2);
                        System.out.print(roomCode + " ");
                    } else {
                        System.out.print("__ ");
                    }
                }
            }
            System.out.println();
        }
    }
}

