package com.inventorysystem_project.dtos;

import java.util.List;

public class AIResponseDTO {
    private String message;
    private List<String> suggestions;
    private String actionType; // e.g., "NAVIGATE", "INFO", "NONE"
    private String actionPayload; // e.g., "/productos", "Stock level is 50"

    public AIResponseDTO(String message, List<String> suggestions, String actionType, String actionPayload) {
        this.message = message;
        this.suggestions = suggestions;
        this.actionType = actionType;
        this.actionPayload = actionPayload;
    }

    // Getters and Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public List<String> getSuggestions() { return suggestions; }
    public void setSuggestions(List<String> suggestions) { this.suggestions = suggestions; }
    public String getActionType() { return actionType; }
    public void setActionType(String actionType) { this.actionType = actionType; }
    public String getActionPayload() { return actionPayload; }
    public void setActionPayload(String actionPayload) { this.actionPayload = actionPayload; }
}
