package model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private List<CuisineTracking> cuisines;
    private List<CostTracking> costBracket;
}
