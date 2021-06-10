package com.example.demo.entity;

import lombok.Data;

@Data
public class PicFace {
  private Long id;
  private Long faceId;
  private Long picId;
  private boolean ispublic;
  private double confident;

  public PicFace(Long faceId, Long picId) {
    this.faceId = faceId;
    this.picId = picId;
  }
  public PicFace(Long id, Long faceId, Long picId) {
    this.id = id;
    this.faceId = faceId;
    this.picId = picId;
  }

  public PicFace(Long id, Long faceId, Long picId, boolean ispublic, double confident) {
    this.id = id;
    this.faceId = faceId;
    this.picId = picId;
    this.ispublic = ispublic;
    this.confident = confident;
  }

  public PicFace(Long faceId, Long picId, boolean ispublic,double confident) {
    this.faceId = faceId;
    this.picId = picId;
    this.ispublic = ispublic;
    this.confident = confident;
  }

  public PicFace() {
  }

  @Override
  public String toString() {
    return "PicFace{" +
      "id=" + id +
      ", faceId=" + faceId +
      ", picId=" + picId +
      ", ispublic=" + ispublic +
      ", confident=" + confident +
      '}';
  }

}
