package work.Restaurant_Java;

import java.io.Serializable;

/**
 * The type Table.
 */
public class Table implements Serializable {
    private int tableNumber;
    private boolean isAvailable;
    //private String reserveName;
    /**
     * The enum Status.
     */
    public enum Status {
        /**
         * Available status.
         */
        AVAILABLE, /**
         * Booked status.
         */
        BOOKED;
    }
    /**
     * Instantiates a new Table.
     * @param tableNumber the table number
     * sets Availability to Available
     *
     */
    public Table(int tableNumber){
        this.tableNumber = tableNumber;
        isAvailable = true;


    }
    /**
     * Gets table number.
     * @return the table number
     */
    int getTableNumber() {
        return tableNumber;
    }
    /**
     * Sets table number.
     * @param tableNumber the table number
     */
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
    /**
     * Books table string.
     * @param table the table
     * @return the string with info about Booked String
     */
    public String bookTable(Table table){
        table.setAvailable(false);
        return "table №" + table.getTableNumber() + " is booked";
    }
    /**
     * Is available boolean.
     * @return the boolean
     */
    boolean isAvailable() {
        return isAvailable;
    }
    /**
     * Check availibility of Table.
     * @return the string (Booked or Available)
     */
    private String checkAvailibility(){
        if (isAvailable()){
            return "AVAILABLE";
        }
        else return "BOOKED";
    }
    /**
     * Sets availability of the Table
     * @param available the available
     */
    void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * gets info about the Table
     * @return the string with Table info
     * */
    public String toString(){
        return "Table № " + getTableNumber() + " Availability: " + checkAvailibility() + "\n";
    }



}
