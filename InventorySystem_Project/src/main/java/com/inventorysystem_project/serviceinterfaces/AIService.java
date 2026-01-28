package com.inventorysystem_project.serviceinterfaces;

import com.inventorysystem_project.dtos.AIRequestDTO;
import com.inventorysystem_project.dtos.AIResponseDTO;

public interface AIService {
    AIResponseDTO processPrompt(AIRequestDTO request);
}
