package work.Restaurant_Java;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * The type Restaurant.
 */
public class Restaurant implements Serializable{


    private int numOfTables;
    private ArrayList<Meal> menu = new ArrayList<Meal>();
    private ArrayList<Table> tablesList = new ArrayList<Table>();

    private ArrayList<Order> newOrders = new ArrayList<Order>();
    private ArrayList<Order> readyOrders = new ArrayList<Order>();
    private String menuFilePath = "C:\\Users\\YaroslavKozak\\IdeaProjects\\Java\\src\\work\\Restaurant\\menu.csv";
    private String orderFilePath = "C:\\Users\\YaroslavKozak\\IdeaProjects\\Java\\src\\Deree\\ProjectRestaurant\\UML\\orders.csv";

    private final int DEFAULT_NUMBER_OF_TABLES = 10;


    /**
     * Instantiates a new Restaurant with DEFAAULT_NUMBER_OF_TABLES.
     */
    Restaurant() throws RestaurantException{
        numOfTables = DEFAULT_NUMBER_OF_TABLES;
        createTables();


        if (readMenu(menuFilePath)!=null){
            loadMenu();


        /*if (readOrders(orderFilePath)!=null){
            //loadOrders();
        }else {
            System.out.println("No orders loaded");}*/

    }

    }

    /**
     * Loads menu.
     */
    private void loadMenu(){
        List<String[]> menuFile = readMenu(menuFilePath);
        for (String[] menuItem : menuFile){
            String name = menuItem[0];
            int price = Integer.parseInt(menuItem[1]);
                menu.add(new Meal(name,price));
        }
        System.out.println("Added: " + menu.size() + " meals");
    }
    /**
     * loads Orders from the file
     * Test
     */
    public void loadOrders(){
      /*  List<String[]> orderFile = readOrders(orderFilePath);

        for (String[] orderRow : orderFile) {
            if (orderRow[0].equals("") || orderRow[1].equals("")){
                System.out.println("Finished reading file");
            }else {
                int tableID = Integer.parseInt(orderRow[0]);
                int mealID = Integer.parseInt(orderRow[1]);
                String orderType = orderRow[2];
                ArrayList<Meal> meals = test ArrayList<Meal>();
                meals.add(menu.get(mealID));
                Table table = tablesAvailable.get(tableID);
                tablesAvailable.get(tableID).setAvailable(false);
                tablesTaken.add(table);
                tablesAvailable.remove(table);


                    if (orderType.equals("READY")){ readyOrders.add(test Order(table, meals)); }
                    else { newOrders.add(test Order(table, meals));}
            }
        }*/

        System.out.println("Added " + newOrders.size() + " test order(s), and " + readyOrders.size() + " ready order(s)");
    }

    /**
     * Reads file with menu and add meals it find there
     * @param file String
     * @return the string with menu Items
     */
    private List<String[]> readOrders(String file) {
        List<String[]>data = new LinkedList<String[]>();
        String dataRow;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((dataRow = br.readLine()) != null) {
                String[] dataRecords = dataRow.split(",");
                data.add(dataRecords);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("could not read the file");
            e.printStackTrace();
        }
        return data;
    }
    /**
     * Reads file with menu and add meals it find there
     * @param file String
     * @return the string with menu Items
     */
    private List<String[]> readMenu(String file) {
        List<String[]>data = new LinkedList<String[]>();
        String dataRow;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((dataRow = br.readLine()) != null) {
                String[] dataRecords = dataRow.split(",");
                data.add(dataRecords);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("could not read the file");
            e.printStackTrace();
        }
        return data;
    }
    /**
     * creates tables (numTables) and add to the tableList
     * @return the string with menu Items
     */
    private void createTables() {
        for (int i = 1;i<=numOfTables;i++){
            tablesList.add(new Table(i));
        }
        System.out.println("created tables: " + tablesList.size());
    }
    /**
     * Show menu string.
     *
     * @return the string with menu Items
     */
    String showMenu() {
        String temp = "";
        for (int i=0;i<menu.size();i++){
            temp += "No " + (i+1) + " : " + menu.get(i).toString() + "\n";
        }
        return temp;
    }

    /**
     * Gets new orders List
     *
     * @return List<Order>  getNewOrders
     */
    ArrayList<Order> getNewOrders() {
        return newOrders;
    }

    /**
     * Show new orders string.
     *
     * @return the string with new Orders
     */
    public String showNewOrdersString(){
        String temp = "";
        for (int i=0;i<newOrders.size();i++){
            temp += newOrders.get(i).toString() + "\n";
        }
        return temp;
    }

    /**
     * Get menu item meal.
     *
     * @param menuItem int
     * @return Meal(menuItem) meal
     */
    public Meal getMenuItem(int menuItem){
        menuItem = menuItem-1;
        return menu.get(menuItem);
    }

    /**
     * Non-gui Test
     * Gets order. Asks for table and meal id
     */
    public void getOrder(){
        System.out.println("***************************");
        System.out.println("Which table");
        showAvailableTables();
        Scanner in = new Scanner(System.in);
        int table = in.nextInt();
        if (!tablesList.get(table).isAvailable()){ System.out.println("Table is not available"); }
        else {

            System.out.println("Choose the meals to order   [0] to finish adding");
            showMenu();
            ArrayList<Meal> orderChoice = new ArrayList<Meal>();
            Scanner in2 = new Scanner(System.in);
            double cart = 0;
            int orderAdd;
            for (orderAdd = 0; orderAdd != -1; orderAdd = (in2.nextInt()-1)){
                orderChoice.add(menu.get(orderAdd));
                cart = cart + menu.get(orderAdd).getPrice();
                System.out.println("Added " + menu.get(orderAdd).toString());
                System.out.println("The whole price is: " + cart + "€");
                cart = cart + menu.get(orderAdd).getPrice();
            }


            Order order = new Order(tablesList.get(table), orderChoice);
            //tablesTaken.add(tablesAvailable.get(table));
            tablesList.get(table).setAvailable(false);
            newOrders.add(order);
            order.setOrderPrice(cart);
            System.out.println("You ordered: \n" + order.getOrderMeals() + "\nTo pay: " + order.getOrderPrice() + "€" + "\nID: " + order.getOrderID());

        }
    }

