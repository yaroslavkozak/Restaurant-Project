package work.Restaurant_Java;

/**
 * The type Restaurant app.
 */
public class RestaurantApp {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) throws RestaurantException {
        RestaurantFrame mainWindow = new RestaurantFrame();
        mainWindow.setSize(615,600);
        mainWindow.setDefaultCloseOperation(3);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setTitle("My Restaurant");
        mainWindow.setVisible(true);
        mainWindow.setResizable(false);

    }




}
