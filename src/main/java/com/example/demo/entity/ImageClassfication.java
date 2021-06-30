package com.example.demo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data

public class ImageClassfication {
  private Long id;
  private String owner;
  private String categy;
  private boolean isPublic;
  private String remark;

  public ImageClassfication(Long id, String owner, String categy) {
    this.id = id;
    this.owner = owner;
    this.categy = categy;
  }

  public ImageClassfication() {
  }

  public ImageClassfication(String owner, String categy, boolean isPublic) {
    this.owner = owner;
    this.categy = categy;
    this.isPublic = isPublic;
  }

  public ImageClassfication(String owner, String categy, boolean isPublic, String remark) {
    this.owner = owner;
    this.categy = categy;
    this.isPublic = isPublic;
    this.remark = remark;
  }

  public ImageClassfication(Long id, String owner, String categy, boolean isPublic, String remark) {
    this.id = id;
    this.owner = owner;
    this.categy = categy;
    this.isPublic = isPublic;
    this.remark = remark;
  }
}
