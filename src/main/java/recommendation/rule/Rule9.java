package recommendation.rule;

import model.Cuisine;
import model.Restaurant;
import model.User;
import store.IRestaurantStore;
import util.CostBracketUtil;
import util.CuisineUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rule9 implements IRestaurantSortingRule {
    private IRestaurantStore restaurantStore;

    public Rule9(IRestaurantStore restaurantStore) {
        this.restaurantStore = restaurantStore;
    }

    @Override
    public List<Restaurant> getOrderedSelectedRestaurants(User user) {
        return restaurantStore.getAllRestaurants();
    }
}
