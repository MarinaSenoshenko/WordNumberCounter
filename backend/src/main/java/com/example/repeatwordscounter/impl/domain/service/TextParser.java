package com.example.repeatwordscounter.impl.domain.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Component
public class TextParser {

    private String[] read(MultipartFile file) {
        try (InputStream in = file.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(" ").append(line);
            }
            return text.toString().split("[^a-zA-Zа-яА-Я]+");
        } catch (IOException e) {
            return null;
        }
    }

    public HashMap<String, Integer> count(MultipartFile file) {
        String[] words = read(file);
        HashMap<String, Integer> wordWithCount = new HashMap<>();
        assert words != null;
        for (String word : words) {
            word = word.toLowerCase();
            if (word.matches(".*[a-zA-Zа-яА-Я]+.*")) {
                wordWithCount.put(word, wordWithCount.getOrDefault(word, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordWithCount.entrySet());
        sortedEntries.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        HashMap<String, Integer> sortedWordWithCount = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            sortedWordWithCount.put(entry.getKey(), entry.getValue());
        }

        return sortedWordWithCount;
    }
}
