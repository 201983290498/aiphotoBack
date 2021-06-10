package com.example.demo.AI.entity.tool.facedetect;

public class FaceDetails {

    private int leftX;
    private int leftY;
    private int wight;
    private int hight;
    private double confidence;

  public int getLeftX() {
    return leftX;
  }

  public void setLeftX(int leftX) {
    this.leftX = leftX;
  }

  public int getLeftY() {
    return leftY;
  }

  public void setLeftY(int leftY) {
    this.leftY = leftY;
  }

  public int getWight() {
    return wight;
  }

  public void setWight(int wight) {
    this.wight = wight;
  }

  public int getHight() {
    return hight;
  }

  public void setHight(int hight) {
    this.hight = hight;
  }

  public double getConfidence() {
    return confidence;
  }

  public void setConfidence(double confidence) {
    this.confidence = confidence;
  }

  @Override
  public String toString() {
    return "FaceDetails{" +
      "leftX=" + leftX +
      ", leftY=" + leftY +
      ", wight=" + wight +
      ", hight=" + hight +
      ", confidence=" + confidence +
      '}';
  }

  public FaceDetails(int leftX, int leftY, int wight, int hight, double confidence) {
    this.leftX = leftX;
    this.leftY = leftY;
    this.wight = wight;
    this.hight = hight;
    this.confidence = confidence;
  }

  public FaceDetails() {
  }
}
