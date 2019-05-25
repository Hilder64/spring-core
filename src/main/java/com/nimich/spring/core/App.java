package com.nimich.spring.core;

import com.nimich.spring.core.beans.Client;
import com.nimich.spring.core.beans.ConsoleEventLogger;

public class App {
    private Client client;
    private ConsoleEventLogger consoleEventLogger;

    public static void main(String[] args) {
        App app = new App();
        app.client = new Client("1", "John Smith");
        app.consoleEventLogger = new ConsoleEventLogger();
        app.logEvent("some event from user 1");
    }

    private void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        consoleEventLogger.logEvent(message);
    }
}
