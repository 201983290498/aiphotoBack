package com.example.demo.repository;

import com.example.demo.entity.Face;

import java.util.List;

public interface FaceRep {

  boolean findById(Long Id);
  boolean insert(Face face);
  boolean delete(Long faceId);
  Long changeNumber(Long faceId,int num);
  List<Face> findByFaceSetId(Long faceSetId);
  Face findByFaceId(Long Id);
  void createTable();
}
