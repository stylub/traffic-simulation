package com.intelligent.trafficlights.simulation.car;

import com.trafficlights.simulation.car.Car;
import com.trafficlights.simulation.utils.Directions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class CarTests {
    @Test
    public void testCarConstructor() {
        Car car = new Car("vehicle1", "south", "north");
        assertNotNull(car);
        assertEquals("vehicle1", car.getId());
        assertEquals(Directions.SOUTH, car.getStartRoad());
        assertEquals(Directions.NORTH, car.getEndRoad());
    }
    @Test
    public void testCarConstructorIncorrectDirection() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Car("vehicle1", "incorrect", "north");
        });
    }
}
