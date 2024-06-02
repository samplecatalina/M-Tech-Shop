package com.proj.ecom.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class SessionCreatedListener implements ApplicationListener<SessionCreatedEvent> {

    @Override
    public void onApplicationEvent(SessionCreatedEvent event) {
        System.out.println("Session created: " + event.getSessionId());
    }
}
