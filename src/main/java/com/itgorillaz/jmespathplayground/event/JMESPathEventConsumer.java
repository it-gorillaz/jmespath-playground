package com.itgorillaz.jmespathplayground.event;

@FunctionalInterface
public interface JMESPathEventConsumer {

    void onEvent(JMESPathEvent event);
}
