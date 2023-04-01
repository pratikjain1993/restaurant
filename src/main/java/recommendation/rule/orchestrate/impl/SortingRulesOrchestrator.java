package recommendation.rule.orchestrate.impl;

import model.Restaurant;
import model.User;
import recommendation.rule.IRestaurantSortingRule;
import recommendation.rule.orchestrate.ISortingRulesOrchestrator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SortingRulesOrchestrator implements ISortingRulesOrchestrator {

    private List<IRestaurantSortingRule> rules;

    public SortingRulesOrchestrator(List<IRestaurantSortingRule> rules) {
        this.rules = rules;
    }

    @Override
    public List<Restaurant> getTopNRestaurantsForUser(User user, int n) {
        List<Restaurant> result = new ArrayList<>();
        Set<String> takenRestaurantIds = new HashSet<>();
        for (IRestaurantSortingRule rule : rules) {
            List<Restaurant> restaurantsFromRule = rule.getOrderedSelectedRestaurants(user).
                    stream().
                    filter(restaurant -> !takenRestaurantIds.contains(restaurant.getRestaurantId())).
                    collect(Collectors.toList());
            result.addAll(restaurantsFromRule);
            takenRestaurantIds.addAll(restaurantsFromRule.stream().
                    map(Restaurant::getRestaurantId).
                    collect(Collectors.toList()));
            if (result.size() >= n) {
                break;
            }
        }

        return result.subList(0, Math.min(n, result.size()));
    }
}
