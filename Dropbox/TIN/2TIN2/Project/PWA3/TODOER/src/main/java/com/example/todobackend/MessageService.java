package com.example.todobackend;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;

import nl.martijndwars.webpush.Subscription;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class MessageService {


    // Keys generated with npx web-push generate-vapid-keys, shouldn't be stored like this
    private String publicKey = "BPtkPuu4t5bvQo_Iwnb3w2a_95a2u5Z8L0-hk3MiDVCSKTvn1crRvGxAVEDWKAd9QK7Yupy90XTffvhoL9TkJAI";
    private String privateKey = "ipX2xGvj1kzinWBhujA6R3lR0-1TPlB-ZmZW5SQsFkg";

    private PushService pushService;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Timer timer = new Timer();


    @Autowired
    private void init() throws GeneralSecurityException {
        Security.addProvider(new BouncyCastleProvider());
        pushService = new PushService(publicKey, privateKey);
    }

    public void subscribeToPoint(Subscription sub, String date, String title) throws ParseException {

        System.out.println("Subbed to " + sub.endpoint);
        System.out.println(date);
        var json = String.format("""
        {
          "title": "%s should be done by now",
          "body": "Get On it!!!"
        }
        """, title);


        timer.schedule(new notificationSend(pushService, sub, json), formatter.parse(date.replace("T", " ")));
    }

}
