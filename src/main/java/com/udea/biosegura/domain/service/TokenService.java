package com.udea.biosegura.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class TokenService {

    public String obtainUserFromToken(String header) throws JsonProcessingException {
     String email = "";
        if (header == null) {
            throw new IllegalArgumentException("No Auth Header");
        }
        try {
            String token = header.replace("Bearer", "");
            String[] chunks = token.strip().split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(payload);
            email= actualObj.get("email").asText();

        } catch (Exception e) {
            e.printStackTrace();
        }
         return email;
    }

    }

