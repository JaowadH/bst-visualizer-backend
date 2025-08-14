package com.example.bst.dto;

import com.fasterxml.jackson.databind.JsonNode;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record TreeResponse(
        UUID id,
        List<Integer> numbers,
        JsonNode bst,
        JsonNode balancedBst,
        Instant createdAt
) {}