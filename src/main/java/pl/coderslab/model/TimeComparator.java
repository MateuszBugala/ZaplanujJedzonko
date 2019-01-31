package pl.coderslab.model;

import java.util.Comparator;

public class TimeComparator implements Comparator<Recipe> {
    @Override
    public int compare(Recipe recipe1, Recipe recipe2) {
        return recipe2.getCreated().compareTo(recipe1.getCreated());
    }
}
