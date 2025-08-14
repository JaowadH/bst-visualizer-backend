package com.example.bst.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
// Accepts {"numbers":[...], "balanced": true}
public class TreeRequest {
    private List<Integer> numbers;
    private boolean balanced;

    public TreeRequest() {}                 // required for Jackson

    public List<Integer> getNumbers() {     // getters/setters required
        return numbers;
    }
    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public boolean isBalanced() {
        return balanced;
    }
    public void setBalanced(boolean balanced) {
        this.balanced = balanced;
    }
}
