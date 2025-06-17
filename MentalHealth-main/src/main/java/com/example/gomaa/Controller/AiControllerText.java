package com.example.gomaa.Controller;



import com.example.gomaa.Dto.TextRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/ai")
public class AiControllerText {

    @PostMapping("/predict")
    public ResponseEntity<String> predictText(@RequestBody TextRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TextRequest> entity = new HttpEntity<>(request, headers);

        String flaskUrl = "http://127.0.0.1:3000/predict";

        ResponseEntity<String> response = restTemplate.postForEntity(flaskUrl, entity, String.class);

        return ResponseEntity.ok(response.getBody());
    }
}

