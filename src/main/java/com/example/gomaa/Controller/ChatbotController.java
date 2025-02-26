//package com.example.gomaa.Controller;
//
//import com.example.gomaa.Service.ChatbotService;
//import com.example.gomaa.entity.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/chatbot")
//public class ChatbotController {
//
//    private final ChatbotService chatbotService;
//
//    @Autowired
//    public ChatbotController(ChatbotService chatbotService) {
//        this.chatbotService = chatbotService;
//    }
//
//    @PostMapping("/{userId}")
//    public ResponseEntity<Message> saveMessage(@PathVariable Long userId, @RequestBody Map<String, String> request) {
//        String question = request.get("question");
//        Message message = chatbotService.saveMessage(userId, question);
//        return ResponseEntity.ok(message);
//    }
//
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<List<Message>> getMessagesByUser(@PathVariable Long userId) {
//        List<Message> messages = chatbotService.getMessagesByUserId(userId);
//        return ResponseEntity.ok(messages);
//    }
//}
