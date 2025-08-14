package com.example.bst.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Accepts any of these payloads:
 * {"numbers":[8,3,10,1,6],"balanced":true}
 * {"values":[8,3,10,1,6],"balanced":true}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TreeRequest {
    @JsonProperty("numbers")              // canonical name
    @JsonAlias({"values"})                // also accept `values`
    private List<Integer> numbers;

    @JsonProperty("balanced")
    @JsonAlias({"balance","isBalanced"})  // accept a few variants
    private Boolean balanced = Boolean.FALSE;

    public TreeRequest() {}               // required by Jackson

    public List<Integer> getNumbers() { return numbers; }
    public void setNumbers(List<Integer> numbers) { this.numbers = numbers; }

    public boolean isBalanced() { return Boolean.TRUE.equals(balanced); }
    public void setBalanced(Boolean balanced) { this.balanced = balanced; }
}
