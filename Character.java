/* ----------------------
 * Spicer Pagan
 * Code Completed 5/1/2023
 * ----------------------
 * Section Information:
 * CPSC 1060 - Section: M W F @ 2:25 PM
 * CPSC 1061 Section: T T @ 2:00 PM
 * ----------------------
 * Character.java File
 * ----------------------
 */

 public class Character {
    private String name;
    private int points;

    public Character(String name) {
        this.name = name;
        this.points = 0;
    }

    // methods for the name of the character.

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // method for the number of points the character has.

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    // adds points

    public void addPoints(int points) {
        this.points += points;
    }
}