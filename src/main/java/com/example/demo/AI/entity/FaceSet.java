package com.example.demo.AI.entity;

import lombok.Data;

@Data
public class FaceSet {
  private Long cost;
  private Long faceStoreId;
  private String faceStoreName;

  public FaceSet(Long cost, Long faceStoreId, String faceStoreName) {
    this.cost = cost;
    this.faceStoreId = faceStoreId;
    this.faceStoreName = faceStoreName;
  }

  public FaceSet() {
  }
}
