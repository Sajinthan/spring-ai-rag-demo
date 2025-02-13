package com.springai.demo.services;

import org.springframework.ai.document.Document;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RagService {

    private final List<Document> documentStore = new ArrayList<>();
    private final OllamaChatModel ollamaClient;

    @Autowired
    public RagService(OllamaChatModel ollamaClient) {
        this.ollamaClient = ollamaClient;
    }

    public void ingestDocument(String content) {
        documentStore.add(new Document(content));
    }

    public List<Document> retrieveRelevantDocs(String query) {
        return documentStore.stream()
                .filter(doc -> Objects.requireNonNull(doc.getText()).toLowerCase().contains(query.toLowerCase()))
                .limit(3)
                .collect(Collectors.toList());
    }

    public String generateRagResponse(String query) {
        List<Document> retrievedDocs = documentStore.stream()
                .toList();

        if (retrievedDocs.isEmpty()) {
            return "No relevant documents found.";
        }

        StringBuilder context = new StringBuilder("Context:\n");
        for (Document doc : retrievedDocs) {
            context.append(doc.getText()).append("\n");
        }

        String prompt = context + "\nUser Query: " + query + "\nProvide a well-informed answer based on the above context.";

        return ollamaClient.call(prompt);
    }
}