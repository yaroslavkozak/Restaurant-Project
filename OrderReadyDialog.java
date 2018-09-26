package work.Restaurant_Java;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Order ready dialog.
 */
public class OrderReadyDialog extends RestaurantDialog implements ActionListener{

    /**
     * Not Used
     * The Jl order id.
     * The Jtf order id.
     */
    JLabel jlOrderID = new JLabel("Order ID");
    JTextField jtfOrderID = new JTextField(10);
    /**
     * The Orders list.
     */
    private JComboBox ordersList = new JComboBox<String>();

    /**
     * The Jb set ready.
     */
    private JButton jbSetReady = new JButton("Ready");
    /**
     * The Jb cancel.
     */
    private JButton jbCancel = new JButton("Cancel");


    /**
     * Instantiates a new Order ready dialog.
     *
     * @param frameIn    the frame in
     * @param restaurant the restaurant
     */
    OrderReadyDialog(JFrame frameIn, Restaurant restaurant){
// properties of OrderReadyDialog frame
        super(frameIn,"Is order Ready?", restaurant);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
//Insert Panel
        JPanel indexPanel = new JPanel();
        indexPanel.setBorder(new TitledBorder("Which order"));
        /*indexPanel.add(jlOrderID);
        indexPanel.add(jtfOrderID);*/


//Setting table picker
        int orderListSize = restaurant.getNewOrders().size();
        ordersList.setPreferredSize(new Dimension(150,20));
        for (int i = 0; i < orderListSize; i++) {
            String orderID = String.valueOf(restaurant.getNewOrders().get(i).getOrderID());
            ordersList.addItem(orderID);
        }
        indexPanel.add(ordersList);

// button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new TitledBorder("Chose Action"));
        buttonPanel.add(jbCancel);
        buttonPanel.add(jbSetReady);

        jbCancel.addActionListener(this);
        jbSetReady.addActionListener(this);

//adding to the main panel + main properties
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



        if (e.getSource()==jbCancel){
            dispose();
        }

        if (e.getSource()==jbSetReady){
            System.out.println("Im here");
            //int orderID = restaurant.getNewOrders().get(ordersList.getSelectedIndex()).getOrderTable().setAvailable(true);
            int orderID = ordersList.getSelectedIndex();
            System.out.println(orderID);
            JOptionPane.showMessageDialog(this,"Order №: " + ordersList.getSelectedItem()+ " is ready!","Order Ready",JOptionPane.INFORMATION_MESSAGE);

            restaurant.getNewOrders().get(ordersList.getSelectedIndex()).getOrderTable().setAvailable(true);
            restaurant.changeOrderStatus(orderID);


            dispose();


        }

    }
}