package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;

@Service
public class AiPiiService {

    @Value("${groq.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String GROQ_URL =
        "https://api.groq.com/openai/v1/chat/completions";

    public String analyze(String text) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String prompt = """
    Look at this text and identify what PII types are present.
    Respond in exactly this format:
    Found: [comma separated list of PII types only]
    Risk: [one sentence about sensitivity]
    Example response: Found: phone number, email address, Aadhaar number
    Do NOT repeat PII values. Do NOT explain each one separately.
    Text: """ + text;

        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "llama-3.1-8b-instant");
        body.put("messages", List.of(message));
        body.put("max_tokens", 200);

        HttpEntity<Map<String, Object>> entity =
            new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response =
                restTemplate.postForEntity(GROQ_URL, entity, Map.class);
            List<Map> choices = (List<Map>) response.getBody().get("choices");
            Map msg = (Map) choices.get(0).get("message");
            return (String) msg.get("content");
        } catch (Exception e) {
            return "AI analysis unavailable: " + e.getMessage();
        }
    }
}