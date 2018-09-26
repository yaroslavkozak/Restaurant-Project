package work.Restaurant_Java;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * The type Menu dialog.
 */
public class MenuDialog extends RestaurantDialog implements ActionListener {


    /**
     * The Meal price.
     */
    private JTextField mealPrice = new JTextField(10);
    /**
     * The Meal name.
     */
    private JTextField mealName = new JTextField(10);


    //JTextArea info = new JTextArea(2,10);


    /**
     * The Jb new meal.
     */
    private JButton jbNewMeal = new JButton("Add new");
    /**
     * The Jb edit.
     */
    private JButton jbEdit = new JButton("Edit");
    /**
     * The Jb remove.
     */
    private JButton jbRemove = new JButton("Remove");

    /**
     * The Menu meals list .
     */
    private JComboBox<String> menuMeals;


    /**
     * Instantiates a new Menu dialog.
     *
     * @param frameIn    the frame in
     * @param restaurant the restaurant
     */
    MenuDialog(JFrame frameIn, Restaurant restaurant){
        super(frameIn,"Changes to the menu", restaurant);
        this.setSize(400, 250);
        this.setLocationRelativeTo(null);
        //this.setResizable(false);

        int menuSize = restaurant.menuSize();

        menuMeals = new JComboBox<String>();
        menuMeals.setPreferredSize(new Dimension(150,20));
        for (int i = 0; i < menuSize; i++) {
            menuMeals.addItem(String.valueOf(restaurant.getMenu().get(i).getName()));
        }

        menuMeals.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
               // info.setText(String.valueOf(restaurant.getMenu().get(menuMeals.getSelectedIndex()).toString()));
                mealPrice.setText(String.valueOf(restaurant.getMenu().get(menuMeals.getSelectedIndex()).getPrice()));
                mealName.setText(String.valueOf(restaurant.getMenu().get(menuMeals.getSelectedIndex()).getName()));

            }
        });

        //JList<Object> mealList = new JList<>(restaurant.getMenu().toArray());


        JPanel indexPanel = new JPanel();
        indexPanel.setBorder(new TitledBorder("Pick item to change"));
        indexPanel.add(menuMeals);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2,2));
       // infoPanel.setBorder(new TitledBorder(""));

        /*
      The Label price.
     */
        JLabel jtmealPrice = new JLabel("Price");
        infoPanel.add(jtmealPrice);
        /*
      The Label name.
     */
        JLabel jtmealName = new JLabel("Name");
        infoPanel.add(jtmealName);
        infoPanel.add(mealPrice);
        infoPanel.add(mealName);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new TitledBorder("Action"));
        buttonPanel.add(jbNewMeal);
       // buttonPanel.add(jbEdit);
        buttonPanel.add(jbRemove);
        buttonPanel.add(jbtCancel);





        jbNewMeal.addActionListener(this);
        jbEdit.addActionListener(this);
        jbRemove.addActionListener(this);


        JPanel panel = new JPanel();
        panel.add(indexPanel);
        panel.add(infoPanel);
        panel.add(buttonPanel);

        this.add(panel);
        this.pack();

        this.setVisible(true);
        this.setResizable(false);
       // this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==jbNewMeal){
            if (Objects.equals(mealName.getText(), "") || Objects.equals(mealPrice.getText(), ""))
            {
                JOptionPane.showMessageDialog(this,"Please type in BOTH - Price and Name in order to create a meal", "Error",JOptionPane.ERROR_MESSAGE);
            }else{
                {
                    JOptionPane.showMessageDialog(this, "You are about to create a new meal to menu with following characteristics: \n" + " Price: " + mealPrice.getText() + "\n" + " Name: " + mealName.getText(), "Confirm", JOptionPane.INFORMATION_MESSAGE);


                    restaurant.addNewMeal(Double.parseDouble(mealPrice.getText()), (mealName.getText()));
                    JOptionPane.showMessageDialog(this, "Menu Item created  \n" + " Menu size: " + restaurant.getMenu().size());
                    dispose();
                }
            }
            }

            /*if (e.getSource()==jbEdit){

                if (Objects.equals(mealName.getText(), "") || Objects.equals(mealPrice.getText(), "")){
                    JOptionPane.showMessageDialog(this,"Select a menu Item from the dropbox", "Error",JOptionPane.ERROR_MESSAGE);
                    indexPanel.requestFocus();
                }else {
                    infoPanel.requestFocus();

                    JOptionPane.showConfirmDialog(this, "You are about to edit following meal: \n" + " Price: " +  restaurant.getMenu().get(menuMeals.getSelectedIndex()).getPrice() + "\n" + " Name: " + restaurant.getMenu().get(menuMeals.getSelectedIndex()).getName() + "\n" + "and set its properties to: \n" + "Price: " + mealPrice.getText() + "\n Name: " + mealName.getText()  , "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
                    restaurant.getMenu().get(menuMeals.getSelectedIndex()).setName(mealName.getName());
                    restaurant.getMenu().get(menuMeals.getSelectedIndex()).setPrice(Double.parseDouble(mealPrice.getText()));

                    JOptionPane.showMessageDialog(this, "Menu Item edited!  \n" + " Menu size: " + restaurant.getMenu().size());
                    dispose();

                   // JOptionPane.showMessageDialog(this, "Menu Item created  \n" + " Menu size: " + restaurant.getMenu().size());

                }*/

            if (e.getSource()==jbRemove){

                if (Objects.equals(mealName.getText(), "") || Objects.equals(mealPrice.getText(), "")){
                    JOptionPane.showMessageDialog(this,"Select a menu Item from the dropbox", "Error",JOptionPane.ERROR_MESSAGE);
                }else {
                    JOptionPane.showConfirmDialog(this, "You are about to remove following meal: \n" + " Price: " +  restaurant.getMenu().get(menuMeals.getSelectedIndex()).getPrice() + "\n" + " Name: " + restaurant.getMenu().get(menuMeals.getSelectedIndex()).getName() + "\n" , "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
                    restaurant.getMenu().remove(menuMeals.getSelectedIndex()).setName(mealName.getName());
                    JOptionPane.showMessageDialog(this, "Menu Item removed  \n" + " Menu size: " + restaurant.getMenu().size());
                    dispose();

                }






            }

    }

    /**
     * Value changed.
     *
     * @param listSelectionEvent the list selection event
     */
    public void valueChanged(ListSelectionEvent listSelectionEvent){


    }
}
