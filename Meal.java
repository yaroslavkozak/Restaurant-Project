package work.Restaurant_Java;


import java.io.Serializable;
import java.util.Scanner;

/**
 * The type Meal.
 */
public class Meal implements Serializable{
    /**
     * double Price.
     */
    private double price;
    /**
     * String Name.
     */
    private String name;


    /**
     * Test
     * Instantiates a new Meal.
     * @param p the Price
     */
    public Meal(double p){
        System.out.println("Type the name of the meal:");
        Scanner in = new Scanner(System.in);
        name = in.nextLine();
        System.out.println("Type in the price");
        price = in.nextInt();
    }

    /**
     * Instantiates a new Meal.
     *
     * @param name  the name
     * @param price the price
     */
    Meal(String name, double price){
        this.price = price;
        this.name = name;
    }



    /**
     * Gets meal info.
     *
     * @return String with name and price
     */

    public String toString() {
        return (name + " : " + price + "€");
    }

    /**
     * Gets price.
     * @return the price
     */
    double getPrice() {
        return price;
    }

    /**
     * test (void)
     * Sets price.
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
