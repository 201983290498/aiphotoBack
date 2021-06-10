/**
  * Copyright 2021 bejson.com
  */
package com.example.demo.AI.entity.tool.detect;

/**
 * Auto-generated: 2021-05-24 11:30:56
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class FaceDectectRectangleArea {

    private double upperLeftX;
    private double upperLeftY;
    private double lowerRightX;
    private double lowerRightY;

  public FaceDectectRectangleArea(double upperLeftX, double upperLeftY, double lowerRightX, double lowerRightY) {
    this.upperLeftX = upperLeftX;
    this.upperLeftY = upperLeftY;
    this.lowerRightX = lowerRightX;
    this.lowerRightY = lowerRightY;
  }

  public FaceDectectRectangleArea() {
  }

  public void setUpperLeftX(double upperLeftX) {
         this.upperLeftX = upperLeftX;
     }
     public double getUpperLeftX() {
         return upperLeftX;
     }

    public void setUpperLeftY(double upperLeftY) {
         this.upperLeftY = upperLeftY;
     }
     public double getUpperLeftY() {
         return upperLeftY;
     }

    public void setLowerRightX(double lowerRightX) {
         this.lowerRightX = lowerRightX;
     }
     public double getLowerRightX() {
         return lowerRightX;
     }

    public void setLowerRightY(double lowerRightY) {
         this.lowerRightY = lowerRightY;
     }
     public double getLowerRightY() {
         return lowerRightY;
     }

}
