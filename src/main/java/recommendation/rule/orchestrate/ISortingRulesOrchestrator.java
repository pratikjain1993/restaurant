package recommendation.rule.orchestrate;

import model.Restaurant;
import model.User;

import java.util.List;

public interface ISortingRulesOrchestrator {
    List<Restaurant> getTopNRestaurantsForUser(User user, int n);
}
