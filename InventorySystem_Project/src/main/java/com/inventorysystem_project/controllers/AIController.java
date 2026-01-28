package com.inventorysystem_project.controllers;

import com.inventorysystem_project.dtos.AIRequestDTO;
import com.inventorysystem_project.dtos.AIResponseDTO;
import com.inventorysystem_project.serviceinterfaces.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = "*") // Allow frontend access
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("/chat")
    public ResponseEntity<AIResponseDTO> chat(@RequestBody AIRequestDTO request) {
        AIResponseDTO response = aiService.processPrompt(request);
        return ResponseEntity.ok(response);
    }
}
