/* ----------------------
 * Spicer Pagan
 * Code Completed 5/1/2023
 * ----------------------
 * Section Information:
 * CPSC 1060 - Section: M W F @ 1:25 PM
 * CPSC 1061 Section: T T @ 2:00 PM
 * ----------------------
 * Bill.java File
 * ----------------------
 */

 public class Bill {
    // priv var
    private String name;
    private String description;
    private boolean isGood;

    //initialize the variables

    public Bill(String name, String description, boolean isGood) {
        this.name = name;
        this.description = description;
        this.isGood = isGood;
    }

    // method for the name of the bill

    public String getName() {
        return name;
    }

    // description of the bill

    public String getDescription() {
        return description;
    }

    // whether bill is good or not

    public boolean isGood() {
        return isGood;
    }
}
