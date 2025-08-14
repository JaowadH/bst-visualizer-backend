package com.example.bst.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BstBuilder {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int v){ this.value = v; }
    }

    public static Node build(List<Integer> nums){
        Node root = null;
        if (nums == null) return null;
        for (int v : nums) root = insert(root, v);
        return root;
    }

    private static Node insert(Node n, int v){
        if (n == null) return new Node(v);
        if (v <= n.value) n.left = insert(n.left, v);
        else n.right = insert(n.right, v);
        return n;
    }

    public static Node buildBalanced(List<Integer> nums){
        if (nums == null || nums.isEmpty()) return null;
        List<Integer> s = new ArrayList<>(nums);
        Collections.sort(s);
        return buildFromSorted(s, 0, s.size() - 1);
    }

    private static Node buildFromSorted(List<Integer> s, int l, int r){
        if (l > r) return null;
        int m = (l + r) / 2;
        Node n = new Node(s.get(m));
        n.left = buildFromSorted(s, l, m-1);
        n.right = buildFromSorted(s, m+1, r);
        return n;
    }
}
