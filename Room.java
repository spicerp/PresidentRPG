/* ----------------------
 * Spicer Pagan
 * Code Completed 5/1/2023
 * ----------------------
 * Section Information:
 * CPSC 1060 - Section: M W F @ 1:25 PM
 * CPSC 1061 Section: T T @ 2:00 PM
 * ----------------------
 * Room.java File
 * ----------------------
 */

 import java.util.HashMap; // imports

public class Room {
    private String name;
    private HashMap<String, String> actions;
    private HashMap<String, Integer> foods;
    
    public Room(String name, HashMap<String, String> actions, HashMap<String, Integer> foods) {
        this.name = name;
        this.actions = actions != null ? new HashMap<>(actions) : new HashMap<>(); // Initialize the actions HashMap, if it's null, create an empty HashMap instead
        this.foods = foods != null ? new HashMap<>(foods) : null; // Initialize the foods HashMap, if it's null, set it to null
    }

    public String getName() {
        return name; // Return the name of the room
    }

    public void setFoods(HashMap<String, Integer> foods) {
        this.foods = foods; // Set the foods available in the room to the specified HashMap
    }

    public HashMap<String, Integer> getFoods() {
        return foods;  // Return the HashMap of foods available in the room
    }

    public void addAction(String actionKey, String actionValue) {
        actions.put(actionKey, actionValue); // Add an action to the available actions for the room
    }

    public HashMap<String, String> getActions() {
        return actions; // Return the HashMap of available actions for the room
    }
}
