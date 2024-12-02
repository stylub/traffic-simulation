package com.trafficlights.simulation.Queue;

import com.trafficlights.simulation.utils.Rounds;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueueTest extends AbstractQueueTest {
    @Override
    protected Queue createQueue(Rounds rounds) {
        return new Queue(rounds);
    }

    @Test
    public void testRemoveFromQueue() {
        queue.addToQueue("north");
        queue.addToQueue("north");
        queue.removeFromQueue(0, 1);
        assertEquals(1, queue.queues.get(0));
    }
}