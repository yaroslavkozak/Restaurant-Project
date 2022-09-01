package work.Restaurant_Java;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Order.
 */
public class Order implements Serializable {

    /**
     * The enum Status.
     */
    public enum Status{
        /**
         * Ready status.
         */
        READY, /**
         * New status.
         */
        NEW;
    }

    /**
     * The Table orderTable.
     */
    private Table orderTable;
    /**
     * The Order status.
     */
    private boolean orderStatus;
    /**
     * ArrayList<Meal> orderItems
     */
    private ArrayList<Meal> orderItems = new ArrayList<Meal>();

    /**
     * int index
     */
    private static int index = 0;

    /**
     * int id (order ID)
     */
    private int orderID;

    /**
     * double orderPrice (initially set to zero)
     */
    private double orderPrice = 0;


    /**
     * Instantiates a new Order.
     *
     * @param orderTable the order table
     * @param orderItems the order items
     */
    Order(Table orderTable, ArrayList<Meal> orderItems){
        this.orderItems = orderItems;
        orderStatus = false;
        ++index;
        this.orderID = setOrderID();
        this.orderTable = orderTable;
        orderTable.setAvailable(false);


    }

    /**
     * Gets order id.
     *
     * @return the order id
     */
    int getOrderID() {
        return orderID;
    }
    /**
     * Sets order id.
     *
     * @return int orderID(+1000)
     */
    private int setOrderID() {
        orderID = 1000 + index;
        return orderID;
    }
    /**
     * Gets order meals.
     *
     * @return List<Meal> getOrderMeals()
     */
    List<Meal> getOrderMeals() {

        return orderItems;
    }
    /**
     * Gets order table.
     *
     * @return the order Table
     */
    Table getOrderTable() {

        return orderTable;
    }
    /**
     * Gets order status.
     *
     * @return the order status info
     */
    private String getOrderStatus() {
        if (orderStatus)
        {
            return "Ready";
        }
        else
        {
            return "Not ready";
        }


    }
    /**
     * Test
     * Checks if order is ready.
     * Prints out the answer to the console
     */
    public void isOrderReady(){
        if (!orderStatus){
            System.out.println("It's not ready yet");
        }else {
            System.out.println("Yes!, be right there!");
        }
    }
    /**
     * Upgrades order status.
     */
    void upgradeOrderStatus(){

        orderStatus=true;

    }
    /**
     * test (void)
     * Changes table of the order
     * @param orderTable the order table
     */
    public void changeTable(Table orderTable){
        this.orderTable = orderTable;
    }
    /**
     * Gets Order info
     * @return String
     */
   public String toString(){
       String orderPrint = "ID: " + orderID +  "\nTable: " + orderTable.getTableNumber() + " \n " + "Meals: " 
           + getOrderMeals() + " \n " + "Price: " + 
           getOrderPrice() + "€"+ " \n " + "Status: " + getOrderStatus() + " \n" + "***********************************" + "\n";
       return orderPrint;
   }
    /**
     * Gets order price.
     * @return double orderPrice
     */
    double getOrderPrice() {
        orderPrice = 0;
       for (int i=0;i<orderItems.size();i++)
       {
        orderPrice += orderItems.get(i).getPrice();
       }
        return orderPrice;
    }
    /**
     * Sets order price.
     * @param orderPrice double orderPrice
     */
    void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }
}




