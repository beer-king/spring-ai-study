package com.controller;

import com.dto.AiStudyResponse;
import com.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    @GetMapping("/ask")
    public String ask(@RequestParam String question){
        return aiService.ask(question);
    }

    @GetMapping("/structured")
    public ResponseEntity<AiStudyResponse> structurd(@RequestParam String question){
        AiStudyResponse res = aiService.askStructured(question);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/test-validation")
    public AiStudyResponse testValidation() {
        return aiService.testValidation();
    }
}
