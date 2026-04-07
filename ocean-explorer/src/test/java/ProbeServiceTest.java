//package com.natwest.assessment.explorer.service; // Package name check karein

import com.natwest.assessment.explorer.model.Direction;
import com.natwest.assessment.explorer.model.Probe;
import org.junit.Assert;
import org.testng.annotations.Test;
import com.natwest.assessment.explorer.service.ProbeService;
import com.natwest.assessment.explorer.model.Grid;

public class ProbeServiceTest {

    @Test
    void testMoveForwardFacingNorth() {
        //given
        Grid grid = new Grid(10, 10);
        Probe probe = new Probe(0, 0, Direction.NORTH, new java.util.ArrayList<>());
        ProbeService service = new ProbeService();

        // When
        service.move(probe, "F", grid);

        // Then
        Assert.assertEquals(0, probe.getX());
        Assert.assertEquals(1, probe.getY());
    }

    @Test
    void testStayOnGridBoundary() {
        // Given
        Grid grid = new Grid(5, 5);
        Probe probe = new Probe(0, 4, Direction.NORTH, new java.util.ArrayList<>());
        ProbeService service = new ProbeService();

        // When
        service.move(probe, "F", grid);

        // Then
        Assert.assertEquals(4, probe.getY());
    }

    @Test
    void testStopAtObstacle() {
        Grid grid = new Grid(10, 10);
        grid.addObstacle(0, 1);

        Probe probe = new Probe(0, 0, Direction.NORTH, new java.util.ArrayList<>());
        ProbeService service = new ProbeService();
        service.move(probe, "F", grid);

        // Expected: Probe is already on (0,0) because (0,1) is blocked
        Assert.assertEquals(0, probe.getY());
    }

    @Test
    void testSequenceOfCommands() {
        Grid grid = new Grid(10, 10);
        Probe probe = new Probe(0, 0, Direction.NORTH, new java.util.ArrayList<>());
        ProbeService service = new ProbeService();

        // Sequence: Forward -> Right -> Forward
        service.move(probe, "F", grid);
        service.move(probe, "R", grid);
        service.move(probe, "F", grid);

        // Final state: (1,1) facing EAST
        Assert.assertEquals(1, probe.getX());
        Assert.assertEquals(1, probe.getY());
        Assert.assertEquals(Direction.EAST, probe.getDirection());
    }

    @Test
    void testMoveBackward() {
        Grid grid = new Grid(10, 10);
        Probe probe = new Probe(0, 5, Direction.NORTH, new java.util.ArrayList<>());
        ProbeService service = new ProbeService();
        service.move(probe, "B", grid);
        Assert.assertEquals(4, probe.getY());
    }
}