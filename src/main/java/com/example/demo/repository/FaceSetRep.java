package com.example.demo.repository;

import com.example.demo.AI.entity.FaceSet;

public interface FaceSetRep {

  public void creatTable();
  public FaceSet findById(Long Id);
  public Long findExistById(Long id);
  public Long addFaceSet(String faceSetName, String faceSetDesc);
  public boolean deleteFaceSetById(Long faceStoreId);
}
