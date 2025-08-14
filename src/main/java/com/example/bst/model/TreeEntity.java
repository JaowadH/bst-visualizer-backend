package com.example.bst.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "trees")
public class TreeEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(columnDefinition = "text")
    private String numbersCsv;

    @Column(columnDefinition = "text")
    private String bstJson;

    @Column(columnDefinition = "text")
    private String balancedBstJson;

    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = Instant.now();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNumbersCsv() { return numbersCsv; }
    public void setNumbersCsv(String numbersCsv) { this.numbersCsv = numbersCsv; }

    public String getBstJson() { return bstJson; }
    public void setBstJson(String bstJson) { this.bstJson = bstJson; }

    public String getBalancedBstJson() { return balancedBstJson; }
    public void setBalancedBstJson(String balancedBstJson) { this.balancedBstJson = balancedBstJson; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
