package com.nimich.spring.core.loggers;

import com.nimich.spring.core.beans.Event;

public interface EventLogger {
    void logEvent(Event event);
}
