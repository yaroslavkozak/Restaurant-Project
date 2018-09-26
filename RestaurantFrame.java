package work.Restaurant_Java;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * The type Restaurant frame.
 */
public class RestaurantFrame extends JFrame implements ActionListener {

    private Restaurant myRestaurant = new Restaurant();;
    private JMenuItem jmiOpen = new JMenuItem("Open");
    private JMenuItem jmiSave = new JMenuItem("Save");
    private JMenuItem jmiExit = new JMenuItem("Exit");
    private JMenuItem jmiDebug = new JMenuItem("Debug");

  //  private JPanel buttonPanel = new JPanel();

    private JButton jbtnEditMenu = new JButton("Edit Menu");
    private JButton jbtnBookTable = new JButton("Book Table");
    private JButton jbtnVacateTable = new JButton("Cancel Booking");
    private JButton jbtnOrderReady= new JButton("Order Ready");
    private JButton jbtnNewOrder = new JButton("New Order");


    private JTextArea jtaFinishedOrders = new JTextArea(30,20);
    private JTextArea jtaOrders = new JTextArea(30,20);
    private JTextArea jtaMenu = new JTextArea(30,20);
    private JTextArea jtaDashboard = new JTextArea(10,40);


   // private JScrollPane jspTableList = new JScrollPane(jtaTableList);
    private JFileChooser jfc = new JFileChooser();

    /**
     * Buttons for the Tables tab
     */
    private JButton[] b  = new JButton[myRestaurant.getNumOfTables()];


