package work.Restaurant_Java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Restaurant dialog.
 */
abstract class RestaurantDialog extends JDialog implements ActionListener {


    /**
     * The Restaurant.
     */
    Restaurant restaurant;
    /**
     * The Jbt cancel.
     */
    JButton jbtCancel = new JButton("Cancel");


    /**
     * Instantiates a new Restaurant dialog.
     *
     * @param frameIn    the frame in
     * @param titleIn    the title in
     * @param restaurant the restaurant
     */
    RestaurantDialog(JFrame frameIn, String titleIn, Restaurant restaurant) {

        super(frameIn, true);
        this.restaurant = restaurant;
        setLocation(100, 200);
        setTitle(titleIn);
        jbtCancel.addActionListener(new CancelButtonListener());


    }

    private class CancelButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            dispose();
        }
    }
}

