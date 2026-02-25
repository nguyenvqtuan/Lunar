package com.example.lunar.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

public class HashHelper {

    public static <T> String hash(T requestBody) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

            String json = mapper.writeValueAsString(requestBody);

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(json.getBytes(StandardCharsets.UTF_8));

            return HexFormat.of().formatHex(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("Hashing failed", e);
        }
    }
}
