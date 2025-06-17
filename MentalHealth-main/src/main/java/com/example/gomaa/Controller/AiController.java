package com.example.gomaa.Controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    @PostMapping("/analyze")
    public ResponseEntity<String> analyzeImage(@RequestParam("file") MultipartFile file) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // إعداد الصورة كـ multipart - المفتاح يجب أن يكون "file"
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename(); // مهم جدًا
            }
        });

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        String flaskUrl = "http://127.0.0.1:5000/predict";

        // إرسال الطلب واستلام الرد
        ResponseEntity<String> response = restTemplate.postForEntity(flaskUrl, requestEntity, String.class);

        return ResponseEntity.ok(response.getBody());
    }
}
