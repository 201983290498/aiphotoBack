package com.example.demo.entity;


import lombok.Data;

import java.io.InputStream;

@Data
public class Picture {

  private Long id;
  private String owner;
  private String categy;
  private byte[] binarypic;
  private Boolean ispublic;
  private String picname;
  private String persontag;
  private Boolean ishuman;

  public Picture(Long id, String owner, String categy, byte[] binarypic, Boolean ispublic, String picname, String persontag, Boolean ishuman) {
    this.id = id;
    this.owner = owner;
    this.categy = categy;
    this.binarypic = binarypic;
    this.ispublic = ispublic;
    this.picname = picname;
    this.persontag = persontag;
    this.ishuman = ishuman;
  }

  public Picture() {
  }

  public Picture(String owner, String categy, byte[] binarypic, Boolean ispublic, String picname, String persontag, Boolean ishuman) {
    this.owner = owner;
    this.categy = categy;
    this.binarypic = binarypic;
    this.ispublic = ispublic;
    this.picname = picname;
    this.persontag = persontag;
    this.ishuman = ishuman;
  }
}