    /**
     * Get new orders print.
     */
    public void getNewOrdersPrint(){
        System.out.println("Here is the list of test Orders");
        for (int i=0; i<newOrders.size();i++){
            System.out.println(newOrders.get(i).toString());
        }
    }

    /**
     * Get ready orders print.
     */
    public void getReadyOrdersPrint(){
        for (int i=0; i<readyOrders.size();i++){
            System.out.println("Here is a list of ready orders!");
            System.out.println(readyOrders.get(i).toString());
        }
    }

    /**
     * Gets readyOrdersList.
     *
     * @return List<Order>  readyOrders
     */
    ArrayList<Order> getReadyOrders() {
        return readyOrders;
    }

    /**
     * Changes Order(orderID) status.
     *
     * @param orderID int
     */
    void changeOrderStatus(int orderID){
        //orderID = orderID-1;
       // System.out.println(newOrders.get(orderID).toString());
        newOrders.get(orderID).upgradeOrderStatus();
        readyOrders.add(newOrders.get(orderID));
        newOrders.remove(newOrders.get(orderID));
    }

    /**
     * Test
     * Reserve table in NON_GUI
     * void
     */
    public void reserveTable(){
        System.out.println("which table to book");
        showAvailableTables();
        Scanner in = new Scanner(System.in);
        int tableToBook = in.nextInt()-1;
        if (tablesList.get(tableToBook).isAvailable()){
            System.out.println("booking a table");
            tablesList.get(tableToBook).setAvailable(false);

        }else {
            System.out.println("Table" + tablesList.get(tableToBook).getTableNumber() + " is not available at the moment");}
    }

    /**
     * Books a Table(index).
     *
     * @param index int
     */
    public void bookTable(int index){
        index = index-1;
        if (tablesList.get(index).isAvailable()){
            tablesList.get(index).setAvailable(false);


        }else {
            // JOptionPane.showMessageDialog(frameIn, "Table" + tablesAvailable.get(index).getTableNumber() + " is not available at the moment", "Table not Available", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Sets Table(index) available.
     *
     * @param index int
     */
    public void vacateTable(int index){
        if (!tablesList.get(index).isAvailable()){
            tablesList.get(index).setAvailable(true);
        }else {
            // JOptionPane.showMessageDialog(frameIn, "Table" + tablesAvailable.get(index).getTableNumber() + " is not available at the moment", "Table not Available", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * String of available tables.
     *
     * @return the string
     */
    String showAvailableTables() {
        String temp = "";
        for (int i=0;i<tablesList.size();i++){
            if (tablesList.get(i).isAvailable())
            temp += tablesList.get(i).toString() + "\n";
        }
        return temp;
    }

    /**
     * Adds new meal to the menu.
     *
     * @param p double (price of meal)
     * @param n String (name of meal)
     */
    void addNewMeal(double p, String n){

       /* System.out.println("Adding test meal to the menu");
        System.out.println("Type in the name of the Dish");
        Scanner in  = new Scanner(System.in);
        String name = in.nextLine();
        System.out.println("Type in the price of the Dish");
        int price = in.nextInt();*/
        Meal meal = new Meal(n,p);
        menu.add(meal);

    }

    /**
     * Menu size int.
     *
     * @return the int
     */
    int menuSize(){
        return  menu.size();
    }

    /**
     * Gets menu.
     *
     * @return the List<Meal>
     */
    ArrayList<Meal> getMenu() {
        return menu;
    }

    /**
     * Gets tables list.
     *
     * @return the Table List
     */
    ArrayList<Table> getTablesList() {
        return tablesList;
    }

    /**
     * Calculate earnings string.
     *
     * @return the string from double earnings
     */
    String calculateEarnings() {
        double earnings = 0;
        for (Order order: readyOrders){
            earnings +=order.getOrderPrice();
        }
        return String.valueOf(earnings);

    }

    /**
     * Gets num of tables.
     *
     * @return the num of tables
     */
    int getNumOfTables() {
        return numOfTables;
    }

    /**
     * Get tables available size int.
     *
     * @return the int
     */
    int getTablesAvailableSize(){
        int t=0;
        for (Table table:tablesList){
            if (table.isAvailable()){
                t++;
            }
        }
        return t;
    }

    /**
     * Get Restaurant toString.
     *
     * @return the String
     */
    public String toString(){

        int i;
        String s = "Airport with " + tablesList.size() + " tables:\n";
        for (Table r:tablesList)
        {
            s += r + "\n";
        }

        s+= "Number of menu items: " + menu.size() +"\n";
        i=1;
        for (Meal m:getMenu())
        {
            s = s+ (i++) +". " + m + "\n";
        }

        s+= "New orders: " + getNewOrders().size() +"\n";
        i=1;
        for (Order o:getNewOrders())
        {
            s = s+ (i++) +". " + o + "\n";
        }

        s+= "Ready orders: " + getReadyOrders().size() +"\n";
        i=1;
        for (Order o:getReadyOrders())
        {
            s = s+ (i++) +". " + o + "\n";
        }




        s+= "Total earnings: " + calculateEarnings();


        return s;
    }
}


