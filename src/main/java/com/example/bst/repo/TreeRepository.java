package com.example.bst.repo;

import com.example.bst.model.TreeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TreeRepository extends JpaRepository<TreeEntity, UUID> {}
