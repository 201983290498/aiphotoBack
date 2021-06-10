package com.example.demo.AI.entity.tool.detect;

import java.util.List;

public class FaceDetectLandmarkAreaList {
  List<Point> dots;

  public FaceDetectLandmarkAreaList() {
  }

  public FaceDetectLandmarkAreaList(List<Point> dots) {
    this.dots = dots;
  }

  public List<Point> getDots() {
    return dots;
  }

  public void setDots(List<Point> dots) {
    this.dots = dots;
  }
}
