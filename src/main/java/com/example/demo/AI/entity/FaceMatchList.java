package com.example.demo.AI.entity;

import com.example.demo.AI.entity.tool.FaceMatch;

import java.util.List;

public class FaceMatchList {
  public List<FaceMatch> results;

  @Override
  public String toString() {
    return "FaceMatchList{" +
      "list=" + results +
      '}';
  }

  public List<FaceMatch> getList() {
    return results;
  }

  public void setList(List<FaceMatch> list) {
    this.results = list;
  }

  public FaceMatchList(List<FaceMatch> list) {
    this.results = list;
  }

  public FaceMatchList() {
  }
}
