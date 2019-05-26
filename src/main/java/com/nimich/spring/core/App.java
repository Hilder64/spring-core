package com.nimich.spring.core;

import com.nimich.spring.core.beans.Client;
import com.nimich.spring.core.beans.Event;
import com.nimich.spring.core.beans.EventType;
import com.nimich.spring.core.loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultEventLogger;
    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger defaultEventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultEventLogger = defaultEventLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = context.getBean("another", App.class);

        Event event = context.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = context.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event = context.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        context.close();
    }

    private void logEvent(EventType eventType, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMessage(message);

        EventLogger logger = loggers.get(eventType);
        if (logger == null) {
            logger = defaultEventLogger;
        }
        logger.logEvent(event);
    }
}
