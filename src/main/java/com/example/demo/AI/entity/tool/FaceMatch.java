package com.example.demo.AI.entity.tool;

public class FaceMatch {
  private String faceName;
  private Long faceId;
  private double confidence;

  public String getFacename() {
    return faceName;
  }

  public void setFacename(String facename) {
    this.faceName = facename;
  }

  public Long getFaceId() {
    return faceId;
  }

  public void setFaceId(Long faceId) {
    this.faceId = faceId;
  }

  public double getConfidence() {
    return confidence;
  }

  public void setConfidence(double confidence) {
    this.confidence = confidence;
  }

  @Override
  public String toString() {
    return "FaceMatch{" +
      "facename='" + faceName + '\'' +
      ", faceId=" + faceId +
      ", confidence=" + confidence +
      '}';
  }

  public FaceMatch(String facename, Long faceId, double confidence) {
    this.faceName = facename;
    this.faceId = faceId;
    this.confidence = confidence;
  }

  public FaceMatch() {
  }
}
