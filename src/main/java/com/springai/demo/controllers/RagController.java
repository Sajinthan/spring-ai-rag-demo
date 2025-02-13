package com.springai.demo.controllers;

import com.springai.demo.services.RagService;
import org.springframework.ai.document.Document;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rag")
public class RagController {

    private final RagService ragService;

    public RagController(RagService ragService) {
        this.ragService = ragService;
    }

    @PostMapping("/ingest")
    public ResponseEntity<String> ingestDocument(@RequestBody Map<String, String> request) {
        String content = request.get("content");
        if (content == null || content.isEmpty()) {
            return ResponseEntity.badRequest().body("Missing 'content' field in request body");
        }
        ragService.ingestDocument(content);
        return ResponseEntity.ok("Document ingested successfully!");
    }

    @PostMapping("/chat")
    public ResponseEntity<String> getRagResponse(@RequestBody Map<String, String> request) {
        String query = request.get("query");
        if (query == null || query.isEmpty()) {
            return ResponseEntity.badRequest().body("Missing 'query' field in request body");
        }
        return ResponseEntity.ok(ragService.generateRagResponse(query));
    }

    @PostMapping("/retrieve")
    public ResponseEntity<List<String>> retrieveRelevantDocs(@RequestBody Map<String, String> request) {
        String query = request.get("query");

        List<String> results = ragService.retrieveRelevantDocs(query)
                .stream()
                .map(Document::getText)
                .toList();

        return ResponseEntity.ok(results);
    }
}