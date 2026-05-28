package com.example.lodgings.Controller;

import com.example.lodgings.entity.ChatMessage;
import com.example.lodgings.service.ChatBotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatBotService chatBotService;

    public ChatController(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> sendMessage(@RequestBody Map<String, String> body) {
        String message = body.get("message");
        String sessionId = body.get("sessionId");
        if (message == null || message.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Message is required"));
        }
        Map<String, Object> result = chatBotService.processMessage(message, sessionId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/conversations")
    public ResponseEntity<List<ChatMessage>> getConversations() {
        return ResponseEntity.ok(chatBotService.getAllConversations());
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(Map.of(
                "totalConversations", chatBotService.getTotalConversations(),
                "recent", chatBotService.getAllConversations().stream().limit(20).toList()
        ));
    }
}
