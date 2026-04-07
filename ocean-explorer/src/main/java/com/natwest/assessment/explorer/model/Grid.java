package com.natwest.assessment.explorer.model;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private final int width;
    private final int height;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
    }
    // this method checks the probe is inside the grid or not
    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    //adding list
    private List<String> obstacles = new ArrayList<>();

    public void addObstacle(int x, int y) {
        obstacles.add(x + "," + y);
    }

    public boolean isObstacle(int x, int y) {
        return obstacles.contains(x + "," + y);
    }
}