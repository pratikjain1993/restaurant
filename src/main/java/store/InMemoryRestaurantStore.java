package store;

import model.Cuisine;
import model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryRestaurantStore implements IRestaurantStore {
    private final List<Restaurant> allRestaurants = new ArrayList<>();


    @Override
    public void addRestaurant(Restaurant restaurant) {
        allRestaurants.add(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return allRestaurants;
    }

    @Override
    public List<Restaurant> getRestaurants(Cuisine cuisine, int costBracket) {
        return allRestaurants.stream().
                filter(rest -> rest.getCuisine() == cuisine && rest.getCostBracket() == costBracket).
                collect(Collectors.toList());
    }

    @Override
    public void removeRestaurant(Restaurant restaurant) {
        synchronized (allRestaurants) {
            allRestaurants.remove(restaurant);
        }
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        synchronized (allRestaurants) {
            allRestaurants.stream().
                    filter(res -> res.getRestaurantId().equals(restaurant.getRestaurantId())).
                    findAny().map(allRestaurants::remove);
            allRestaurants.add(restaurant.toBuilder().build());
        }
    }
}
