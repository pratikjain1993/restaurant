package recommendation.rule;

import model.Restaurant;
import model.User;

import java.util.List;

public interface IRestaurantSortingRule {
    List<Restaurant> getOrderedSelectedRestaurants(User user);
}
