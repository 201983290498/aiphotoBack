/**
  * Copyright 2021 bejson.com
  */
package com.example.demo.AI.entity;
import com.example.demo.AI.entity.tool.detect.FaceDetectDetailList;

import java.util.List;

/**
 * Auto-generated: 2021-05-24 11:30:56
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Detect{
    private long imageId;
    private int cost;
    private int faceNum;
    private List<FaceDetectDetailList> faceDetectDetailList;
    public void setImageId(long imageId) {
         this.imageId = imageId;
     }
    public long getImageId() {
         return imageId;
     }
    public void setCost(int cost) {
         this.cost = cost;
     }
    public int getCost() {
         return cost;
     }
    public void setFaceNum(int faceNum) {
         this.faceNum = faceNum;
     }
    public int getFaceNum() {
         return faceNum;
     }
    public void setFaceDetectDetailList(List<FaceDetectDetailList> faceDetectDetailList) {
         this.faceDetectDetailList = faceDetectDetailList;
     }
    public List<FaceDetectDetailList> getFaceDetectDetailList() {
         return faceDetectDetailList;
     }
}
