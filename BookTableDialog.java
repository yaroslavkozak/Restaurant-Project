package work.Restaurant_Java;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Book table dialog.
 */
public class BookTableDialog extends RestaurantDialog implements ActionListener {

    /**
     * test (void)
     * The Jl order id.
     */
    JLabel jlOrderID = new JLabel("Order ID");
    /**
     * The Tables available list.
     */
    private JComboBox<String> tablesAvailableList = new JComboBox<String>();

    /**
     * The Jb book.
     */
    private JButton jbBook= new JButton("Book");


    /**
     * Instantiates a new Book table dialog.
     *
     * @param frameIn    the frame in
     * @param restaurant the restaurant
     */
    BookTableDialog(JFrame frameIn, Restaurant restaurant) {

  // props main
        super(frameIn,"Book a table", restaurant);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

// index + table choice list
        JPanel indexPanel = new JPanel();
        indexPanel.setBorder(new TitledBorder("Which table to book"));
        tablesAvailableList.setPreferredSize(new Dimension(150,20));

        for (int i = 0; i < restaurant.getTablesList().size(); i++) {
            if (restaurant.getTablesList().get(i).isAvailable()) {
                String orderID = String.valueOf(restaurant.getTablesList().get(i).getTableNumber());
                tablesAvailableList.addItem(orderID);
            }else {
                System.out.println("Found booked table");
            }
        }
        indexPanel.add(tablesAvailableList);



// buttonPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new TitledBorder("Chose Action"));
        buttonPanel.add(jbtCancel);
        buttonPanel.add(jbBook);


        jbtCancel.addActionListener(this);
        jbBook.addActionListener(this);

// main panel set up
        JPanel panel = new JPanel();
        panel.add(indexPanel);
        panel.add(buttonPanel);

        this.add(panel);

        this.pack();
        // this.setDefaultCloseOperation();
        this.setVisible(true);
        this.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==jbBook){
            System.out.println("Booked: " + tablesAvailableList.getSelectedItem());
            restaurant.getTablesList().get(Integer.parseInt(String.valueOf(tablesAvailableList.getSelectedItem()))-1).setAvailable(false);
            JOptionPane.showMessageDialog(this,"Table №: " + (restaurant.getTablesList().get(Integer.parseInt(String.valueOf(tablesAvailableList.getSelectedItem()))-1).getTableNumber()) + " is booked!","Table Booking",JOptionPane.INFORMATION_MESSAGE);
            dispose();

        }

    }
}
