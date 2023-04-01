package util;

import error.RestaurantSystemError;
import model.Cuisine;
import model.CuisineTracking;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class CuisineUtil {
    public static Cuisine getPrimaryCuisine(List<CuisineTracking> cuisineTrackings) {
        return cuisineTrackings.stream().
                max(Comparator.comparing(CuisineTracking::getNoOfOrders)).
                map(CuisineTracking::getType).
                orElseThrow(() -> new RestaurantSystemError("Could mot find a costTracking"));
    }

    public static List<Cuisine> getSecondaryCuisines(List<CuisineTracking> cuisineTrackings) {
        List<CuisineTracking> sortedList = new ArrayList<>(cuisineTrackings);
        sortedList.sort(Comparator.comparing(CuisineTracking::getNoOfOrders).reversed());
        List<Cuisine> secondaryCuisines = new ArrayList<>();
        for(int i = 1; i <= 2 && i < sortedList.size(); ++i) {
            secondaryCuisines.add(sortedList.get(i).getType());
        }
        return secondaryCuisines;
    }
}
