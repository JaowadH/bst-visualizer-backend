package com.example.bst.dto;

import java.time.Instant;
import java.util.UUID;

public record TreeSummary(UUID id, String numbers, Instant createdAt) {}
