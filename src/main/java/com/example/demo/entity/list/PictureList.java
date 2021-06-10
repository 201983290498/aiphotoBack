package com.example.demo.entity.list;

import com.example.demo.entity.Picture;

import java.util.List;

public class PictureList {
  public List<Picture> pictureList;

  public List<Picture> getPictureList() {
    return pictureList;
  }

  public void setPictureList(List<Picture> pictureList) {
    this.pictureList = pictureList;
  }

  public PictureList(List<Picture> pictureList) {
    this.pictureList = pictureList;
  }

  public PictureList() {
  }
}
