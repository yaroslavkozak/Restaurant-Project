package work.Restaurant_Java;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Cancel booking dialog.
 */
public class CancelBookingDialog extends RestaurantDialog implements ActionListener {

    /**
     * The Tables booked list.
     */
// JLabel jlOrderID = new JLabel("Order ID");
    private JComboBox<Integer> tablesBookedList = new JComboBox<Integer>();

    /**
     * The Jb vacate.
     */
    private JButton jbVacate = new JButton("Vacate");


    /**
     * Instantiates a new Cancel booking dialog.
     *
     * @param frameIn    the frame in
     * @param restaurant the restaurant
     */
    public CancelBookingDialog(JFrame frameIn, Restaurant restaurant) {

 //props
        super(frameIn, "Cancel Booking", restaurant);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

//index panel + tables
        JPanel indexPanel = new JPanel();
        indexPanel.setBorder(new TitledBorder("Which table got canceled"));


        tablesBookedList.setPreferredSize(new Dimension(150, 20));

        for (int i = 0; i < restaurant.getTablesList().size(); i++) {
            if (!restaurant.getTablesList().get(i).isAvailable()) {
                int orderID = restaurant.getTablesList().get(i).getTableNumber();
                tablesBookedList.addItem(orderID);
            }
        }
            indexPanel.add(tablesBookedList);

// button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new TitledBorder("Chose Action"));
            buttonPanel.add(jbtCancel);
            buttonPanel.add(jbVacate);


            jbtCancel.addActionListener(this);
            jbVacate.addActionListener(this);

// adding all to main panel
        JPanel panel = new JPanel();
        panel.add(indexPanel);
            panel.add(buttonPanel);

            this.add(panel);
// main props
            this.pack();
            // this.setDefaultCloseOperation();
            this.setVisible(true);
            this.setResizable(false);

        }

        @Override
        public void actionPerformed (ActionEvent e){
            if (e.getSource() == jbVacate) {
               // restaurant.vacateTable(tablesBookedList.getSelectedIndex()+1);
                int table = Integer.parseInt(String.valueOf(tablesBookedList.getSelectedItem()));
                System.out.println(table);
                restaurant.getTablesList().get(table-1).setAvailable(true);
                JOptionPane.showMessageDialog(this, "Table №: " + (tablesBookedList.getSelectedItem()) + " is vacated!", "Table vacated", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }

    }
