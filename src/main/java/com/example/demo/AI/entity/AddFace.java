/**
 * Copyright 2021 json.cn
 */
package com.example.demo.AI.entity;

/**
 * Auto-generated: 2021-05-24 17:15:14
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class AddFace {

  private long faceSetId;
  private String faceSetName;
  private String faceName;
  private long faceId;
  private int cost;
  public void setFaceSetId(long faceSetId) {
    this.faceSetId = faceSetId;
  }
  public long getFaceSetId() {
    return faceSetId;
  }

  public void setFaceSetName(String faceSetName) {
    this.faceSetName = faceSetName;
  }
  public String getFaceSetName() {
    return faceSetName;
  }

  public void setFaceName(String faceName) {
    this.faceName = faceName;
  }
  public String getFaceName() {
    return faceName;
  }

  public void setFaceId(long faceId) {
    this.faceId = faceId;
  }
  public long getFaceId() {
    return faceId;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }
  public int getCost() {
    return cost;
  }

}
