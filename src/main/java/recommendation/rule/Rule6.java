package recommendation.rule;

import model.Cuisine;
import model.Restaurant;
import model.User;
import store.IRestaurantStore;
import util.CostBracketUtil;
import util.CuisineUtil;

import java.util.List;
import java.util.stream.Collectors;

public class Rule6 implements IRestaurantSortingRule {
    private IRestaurantStore restaurantStore;

    public Rule6(IRestaurantStore restaurantStore) {
        this.restaurantStore = restaurantStore;
    }

    @Override
    public List<Restaurant> getOrderedSelectedRestaurants(User user) {
        Cuisine primaryCuisine = CuisineUtil.getPrimaryCuisine(user.getCuisines());
        int primaryCostBracket = CostBracketUtil.getPrimaryCostBracket(user.getCostBracket());
        List<Restaurant> primaryRestaurants = restaurantStore.getRestaurants(primaryCuisine, primaryCostBracket);
        return primaryRestaurants.stream().
                filter(restaurant -> restaurant.getRating() < 4).
                collect(Collectors.toList());
    }
}
