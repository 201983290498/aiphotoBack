/**
  * Copyright 2021 bejson.com
  */
package com.example.demo.AI.entity.tool.detect;
import java.util.List;

/**
 * Auto-generated: 2021-05-24 11:30:56
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class FaceDetectDetailList {

    private String faceId;
    private FaceDectectRectangleArea faceDectectRectangleArea;
    private List<FaceDetectLandmarkAreaList> faceDetectLandmarkAreaList;
    private double faceScore;
    private double roll;
    private double pitch;
    private double raw;
    private int faceLandmarkNumber;
    private FaceDetectAttributes faceDetectAttributes;

  public FaceDetectDetailList() {
  }

  public FaceDetectDetailList(String faceId, FaceDectectRectangleArea faceDectectRectangleArea, List<FaceDetectLandmarkAreaList> faceDetectLandmarkAreaList, double faceScore, double roll, double pitch, double raw, int faceLandmarkNumber, FaceDetectAttributes faceDetectAttributes) {
    this.faceId = faceId;
    this.faceDectectRectangleArea = faceDectectRectangleArea;
    this.faceDetectLandmarkAreaList = faceDetectLandmarkAreaList;
    this.faceScore = faceScore;
    this.roll = roll;
    this.pitch = pitch;
    this.raw = raw;
    this.faceLandmarkNumber = faceLandmarkNumber;
    this.faceDetectAttributes = faceDetectAttributes;
  }

  public String getFaceId() {
    return faceId;
  }

  public void setFaceId(String faceId) {
    this.faceId = faceId;
  }

  public FaceDectectRectangleArea getFaceDectectRectangleArea() {
    return faceDectectRectangleArea;
  }

  public void setFaceDectectRectangleArea(FaceDectectRectangleArea faceDectectRectangleArea) {
    this.faceDectectRectangleArea = faceDectectRectangleArea;
  }

  public List<FaceDetectLandmarkAreaList> getFaceDetectLandmarkAreaList() {
    return faceDetectLandmarkAreaList;
  }

  public void setFaceDetectLandmarkAreaList(List<FaceDetectLandmarkAreaList> faceDetectLandmarkAreaList) {
    this.faceDetectLandmarkAreaList = faceDetectLandmarkAreaList;
  }

  public double getFaceScore() {
    return faceScore;
  }

  public void setFaceScore(double faceScore) {
    this.faceScore = faceScore;
  }

  public double getRoll() {
    return roll;
  }

  public void setRoll(double roll) {
    this.roll = roll;
  }

  public double getPitch() {
    return pitch;
  }

  public void setPitch(double pitch) {
    this.pitch = pitch;
  }

  public double getRaw() {
    return raw;
  }

  public void setRaw(double raw) {
    this.raw = raw;
  }

  public int getFaceLandmarkNumber() {
    return faceLandmarkNumber;
  }

  public void setFaceLandmarkNumber(int faceLandmarkNumber) {
    this.faceLandmarkNumber = faceLandmarkNumber;
  }

  public FaceDetectAttributes getFaceDetectAttributes() {
    return faceDetectAttributes;
  }

  public void setFaceDetectAttributes(FaceDetectAttributes faceDetectAttributes) {
    this.faceDetectAttributes = faceDetectAttributes;
  }
}
