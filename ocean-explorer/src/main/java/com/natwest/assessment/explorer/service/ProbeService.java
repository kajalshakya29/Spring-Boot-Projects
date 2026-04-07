package com.natwest.assessment.explorer.service;

import com.natwest.assessment.explorer.model.Direction;
import com.natwest.assessment.explorer.model.Probe;
import com.natwest.assessment.explorer.model.Grid;
import org.springframework.stereotype.Service;

@Service
public class ProbeService {
    public void move(Probe probe, String command, Grid grid) {
        switch (command) {
            case "F" -> moveRelative(probe, grid, 1);  // Forward
            case "B" -> moveRelative(probe, grid, -1); // Backward
            case "L" -> turn(probe, -1);               // Left
            case "R" -> turn(probe, 1);                // Right
            default -> System.out.println("Invalid command: " + command);
        }
    }

    public void moveRelative(Probe probe,Grid grid, int step) {
        int nextX = probe.getX();
        int nextY = probe.getY();

        switch (probe.getDirection()) {
            case NORTH -> nextY += step;
            case SOUTH -> nextY -= step;
            case EAST  -> nextX += step;
            case WEST  -> nextX -= step;
        }

        if (grid.isWithinBounds(nextX, nextY) && !grid.isObstacle(nextX, nextY)) {
            probe.setX(nextX);
            probe.setY(nextY);
            probe.addToPath(nextX, nextY);
        } else {
            System.out.println("Movement blocked at (" + nextX + "," + nextY + ")");
        }
    }

    private void turn(Probe probe, int turnDir) {
        Direction[] dirs = Direction.values();
        int currentIndex = probe.getDirection().ordinal();

        int nextIndex = (currentIndex + turnDir + 4) % 4;
        probe.setDirection(dirs[nextIndex]);
    }
}