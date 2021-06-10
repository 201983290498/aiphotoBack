package com.example.demo.entity;


import lombok.Data;
import java.util.Base64;

@Data
public class _Base64Picture {
  private Long id;
  private String owner;
  private String categy;
  private String b64;
  private Boolean ispublic;
  private String picname;
  //多值属性
  private String persontag;
  private Boolean ishuman;

  public _Base64Picture(Long id, String owner, String categy, String b64, Boolean ispublic, String picname, String persontag, Boolean ishuman) {
    this.id = id;
    this.owner = owner;
    this.categy = categy;
    this.b64 = b64;
    this.ispublic = ispublic;
    this.picname = picname;
    this.persontag = persontag;
    this.ishuman = ishuman;
  }

  public _Base64Picture() {

  }

  public Picture Base64ToBytes(){
    byte[] bytes = null;
    String[] strings = b64.split(",");
    b64 = strings[1];
    Base64.Decoder decoder = Base64.getDecoder();
    bytes = decoder.decode(b64);

    Picture picture = new Picture(id, owner, categy, bytes, ispublic, picname, persontag, ishuman);
    return picture;
  }
}
