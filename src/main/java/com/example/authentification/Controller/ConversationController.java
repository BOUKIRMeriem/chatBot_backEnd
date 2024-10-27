package com.example.authentification.Controller;

import com.example.authentification.Entity.ConversationEntity;
import com.example.authentification.Entity.MessageEntity;
import com.example.authentification.Entity.UserEntity;
import com.example.authentification.Repository.ConversationRepository;
import com.example.authentification.Repository.UserRepository;
import com.example.authentification.Service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final ConversationService conversationService;
    @PostMapping
    public ResponseEntity<?> addNewConversation(@RequestBody ConversationEntity conversation) {
        Optional<UserEntity> userOptional = userRepository.findById(conversation.getUser().getId());
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("L'utilisateur avec l'ID " + conversation.getUser().getId() + " n'existe pas.");
        }
        conversation.setUser(userOptional.get());
        if (conversation.getMessages() != null) {
            for (MessageEntity message : conversation.getMessages()) {
                message.setConversation(conversation);
            }
        }
        ConversationEntity savedConversation = conversationRepository.save(conversation);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedConversation);
    }

    @PostMapping("/{conversationId}/messages")
    public ResponseEntity<Void> addMessageToConversation(
            @PathVariable int conversationId,
            @RequestBody MessageEntity message) {
        Optional<ConversationEntity> conversation = conversationRepository.findById(conversationId);
        if (conversation.isPresent()) {
            conversation.get().getMessages().add(message);
            conversationRepository.save(conversation.get());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<ConversationEntity>> getConversations(@PathVariable int userId) {
        List<ConversationEntity> conversations = conversationRepository.findByUserId(userId);
        if (conversations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(conversations);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConversation(@PathVariable int id) {
        boolean isRemoved = conversationService.deleteConversation(id);
        if (!isRemoved) {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }
        return ResponseEntity.ok().build(); // Return 200 if successfully deleted
    }



}
