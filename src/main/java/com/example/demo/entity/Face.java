package com.example.demo.entity;

import lombok.Data;

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
    this.number = 1;
  }

  public Face() {
  }

  public Face(Long faceId, Long faceSetId, String faceName, int number) {
    this.faceId = faceId;
    this.faceSetId = faceSetId;
    this.faceName = faceName;
    this.number = number;
  }

  public Long getFaceId() {
    return faceId;
  }

  public void setFaceId(Long faceId) {
    this.faceId = faceId;
  }

  public Long getFaceSetId() {
    return faceSetId;
  }

  public void setFaceSetId(Long faceSetId) {
    this.faceSetId = faceSetId;
  }

  public String getFaceName() {
    return faceName;
  }

  public void setFaceName(String faceName) {
    this.faceName = faceName;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }
}
