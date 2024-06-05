package com.example.repeatwordscounter.impl.service;

import com.example.repeatwordscounter.impl.domain.service.TextParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;


@Service
public class CounterService {

    public HashMap<String, Integer> getInfo(MultipartFile file) {
        return new TextParser().count(file);
    }
}
