package com.crudapp.crudapp.controller;

import com.crudapp.crudapp.entities.BroadcastMessage;
import com.crudapp.crudapp.entities.BroadcastUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.PublicKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/broadcast")
public class BroadcastMessageController {

    private final List<BroadcastUser> users = new ArrayList<>();
    private final List<BroadcastMessage> messages = new ArrayList<>();

    @PostMapping("/users")
    public ResponseEntity<BroadcastUser> addUser(@RequestBody BroadcastUser user) {
        users.add(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<BroadcastUser>> getAllUsers() {
        return ResponseEntity.ok(users);
    }

    @PostMapping("/messages")
    public ResponseEntity<BroadcastMessage> broadcastMessage(@RequestBody BroadcastMessage message) {

      BroadcastUser sender= message.getSender();
      Long senderId= sender.getId();
      String senderUsername= sender.getUsername();
      String content=message.getContent();
      message.setLocalDateTime(LocalDateTime.now(ZoneId.systemDefault()));
      messages.add(message);

        return ResponseEntity.ok(message);
    }

    @GetMapping("/receive")
    public ResponseEntity<List<BroadcastMessage>> receiveMessages(@RequestParam Long userId) {
        List<BroadcastMessage> receivedMessages = new ArrayList<>();
        for (BroadcastMessage message : messages) {
            if (!message.getSender().getId().equals(userId)) {

                receivedMessages.add(message);
            }
        }
        return ResponseEntity.ok(receivedMessages);
    }


}
