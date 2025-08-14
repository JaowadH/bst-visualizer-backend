package com.example.bst.controller;

import com.example.bst.dto.TreeRequest;
import com.example.bst.dto.TreeResponse;
import com.example.bst.dto.TreeSummary;
import com.example.bst.service.TreeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/trees")
public class TreeController {

    private final TreeService service;

    public TreeController(TreeService service) { this.service = service; }

    // CREATE
    @PostMapping(consumes = "application/json", produces = "application/json")
    public TreeResponse create(@RequestBody TreeRequest req) throws Exception {
        return service.create(req);
    }

    // LIST (paged)
    @GetMapping(produces = "application/json")
    public Map<String, Object> list(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "20") int limit
    ) {
        List<TreeSummary> items = service.list(offset, limit);
        return Map.of("items", items, "total", items.size());
    }

    // GET BY ID
    @GetMapping(path = "/{id}", produces = "application/json")
    public TreeResponse get(@PathVariable UUID id) throws Exception {
        return service.get(id);
    }

    // --- TEMP: echo raw JSON to verify request body reaches the server as JSON ---
    @PostMapping(path = "/_raw", consumes = "application/json", produces = "application/json")
    public Map<String, Object> raw(@RequestBody Map<String, Object> body) {
        return body;
    }

    // Better 400 message when JSON can't bind to TreeRequest
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, Object> badJson(HttpMessageNotReadableException ex) {
        return Map.of(
                "error", "Invalid JSON for TreeRequest",
                "detail", ex.getMostSpecificCause() != null ? ex.getMostSpecificCause().getMessage() : ex.getMessage()
        );
    }
}