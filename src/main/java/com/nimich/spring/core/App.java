package com.nimich.spring.core;

import com.nimich.spring.core.beans.Client;
import com.nimich.spring.core.beans.Event;
import com.nimich.spring.core.loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = context.getBean("another", App.class);

        Event event = context.getBean("event", Event.class);
        app.logEvent(event, "some event from user 1");

        event = context.getBean("event", Event.class);
        app.logEvent(event, "Some event from user 2");

        context.close();
    }

    private void logEvent(Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMessage(message);
        eventLogger.logEvent(event);
    }
}
