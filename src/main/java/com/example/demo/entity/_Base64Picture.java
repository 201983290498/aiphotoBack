package com.example.demo.entity;


import lombok.Data;
import java.util.Base64;
import java.util.Date;

@Data
public class _Base64Picture implements Comparable<_Base64Picture>{
  private Long id;
  private String owner;
  //多指属性,多个属性;隔开不同的类别，其中最重要的分类放在最前面,公有照片的的预测分类最重要，私有照片的个人分类最重要
  private String categy;
  private String b64;
  private Boolean ispublic;
  private String picname;
  //多值属性
  private String persontag;
  private Boolean ishuman;
  private Date data;
  private String remark;

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

  public _Base64Picture(Long id, String owner, String categy, String b64, Boolean ispublic, String picname, String persontag, Boolean ishuman, Date data) {
    this.id = id;
    this.owner = owner;
    this.categy = categy;
    this.b64 = b64;
    this.ispublic = ispublic;
    this.picname = picname;
    this.persontag = persontag;
    this.ishuman = ishuman;
    this.data = data;
  }

  public _Base64Picture() {

  }

  public Picture Base64ToBytes(){
    byte[] bytes = null;
    String[] strings = b64.split(",");
    b64 = strings[1];
    Base64.Decoder decoder = Base64.getDecoder();
    bytes = decoder.decode(b64);

    return new Picture(id, owner, categy, bytes, ispublic, picname, persontag, ishuman);
  }

  @Override
  public int compareTo(_Base64Picture o) {
    if (data.getTime() - o.data.getTime()==0)
      return 0;
    else if(data.getTime() - o.data.getTime()>0)
      return 1;
    else
      return -1;
  }

  public _Base64Picture(Long id, String owner, String categy, String b64, Boolean ispublic, String picname, String persontag, Boolean ishuman, Date data, String remark) {
    this.id = id;
    this.owner = owner;
    this.categy = categy;
    this.b64 = b64;
    this.ispublic = ispublic;
    this.picname = picname;
    this.persontag = persontag;
    this.ishuman = ishuman;
    this.data = data;
    this.remark = remark;
  }
}
