package error;

public class RestaurantSystemError extends RuntimeException {
    public RestaurantSystemError(String errorMessage) {
        super(errorMessage);
    }
}
