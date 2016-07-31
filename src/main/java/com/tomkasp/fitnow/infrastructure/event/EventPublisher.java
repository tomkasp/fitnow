package com.tomkasp.fitnow.infrastructure.event;

/**
 * @author Tomasz Kasprzycki
 */
public interface EventPublisher {
    void publish(Object event);
}
