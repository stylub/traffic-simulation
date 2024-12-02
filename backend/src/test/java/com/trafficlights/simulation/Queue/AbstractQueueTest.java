package com.trafficlights.simulation.Queue;

import com.trafficlights.simulation.utils.Rounds;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractQueueTest {
    protected Queue queue;

    protected abstract Queue createQueue(Rounds rounds);

    @BeforeEach
    public void setUp() {
        Rounds rounds = new Rounds(Arrays.asList(
                Arrays.asList("north", "south"),
                Collections.singletonList("east"),
                Collections.singletonList("west")
        ));
        queue = createQueue(rounds);
    }

    @Test
    public void testAddToQueue() {
        queue.addToQueue("north");
        queue.addToQueue("east");
        assertEquals(1, queue.queues.get(0));
        assertEquals(1, queue.queues.get(1));
        assertEquals(0, queue.queues.get(2));
    }

    @Test
    public void testTakeMax() {
        queue.addToQueue("north");
        queue.addToQueue("north");
        queue.addToQueue("east");
        int maxIndex = queue.takeMax();
        assertEquals(0, maxIndex);
    }

    @Test
    public void testAllEmpty() {
        assertTrue(queue.allEmpty());
        queue.addToQueue("north");
        assertFalse(queue.allEmpty());
    }
}