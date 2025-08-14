package com.example.bst.controller;

import com.example.bst.dto.TreeRequest;
import com.example.bst.dto.TreeResponse;
import com.example.bst.dto.TreeSummary;
import com.example.bst.service.TreeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/trees")
public class TreeController {

    private final TreeService service;

    public TreeController(TreeService service) { this.service = service; }

    @PostMapping
    public TreeResponse create(@RequestBody TreeRequest req) throws Exception {
        return service.create(req);
    }

    @GetMapping
    public Map<String, Object> list(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "20") int limit
    ) {
        List<TreeSummary> items = service.list(offset, limit);
        return Map.of("items", items, "total", items.size());
    }

    @GetMapping("/{id}")
    public TreeResponse get(@PathVariable UUID id) throws Exception {
        return service.get(id);
    }
}