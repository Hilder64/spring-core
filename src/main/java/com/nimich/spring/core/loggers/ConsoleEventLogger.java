package com.nimich.spring.core.loggers;

import com.nimich.spring.core.beans.Event;

public class ConsoleEventLogger implements EventLogger {
    @Override
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
