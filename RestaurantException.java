package work.Restaurant_Java;

public class RestaurantException extends Exception {

    public RestaurantException () {
        super("Error, Restaurant System violation");
    }
    public RestaurantException (String message) {
        super(message);
    }
}
