package com.example.demo.entity;

import lombok.Data;

@Data
public class Face {

  private Long faceId;
  private Long faceSetId;
  private String faceName;
  private int number; //当人脸数为0时自动删除该人人脸记录。

  @Override
  public String toString() {
    return "Face{" +
      "faceName='" + faceName + '\'' +
      ", faceSetId=" + faceSetId +
      ", faceId=" + faceId +
      ", number=" + number +
      '}';
  }


  public Face(String faceName, Long faceSetId, Long faceId) {
    this.faceName = faceName;
    this.faceSetId = faceSetId;
    this.faceId = faceId;
  }

  public Face() {
  }

  public Face(Long faceId, Long faceSetId, String faceName, int number) {
    this.faceId = faceId;
    this.faceSetId = faceSetId;
    this.faceName = faceName;
    this.number = number;
  }
}
