package com.example.gomaa.Service;

import com.example.gomaa.Repository.MessageRepository;
import com.example.gomaa.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatbotService {

    private final MessageRepository messageRepository;
    private final MistralAIService mistralAIService;  // استبدال OpenAI بموديل Mistral

    @Autowired
    public ChatbotService(MessageRepository messageRepository, MistralAIService mistralAIService) {
        this.messageRepository = messageRepository;
        this.mistralAIService = mistralAIService;
    }

    public Message saveMessage(Long userId, String question) {
        String answer = mistralAIService.getChatbotResponse(question); // استدعاء Mistral بدلاً من OpenAI
        Message message = new Message();
        message.setUserId(userId);
        message.setQuestion(question);
        message.setAnswer(answer);
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByUserId(Long userId) {
        return messageRepository.findByUserId(userId);
    }
}
