package com.example.demo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
//@Component("imgClassify")
public class ImageClassfication {
  private Long id;
  private String owner;
  private String categy;
  private boolean isPublic;

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

}
