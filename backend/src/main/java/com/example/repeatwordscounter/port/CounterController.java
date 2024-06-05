package com.example.repeatwordscounter.port;

import com.example.repeatwordscounter.impl.service.CounterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("")
public class CounterController {
    private final CounterService counterService;

    @PostMapping()
    public HashMap<String, Integer> uploadFile(@RequestParam("file") MultipartFile file) {
        return counterService.getInfo(file);
    }
}
