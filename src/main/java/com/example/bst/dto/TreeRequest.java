package com.example.bst.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/** Accepts:
 *  {"numbers":[8,3,10,1,6],"balanced":true}
 *  {"values":[8,3,10,1,6],"balanced":true}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TreeRequest {
    @JsonProperty("numbers")
    @JsonAlias({"values"})
    private List<Integer> numbers;

    @JsonProperty("balanced")
    @JsonAlias({"isBalanced","balance"})
    private Boolean balanced = Boolean.FALSE;

    public TreeRequest() {}

    public List<Integer> getNumbers() { return numbers; }
    public void setNumbers(List<Integer> numbers) { this.numbers = numbers; }

    public boolean isBalanced() { return Boolean.TRUE.equals(balanced); }
    public void setBalanced(Boolean balanced) { this.balanced = balanced; }
}
