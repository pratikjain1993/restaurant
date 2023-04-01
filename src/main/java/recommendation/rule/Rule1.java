package recommendation.rule;

import model.Cuisine;
import model.Restaurant;
import model.User;
import store.IRestaurantStore;
import util.CostBracketUtil;
import util.CuisineUtil;

import java.util.ArrayList;
import java.util.List;

public class Rule1 implements IRestaurantSortingRule {

    private IRestaurantStore restaurantStore;

    public Rule1(IRestaurantStore restaurantStore) {
        this.restaurantStore = restaurantStore;
    }

    @Override
    public List<Restaurant> getOrderedSelectedRestaurants(User user) {
        Cuisine primaryCuisine = CuisineUtil.getPrimaryCuisine(user.getCuisines());
        int primaryCostBracket = CostBracketUtil.getPrimaryCostBracket(user.getCostBracket());
        List<Restaurant> primaryRestaurants = restaurantStore.getRestaurants(primaryCuisine, primaryCostBracket);
        if (primaryRestaurants != null && !primaryRestaurants.isEmpty()) {
            return primaryRestaurants;
        }
        List<Integer> secondaryCostBrackets = CostBracketUtil.getSecondaryCostBrackets(user.getCostBracket());
        List<Cuisine> secondaryCuisines = CuisineUtil.getSecondaryCuisines(user.getCuisines());
        List<Restaurant> result = new ArrayList<>();
        for (Integer costBracket : secondaryCostBrackets) {
            for (Cuisine cuisine : secondaryCuisines) {
                result.addAll(restaurantStore.getRestaurants(cuisine, costBracket));
            }
        }
        return result;
    }
}
