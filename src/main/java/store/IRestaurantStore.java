package store;

import model.Cuisine;
import model.Restaurant;

import java.util.List;

public interface IRestaurantStore {
    void addRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();

    List<Restaurant> getRestaurants(Cuisine cuisine, int costBracket);

    List<Restaurant> getRecentlyCreatedRestaurants();

    void removeRestaurant(Restaurant restaurant);

    void updateRestaurant(Restaurant restaurant);
}
