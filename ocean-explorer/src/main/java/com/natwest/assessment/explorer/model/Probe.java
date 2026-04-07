package com.natwest.assessment.explorer.model;

import java.util.ArrayList;
import java.util.List;

public class Probe {
    private int x;
    private int y;
    private Direction direction;
    private List<String> visitedPath = new ArrayList<>();

    public Probe(int x, int y, Direction direction, List<String> visitedPath) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.visitedPath = visitedPath != null ? visitedPath : new ArrayList<>();
    }

    public Probe() {}

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public List<String> getVisitedPath() {
        return visitedPath;
    }
    public void addToPath(int x, int y) {
        visitedPath.add("(" + x + "," + y + ")");
    }
}