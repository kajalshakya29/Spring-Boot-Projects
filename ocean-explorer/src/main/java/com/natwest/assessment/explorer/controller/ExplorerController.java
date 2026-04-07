package com.natwest.assessment.explorer.controller;

import com.natwest.assessment.explorer.model.Direction;
import com.natwest.assessment.explorer.model.Grid;
import com.natwest.assessment.explorer.model.Probe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.natwest.assessment.explorer.service.ProbeService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/explorer")
public class ExplorerController {
    @Autowired
    private ProbeService probeService;

    @PostMapping("/move")
    public ResponseEntity<?> moveProbe(@RequestParam int x, @RequestParam int y, @RequestParam String dir, @RequestParam String commands) {

        try {
            Direction startDirection = Direction.valueOf(dir.toUpperCase()); //validate & convert direction
            Grid grid = new Grid(10, 10); // Grid setup (10x10)
            grid.addObstacle(2, 2);
            Probe probe = new Probe(x, y, startDirection, new ArrayList<>()); // probe creation using startDirection
            for (char cmd : commands.toCharArray()) {  // command execution
                probeService.move(probe, String.valueOf(cmd).toUpperCase(), grid);
            }
            return ResponseEntity.ok(probe); //success response

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid Direction! Please use: NORTH, EAST, SOUTH, or WEST.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Something went wrong: " + e.getMessage());
        }
    }
}