    /**
     * Instantiates a new Restaurant frame.
     */
    RestaurantFrame() throws RestaurantException {

// ADDING A MENU AND ITEMS
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        fileMenu.setMnemonic('F');
        fileMenu.add(jmiOpen);
        fileMenu.add(jmiSave);
        fileMenu.add(jmiDebug);
        fileMenu.add(jmiExit);


// ICON PICTURE TO DASHBOARD

        JLabel jlblPicture = new JLabel();
        jlblPicture.setIcon(new ImageIcon("C:\\Users\\YaroslavKozak\\IdeaProjects\\Java\\src\\work\\Restaurant_Java\\image.gif"));







       /* Dashboard.setLayout(new BorderLayout());
        Dashboard.add(jlblPicture,BorderLayout.NORTH);


        Dashboard.add(info_on_dashboard);
        info_on_dashboard.add(jtaDashboard)*/;



// Dashboard  tab and panel
        JPanel info_on_dashboard = new JPanel();
        info_on_dashboard.setBorder(new TitledBorder("Information"));

        jtaDashboard.setText(populateDashboardInfo());

        JPanel dashboard_panel = new JPanel();
        dashboard_panel.setLayout(new BorderLayout());
        dashboard_panel.add(jlblPicture,BorderLayout.NORTH);
        info_on_dashboard.setBorder(new TitledBorder("Information"));
        info_on_dashboard.add(jtaDashboard);
        dashboard_panel.add(info_on_dashboard);
        JPanel dashboard = new JPanel();
        dashboard.add(dashboard_panel);

        info_on_dashboard.setBackground(Color.GRAY);
        jtaDashboard.setBackground(Color.GRAY);

//jtaOrders
        jtaOrders.setBackground(Color.white);
        jtaOrders.setBorder(new TitledBorder("Orders"));
        listOrders();


//tables text area (not used)
        JTextArea jtaTableList = new JTextArea(30, 20);
        jtaTableList.setBackground(Color.white);
        jtaTableList.setBorder(new TitledBorder("Tables"));
        jtaTableList.setText(myRestaurant.showAvailableTables());

//menu tab


        jtaMenu.setBackground(Color.white);
        jtaMenu.setBorder(new TitledBorder("Menu"));
        listMenu();


  // tabs properties

        jtaDashboard.setEditable(false);
        jtaFinishedOrders.setEditable(false);
        jtaOrders.setEditable(false);
        jtaTableList.setEditable(false);
        jtaMenu.setEditable(false);



  // tables tab

        JPanel tablesPanel = new JPanel();
        tablesPanel.setLayout(new GridLayout(2,5));
        for(int i=0; i<myRestaurant.getNumOfTables();i++) {
            b[i] = new JButton(String.valueOf(myRestaurant.getTablesList().get(i).getTableNumber()));
            tablesPanel.add(b[i]);
        }



 // Work with tabs

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Dashboard", dashboard);
        JScrollPane jspOrders = new JScrollPane(jtaOrders);
        tabs.add("Orders", jspOrders);
       // tabs.add("Finished", jspFinishedOrders);
        tabs.add("Tables", tablesPanel);
        JScrollPane jspMenu = new JScrollPane(jtaMenu);
        tabs.add("Menu", jspMenu);
        tabs.setTabPlacement(JTabbedPane.LEFT);



// SETTING LAYOUT FOR BUTTON PANEL
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(true);


        buttonPanel.add(jbtnEditMenu);
        buttonPanel.add(jbtnVacateTable);
        buttonPanel.add(jbtnBookTable);
        buttonPanel.add(jbtnOrderReady);
        buttonPanel.add(jbtnNewOrder);
        buttonPanel.setBorder(new TitledBorder("Enter an option"));

        jbtnEditMenu.setToolTipText("See the menu details");
        jbtnVacateTable.setToolTipText("Unbook a table");
        jbtnBookTable.setToolTipText("Book table");
        jbtnOrderReady.setToolTipText("Order is ready!");
        jbtnNewOrder.setToolTipText("Record an order");


        jbtnEditMenu.addActionListener(this);
        jbtnVacateTable.addActionListener(this);
        jbtnBookTable.addActionListener(this);
        jbtnOrderReady.addActionListener(this);
        jbtnNewOrder.addActionListener(this);
        jmiOpen.addActionListener(this);
        jmiSave.addActionListener(this);
        jmiExit.addActionListener(this);
        jmiDebug.addActionListener(this);


// POSITIONING AND PANELS

        getContentPane().add(tabs,BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    }



    /**
     * gets new Orders in String
     * sets jtaOrders to String
     */
    private void listOrders() {
        String temp = "";
        for (Order order:myRestaurant.getNewOrders()){
            temp+=order.toString();
        }
        jtaOrders.setText(temp);


    }
    /**
     * gets finished Orders in String
     * @return getNewOrders() String
     */
    private String listFinishedOrders(){
        String temp1 = "";
        for (Order order1:myRestaurant.getReadyOrders()){
            temp1+=myRestaurant.getNewOrders().toString();
        }

        return temp1;


    }
    /**
     * checks TablesList for booked and available tables
     * sets text in buttons (tables tab) to display information of the table
     */
    private void checkTables(){

     //   JButton[] b  = new JButton[myRestaurant.getNumOfTables()];

        for (int i = 0; i<myRestaurant.getTablesList().size();i++) {
            if (!myRestaurant.getTablesList().get(i).isAvailable()) {
                b[myRestaurant.getTablesList().get(i).getTableNumber() - 1].setText("is Booked");
            } else if (myRestaurant.getTablesList().get(i).isAvailable()) {
                b[myRestaurant.getTablesList().get(i).getTableNumber() - 1].setText(String.valueOf(myRestaurant.getTablesList().get(i).getTableNumber()));

            }
        }
    }
    /**
     * sets menu tab testarea to display current menu
     */
    private void listMenu(){
        jtaMenu.setText(myRestaurant.showMenu());

    }
    /**
     * Creates a String with menu size + number of tables available + info on orders + and todays Earnings
     * Returns String with the info
     */
    private String populateDashboardInfo(){
        return "Menu contains: " + myRestaurant.getMenu().size() + " meals" + "\n" + "Tables available: " + myRestaurant.getTablesAvailableSize() + " \n" + " Processing: " + myRestaurant.getNewOrders().size() + " orders " + " \n " + "Finished: " + myRestaurant.getReadyOrders().size()+ "\n**********************************\n" + "Today earnings: " + (myRestaurant.calculateEarnings());
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==jbtnEditMenu)
        {
            new MenuDialog(this,myRestaurant);

        }

       if (e.getSource()==jbtnVacateTable){
           new CancelBookingDialog(this,myRestaurant);
           checkTables();

       }
        if (e.getSource()==jbtnBookTable)
        {
            new BookTableDialog(this,myRestaurant);
            checkTables();

        }

       if (e.getSource()==jbtnOrderReady){
           new OrderReadyDialog(this,myRestaurant);
           checkTables();


       }

        if (e.getSource()==jbtnNewOrder)
        {
            new NewOrderDialog(this,myRestaurant);
            checkTables();


        }

        if (e.getSource()==jmiOpen)
        {
            if (jfc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
            {
                File fileName = jfc.getSelectedFile();
                String filePath = fileName.getAbsolutePath();
                try {
                    open(filePath);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (e.getSource()==jmiSave)
        {
            if (jfc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
            {
                File fileName = jfc.getSelectedFile();
                String filePath = fileName.getAbsolutePath();
                try {
                    save(filePath);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (e.getSource()==jmiExit)
        {
            System.exit(0);
        }
        if (e.getSource()==jmiDebug)
        {
            JOptionPane.showMessageDialog(this, myRestaurant.toString(), "Debug", JOptionPane.INFORMATION_MESSAGE); ;
        }


        listMenu();
        listOrders();
        jtaFinishedOrders.setText(listFinishedOrders());
        listMenu();
        jtaDashboard.setText(populateDashboardInfo());
        checkTables();
    }

    /**
     * Opens a file with Restaurant Obj loads it to the program
     * @param filePath String
     * @throws IOException if there is a problem with opening a file
     */
    private void open(String filePath) throws IOException
    {
        try
        {
            FileInputStream fileInput = new FileInputStream(filePath);
            ObjectInputStream objInput = new ObjectInputStream(fileInput);
            myRestaurant = (Restaurant) objInput.readObject();
            objInput.close();
            JOptionPane.showMessageDialog(this, "Restaurant loaded", "Information", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Loads Restaurant Obj to a file
     * @param filePath String
     * @throws IOException if there is a problem with opening a file
     */
    private void save(String filePath) throws IOException
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(myRestaurant);
            objOut.close();
            JOptionPane.showMessageDialog(this, "Restaurant saved", "Information", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addComp(JPanel panel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place, int stretch){

        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = xPos;
        gridConstraints.gridy = yPos;
        gridConstraints.gridwidth = compWidth;
        gridConstraints.gridheight = compHeight;
        gridConstraints.weightx = 100;
        gridConstraints.weightx = 100;
        gridConstraints.insets = new Insets(5,5,5,5);
        gridConstraints.anchor = place;
        gridConstraints.fill = stretch;

        panel.add(comp,gridConstraints);
    }
}
