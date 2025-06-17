package com.example.gomaa.Controller;


import com.example.gomaa.Dto.ChatMessageDTO;
import com.example.gomaa.Repository.UserRepository;
import com.example.gomaa.Service.MistralAIService2;
import com.example.gomaa.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/chat2")
public class ChatController3 {

    private MistralAIService2 mistralAIService;
    private UserRepository userRepository;

    @Autowired
    public ChatController3(MistralAIService2 mistralAIService, UserRepository userRepository) {
        this.mistralAIService = mistralAIService;
        this.userRepository = userRepository;
    }

    @PostMapping("/ask")
    public String askAI(@RequestParam Long userId, @RequestParam String message) {
        return mistralAIService.getAIResponse(message, userId);
    }



    @GetMapping("/history")
    public List<ChatMessageDTO> getUserChatHistory(@RequestParam Long userId) {
        return mistralAIService.getUserChatHistory(userId);
    }

    @GetMapping("/latest")
    public ChatMessageDTO getLatestUserChat(@RequestParam Long userId) {
        return mistralAIService.getLatestUserChat(userId);
    }
}
