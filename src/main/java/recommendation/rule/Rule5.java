package recommendation.rule;

import model.Cuisine;
import model.Restaurant;
import model.User;
import store.IRestaurantStore;
import util.CostBracketUtil;
import util.CuisineUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Rule5 implements IRestaurantSortingRule{

    private IRestaurantStore restaurantStore;

    public Rule5(IRestaurantStore restaurantStore) {
        this.restaurantStore = restaurantStore;
    }

    @Override
    public List<Restaurant> getOrderedSelectedRestaurants(User user) {
        List<Restaurant> restaurants = restaurantStore.getRecentlyCreatedRestaurants();
        restaurants.sort(Comparator.comparing(Restaurant::getRating).reversed());
        List<Restaurant> result = new ArrayList<>();
        for (int i = 0; i < Math.min(4, restaurants.size()); i++) {
            result.add(restaurants.get(i));
        }
        return result;
    }
}
