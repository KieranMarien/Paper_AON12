package com.example.todobackend;

import nl.martijndwars.webpush.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Arrays;

@RestController
@RequestMapping("/Subscribe")
public class MessageController {


    private final MessageService messageService;


    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public void subscribe(@RequestBody String subscription) throws ParseException {


        String[] sub = subscription.split(",,,,");


        Subscription.Keys keys = new Subscription.Keys(sub[1], sub[2]);
        Subscription newSub = new Subscription(sub[0], keys);


        messageService.subscribeToPoint(newSub, sub[3], sub[4]);
    }

}
