package com.example.bst.service;

import com.example.bst.dto.TreeRequest;
import com.example.bst.dto.TreeResponse;
import com.example.bst.dto.TreeSummary;
import com.example.bst.model.TreeEntity;
import com.example.bst.repo.TreeRepository;
import com.example.bst.util.BstBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TreeService {

    private final TreeRepository repo;
    private final ObjectMapper om = new ObjectMapper();

    public TreeService(TreeRepository repo) {
        this.repo = repo;
    }

    public TreeResponse create(TreeRequest req) throws Exception {
        // CHANGED: use getters instead of record-style accessors
        List<Integer> nums = (req != null && req.getNumbers() != null) ? req.getNumbers() : List.of();

        BstBuilder.Node bst = BstBuilder.build(nums);
        String bstJson = om.writeValueAsString(bst);

        JsonNode bstNode = om.readTree(bstJson);
        JsonNode balancedNode = null;
        String balancedJson = null;

        // CHANGED: use isBalanced()
        if (req != null && req.isBalanced()) {
            BstBuilder.Node bb = BstBuilder.buildBalanced(nums);
            balancedJson = om.writeValueAsString(bb);
            balancedNode = om.readTree(balancedJson);
        }

        TreeEntity e = new TreeEntity();
        e.setNumbersCsv(nums.stream().map(String::valueOf).collect(Collectors.joining(",")));
        e.setBstJson(bstJson);
        e.setBalancedBstJson(balancedJson);

        e = repo.save(e);

        return new TreeResponse(
                e.getId(),
                nums,
                bstNode,
                balancedNode,
                e.getCreatedAt()
        );
    }

    public List<TreeSummary> list(int offset, int limit) {
        return repo.findAll().stream()
                .sorted(Comparator.comparing(TreeEntity::getCreatedAt).reversed())
                .skip(Math.max(offset, 0L))
                .limit(Math.max(limit, 0))
                .map(e -> new TreeSummary(e.getId(), e.getNumbersCsv(), e.getCreatedAt()))
                .toList();
    }

    public TreeResponse get(UUID id) throws Exception {
        TreeEntity e = repo.findById(id).orElseThrow();
        JsonNode bstNode = om.readTree(e.getBstJson());
        JsonNode balancedNode = e.getBalancedBstJson() == null ? null : om.readTree(e.getBalancedBstJson());

        List<Integer> nums = Arrays.stream(
                        Optional.ofNullable(e.getNumbersCsv()).orElse("")
                                .split(","))
                .filter(s -> !s.isBlank())
                .map(Integer::parseInt)
                .toList();

        return new TreeResponse(
                e.getId(),
                nums,
                bstNode,
                balancedNode,
                e.getCreatedAt()
        );
    }
}
