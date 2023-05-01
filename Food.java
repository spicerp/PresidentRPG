/* ----------------------
 * Spicer Pagan
 * Code Completed 5/1/2023
 * ----------------------
 * Section Information:
 * CPSC 1060 - Section: M W F @ 2:25 PM
 * CPSC 1061 Section: T T @ 2:00 PM
 * ----------------------
 * Food.java File
 * ----------------------
 */

 public class Food {
    private String name;
    private int pointCost;

    // Constructor that creates a new Food object with the given name and point cost.
    public Food(String name, int pointCost) {
        this.name = name;
        this.pointCost = pointCost;
    }

    // method for the name of the food.
    public String getName() {
        return name;
    }

    // method for the point cost of the food.
    public int getPointCost() {
        return pointCost;
    }
}
