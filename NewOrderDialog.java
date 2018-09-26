package work.Restaurant_Java;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * 1. create an option to edit order
 * 2. number of items in order
 * 3.  price of the order
 * 4. checker for table (JComboBox of available)
 * 5. Add to order + Finish
 */
public class NewOrderDialog extends RestaurantDialog implements ActionListener {


    /**
     * test
     * Text fields to get Table Choice of the order
     */
    private JLabel tableChoiceLabel = new JLabel("Chose Table");
    private JTextField tableChoiceField = new JTextField(10);
    /**
     * test
     * The Order choice label.
     * The Order choice field.
     */
    JLabel orderChoiceLabel = new JLabel("Chose Meal");
    JTextField orderChoiceField = new JTextField(10);

    /**
     * The Meal list.
     */
    private JComboBox<String> mealList;
    /**
     * The Table list.
     */
    private JComboBox<String> tableList;
    /**
     * The Order info.
     */
    private String orderInfo = "";

    /**
     * The Jb finish order.
     */
    private JButton jbFinishOrder = new JButton("Finish Order");
    /**
     * The Jb add to order.
     */
    private JButton jbAddToOrder = new JButton("Add");

    /**
     * The Order list.
     */
    private ArrayList<Meal> orderList = new ArrayList<Meal>();



    /**
     * Instantiates a new New order dialog.
     *
     * @param frameIn    the frame in
     * @param restaurant the restaurant
     */
    NewOrderDialog(JFrame frameIn, Restaurant restaurant) {

// props
        super(frameIn,"New Order", restaurant);
        //this.setTitle("New Order");
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

// panel borders
        JPanel tablePanel = new JPanel();
        tablePanel.setBorder(new TitledBorder("Table"));
        JPanel orderPanel = new JPanel();
        orderPanel.setBorder(new TitledBorder("Meals"));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new TitledBorder("Press to continue"));





      /*  tablePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        tablePanel.add(tableChoiceLabel);
        tablePanel.add(tableChoiceField);*/



// table choice
        tableList = new JComboBox<String>();
        tableList.setPreferredSize(new Dimension(150,20));

        for (int i = 0; i < restaurant.getTablesList().size(); i++) {
            if (restaurant.getTablesList().get(i).isAvailable()) {
                String table = String.valueOf(restaurant.getTablesList().get(i).getTableNumber());
                tableList.addItem(table);
            }
        }

        tablePanel.add(tableList);



//order meal choice (order Panel)


        orderPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Create a JComboBox for menu

        int menuSize = restaurant.menuSize();

        mealList = new JComboBox<String>();
        mealList.setPreferredSize(new Dimension(150,20));

        for (int i = 0; i < menuSize; i++) {
            mealList.addItem(String.valueOf(restaurant.getMenu().get(i).getName()));
        }



        orderPanel.add(mealList);

// button panel
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(jbtCancel);
        buttonPanel.add(jbAddToOrder);
        buttonPanel.add(jbFinishOrder);

        jbtCancel.addActionListener(this);
        jbFinishOrder.addActionListener(this);
        jbAddToOrder.addActionListener(this);


// adding all to the main panel & props
        JPanel panel = new JPanel();
        panel.add(tablePanel);
        panel.add(orderPanel);
        panel.add(buttonPanel);

        //jbFinishOrder.setDefaultCapable(false);
        jbFinishOrder.setBorderPainted( false );
        jbFinishOrder.setFocusPainted( false );

        this.add(panel);
        this.pack();
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        /*
      The Table.
     */
        int table;
        if (e.getSource() == jbAddToOrder) {

            table = (Integer.parseInt(String.valueOf(tableList.getSelectedItem())));
            System.out.println(table);
            /*
      The Order index.
     */
            int orderIndex = mealList.getSelectedIndex();
            orderList.add(restaurant.getMenu().get(orderIndex));
            JOptionPane.showMessageDialog(this,"Added: " + restaurant.getMenu().get(orderIndex).toString() + " to the table № " + (table),"Added to cart",JOptionPane.INFORMATION_MESSAGE);
            tableList.setEnabled(false);
            jbFinishOrder.setBorderPainted( true );
            jbFinishOrder.setFocusPainted( true );





          /*  try {
                table = (Integer.parseInt(tableChoiceField.getText()));
                orderIndex = mealList.getSelectedIndex();
                orderList.add(restaurant.getMenu().get(orderIndex));
                tableChoiceField.setEditable(false);
                System.out.println("Added: " + restaurant.getMenu().get(orderIndex).toString() + " to the table № " + (table));

                jbFinishOrder.setBorderPainted(true);
                jbFinishOrder.setFocusPainted(true);
                JOptionPane.showMessageDialog(this, "Added to the order: " + restaurant.getMenu().get(orderIndex).toString() + "\n" + "Table: " + restaurant.getTablesList().get(table - 1));
            } catch (Exception au) {
                JOptionPane.showMessageDialog(this, "Please type in a table", "New Order", JOptionPane.ERROR_MESSAGE);
            }*/
        }



        if (e.getSource()== jbFinishOrder){

            table = (Integer.parseInt(String.valueOf(tableList.getSelectedItem())));
            // table = (Integer.parseInt(tableChoiceField.getText()));
            Order order = new Order(restaurant.getTablesList().get(table -1), orderList);


            if (orderList.size()==0){
                JOptionPane.showMessageDialog(this, "Please, add Item to cart", "Error", JOptionPane.INFORMATION_MESSAGE);

            }else {


                double price = 0;
                for (Meal meal : orderList) {
                    price += meal.getPrice();
                }
//work out the orderInfo
                orderInfo += "New Order Added: \n" + order.toString();
                JOptionPane.showMessageDialog(this, orderInfo, "New Order", JOptionPane.INFORMATION_MESSAGE);
                restaurant.getNewOrders().add(order);
                dispose();
            }
        }

    }


}