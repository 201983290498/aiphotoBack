package com.example.demo.AI.entity;

import com.example.demo.AI.entity.tool.facedetect.FaceDetails;

import java.util.List;

/**
 * 输入一张图片，输出这张图片中人员的数量以及图片中人员头部的位置信息。
 */
public class FaceDetect {

    private long imageId;
    private List<FaceDetails> faceDetails;
    private int faceNum;

    public void setImageId(long imageId) {
      this.imageId = imageId;
    }

    public long getImageId() {
      return imageId;
    }

    public void setFaceDetails(List<FaceDetails> faceDetails) {
      this.faceDetails = faceDetails;
    }

    public List<FaceDetails> getFaceDetails() {
      return faceDetails;
    }

    public void setFaceNum(int faceNum) {
      this.faceNum = faceNum;
    }

    public int getFaceNum() {
      return faceNum;
    }
}
