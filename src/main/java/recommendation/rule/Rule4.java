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

public class Rule4 implements IRestaurantSortingRule{
    private IRestaurantStore restaurantStore;

    public Rule4(IRestaurantStore restaurantStore) {
        this.restaurantStore = restaurantStore;
    }

    @Override
    public List<Restaurant> getOrderedSelectedRestaurants(User user) {
        List<Cuisine> secondaryCuisines = CuisineUtil.getSecondaryCuisines(user.getCuisines());
        int primaryCostBracket = CostBracketUtil.getPrimaryCostBracket(user.getCostBracket());
        List<Restaurant> result = new ArrayList<>();
        for (Cuisine cuisine : secondaryCuisines) {
            List<Restaurant> restaurants = restaurantStore.getRestaurants(cuisine, primaryCostBracket);
            result.addAll(restaurants.stream().
                    filter(restaurant -> restaurant.getRating() >= 4.5).collect(Collectors.toList()));
        }
        return result;
    }
}
