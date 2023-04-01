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

public class Rule3 implements IRestaurantSortingRule {
    private IRestaurantStore restaurantStore;

    public Rule3(IRestaurantStore restaurantStore) {
        this.restaurantStore = restaurantStore;
    }

    @Override
    public List<Restaurant> getOrderedSelectedRestaurants(User user) {
        Cuisine primaryCuisine = CuisineUtil.getPrimaryCuisine(user.getCuisines());
        List<Integer> secondaryCostBrackets = CostBracketUtil.getSecondaryCostBrackets(user.getCostBracket());
        List<Restaurant> result = new ArrayList<>();
        for (Integer costBracket : secondaryCostBrackets) {
            List<Restaurant> restaurants = restaurantStore.getRestaurants(primaryCuisine, costBracket);
            result.addAll(restaurants.stream().
                    filter(restaurant -> restaurant.getRating() >= 4.5).collect(Collectors.toList()));
        }
        return result;
    }
}
