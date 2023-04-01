package util;

import error.RestaurantSystemError;
import model.CostTracking;
import model.Cuisine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class CostBracketUtil {
    public static int getPrimaryCostBracket(List<CostTracking> costTrackings) {
        return costTrackings.stream().
                max(Comparator.comparing(CostTracking::getNoOfOrders)).
                map(CostTracking::getType).
                orElseThrow(() -> new RestaurantSystemError("Could mot find a costTracking"));
    }

    public static List<Integer> getSecondaryCostBrackets(List<CostTracking> costTrackings) {
        List<CostTracking> sortedList = new ArrayList<>(costTrackings);
        sortedList.sort(Comparator.comparing(CostTracking::getNoOfOrders).reversed());
        List<Integer> secondaryCostBrackets = new ArrayList<>();
        for(int i = 1; i <= 2 && i < sortedList.size(); ++i) {
            secondaryCostBrackets.add(sortedList.get(i).getType());
        }
        return secondaryCostBrackets;
    }
}
