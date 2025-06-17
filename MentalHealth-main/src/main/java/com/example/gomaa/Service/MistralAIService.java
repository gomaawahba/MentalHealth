package com.example.gomaa.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MistralAIService {

    @Value("${mistral.api.key}")  // تأكد من إضافة المفتاح في application.properties
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    // رابط API الخاص بـ Mistral AI
    private final String apiUrl = "https://api.mistral.ai/v1/chat/completions";

    public String getChatbotResponse(String userMessage) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "mistral-7b-instruct");  // استخدم النموذج المناسب من Mistral
        requestBody.put("messages", new Object[] {
                Map.of("role", "system", "content", "أنت مساعد ذكي."),
                Map.of("role", "user", "content", userMessage)
        });

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            Map<String, Object> response = restTemplate.postForObject(apiUrl, entity, Map.class);
            System.out.println("API Response: " + response);

            if (response != null && response.get("choices") != null) {
                return ((Map<String, Object>) ((List<?>) response.get("choices")).get(0)).get("message").toString();
            } else {
                return "عذرًا، لم أتلقَ أي إجابة.";
            }
        } catch (HttpClientErrorException e) {
            System.err.println("HTTP Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return "عذرًا، حدث خطأ في جلب الإجابة.";
        } catch (Exception e) {
            e.printStackTrace();
            return "عذرًا، حدث خطأ في جلب الإجابة.";
        }
    }
}
