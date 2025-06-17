package com.example.gomaa.Service;

import com.example.gomaa.Dto.ChatMessageDTO;
import com.example.gomaa.Repository.ChatMessageRepository;
import com.example.gomaa.Repository.UserRepository;
import com.example.gomaa.entity.ChatMessage;
import com.example.gomaa.entity.Users;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MistralAIService2 {

    private  MistralAiChatModel chatModel;
    private ChatMessageRepository chatMessageRepository;
    private UserRepository userRepository;

    public MistralAIService2(MistralAiChatModel chatModel, ChatMessageRepository chatMessageRepository, UserRepository userRepository) {
        this.chatModel = chatModel;
        this.chatMessageRepository = chatMessageRepository;
        this.userRepository = userRepository;
    }

    public String getAIResponse(String userMessage, Long userId) {
        ChatResponse response = chatModel.call(new Prompt(userMessage));
        String aiResponse = response.getResults().get(0).getOutput().getText();

        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        ChatMessage chatMessage = new ChatMessage(userMessage, aiResponse, user);
        chatMessageRepository.save(chatMessage);

        return aiResponse;
    }

    public List<ChatMessageDTO> getUserChatHistory(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        return chatMessageRepository.findByUserId(userId)
                .stream()
                .map(ChatMessageDTO::new)
                .collect(Collectors.toList());
    }

    public ChatMessageDTO getLatestUserChat(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        ChatMessage chatMessage = chatMessageRepository.findTopByUserIdOrderByTimestampDesc(userId)
                .orElseThrow(() -> new RuntimeException("No chat messages found for this user"));

        return new ChatMessageDTO(chatMessage.getId(),chatMessage.getUserMessage(),chatMessage.getAiResponse(),chatMessage.getTimestamp().toLocalDate());
    }
}
