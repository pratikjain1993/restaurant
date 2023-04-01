package api;

import model.Restaurant;
import model.User;
import recommendation.rule.*;
import recommendation.rule.orchestrate.ISortingRulesOrchestrator;
import recommendation.rule.orchestrate.impl.SortingRulesOrchestrator;
import store.IRestaurantStore;
import store.InMemoryRestaurantStore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GetRestaurantsListAPI {

    private IRestaurantStore restaurantStore;
    private ISortingRulesOrchestrator sortingRulesOrchestrator;
    private List<IRestaurantSortingRule> rules;

    public GetRestaurantsListAPI() {
        restaurantStore = new InMemoryRestaurantStore();
        rules = new ArrayList<>();
        rules.add(new Rule1(restaurantStore));
        rules.add(new Rule2(restaurantStore));
        rules.add(new Rule3(restaurantStore));
        rules.add(new Rule4(restaurantStore));
        rules.add(new Rule5(restaurantStore));
        rules.add(new Rule6(restaurantStore));
        rules.add(new Rule7(restaurantStore));
        rules.add(new Rule8(restaurantStore));
        rules.add(new Rule9(restaurantStore));
        sortingRulesOrchestrator = new SortingRulesOrchestrator(rules);
    }

    public List<String> getRestaurantRecommendations(User user, Restaurant[] availableRestaurants) {
        for (int i = 0; i < availableRestaurants.length; i++) {
            restaurantStore.addRestaurant(availableRestaurants[i]);
        }
        return sortingRulesOrchestrator.
                getTopNRestaurantsForUser(user, 100).
                stream().
                map(Restaurant::getRestaurantId).
                collect(Collectors.toList());
    }
}