package com.example.todobackend;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.jose4j.lang.JoseException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class notificationSend extends TimerTask {

    private PushService pushService;
    private Subscription sub;
    private String messageJson;

    public notificationSend(PushService push, Subscription subscription, String message) {
        this.pushService = push;
        this.sub = subscription;
        this.messageJson = message;

    }

    public void run(){
        try {
            pushService.send(new Notification(sub, messageJson));
        } catch (JoseException | GeneralSecurityException | IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
