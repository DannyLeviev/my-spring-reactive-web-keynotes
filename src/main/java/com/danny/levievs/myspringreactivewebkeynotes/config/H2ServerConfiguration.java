package com.danny.levievs.myspringreactivewebkeynotes.config;


import org.h2.tools.Server;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class H2ServerConfiguration {

    private Server webServer;

    @EventListener(ContextRefreshedEvent.class)
    public void start() throws SQLException {
        this.webServer = Server.createWebServer(
                "-webPort", "8082",
                "-webAllowOthers",
                "-ifNotExists"
        ).start();
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {
        if (this.webServer != null) {
            this.webServer.stop();
        }
    }
}