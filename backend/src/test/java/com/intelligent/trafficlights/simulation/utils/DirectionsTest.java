package com.intelligent.trafficlights.simulation.utils;

import com.trafficlights.simulation.utils.Directions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DirectionsTest {

    @Test
    public void testGetValue() {
        assertEquals(0, Directions.NORTH.getValue());
        assertEquals(1, Directions.WEST.getValue());
        assertEquals(2, Directions.SOUTH.getValue());
        assertEquals(3, Directions.EAST.getValue());
    }

    @Test
    public void testToString() {
        assertEquals("north", Directions.NORTH.toString());
        assertEquals("west", Directions.WEST.toString());
        assertEquals("south", Directions.SOUTH.toString());
        assertEquals("east", Directions.EAST.toString());
    }

    @Test
    public void testParseDirection() {
        assertEquals(Directions.NORTH, Directions.parseDirection("north"));
        assertEquals(Directions.WEST, Directions.parseDirection("west"));
        assertEquals(Directions.SOUTH, Directions.parseDirection("south"));
        assertEquals(Directions.EAST, Directions.parseDirection("east"));
        assertEquals(Directions.NORTH, Directions.parseDirection("NORTH"));
        assertEquals(Directions.WEST, Directions.parseDirection("WEST"));
        assertEquals(Directions.SOUTH, Directions.parseDirection("SOUTH"));
        assertEquals(Directions.EAST, Directions.parseDirection("EAST"));
    }

    @Test
    public void testParseDirectionInvalid() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Directions.parseDirection("invalid");
        });
        assertEquals("Invalid direction: invalid. Valid directions are: NORTH, WEST, SOUTH, EAST.", exception.getMessage());
    }
}