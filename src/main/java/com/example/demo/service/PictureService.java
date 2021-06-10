package com.example.demo.service;

import com.example.demo.entity.Picture;
import com.example.demo.repository.PictureRep;
import com.example.demo.tool.MyPictureIOS;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Data
@Service("pictureService")
public class PictureService {

  @Autowired
  private PictureRep pictureRep;

  @Autowired
  public MyPictureIOS myPictureIOS;

  /**
   * 动态读写图片，然后返回地址
   * @param userName
   * @param categy
   * @return
   */
  public List<String> getPublicPictureByCategy(String userName,String categy){
    List<Picture> pictureList = _getPictureList(userName, categy, true);
    List<String> list = new LinkedList<String>();
    if(pictureList!=null){
      myPictureIOS.InitDir(pictureList.get(0));
      for (Picture pic:pictureList) {
        myPictureIOS.readBin2Image(pic);
        list.add(pic.getOwner()+ "/" +pic.getPicname());
      }
    }
    return list;
  }

  /**
   * 获取私人相册的图片，返回静态地址
   * @param userName
   * @param categy
   * @return
   */
  public List<String> getPrivatePictureByCategy(String userName,String categy){
    List<Picture> pictureList = _getPictureList(userName, categy, false);
    List<String> list = new LinkedList<String>();
    if(pictureList!=null){
      myPictureIOS.InitDir(pictureList.get(0));
      for (Picture pic:pictureList) {
          myPictureIOS.readBin2Image(pic);
        list.add(pic.getOwner()+ "/" +pic.getPicname());
      }
    }
    return list;
  }

  /**
   * 根据类别查找类
   * @param username
   * @param categy
   * @param ispublic
   * @return
   */
  private List<Picture> _getPictureList(String username,String categy,Boolean ispublic){
    return pictureRep.findList(username,ispublic,categy);
  }

  /**
   * 配下面的方法淘汰
   * @param picture
   * @return
   * @throws IOException
   */
  public Boolean _addPicture(Picture picture) throws IOException {
    return pictureRep.addInfoLocal(picture);
  }

  public Boolean _addPictureByBytes(Picture picture) throws  IOException{
    return pictureRep.addInfoBybytes(picture);
  }

  /**
   * 添加获取二进制数据流的数据集合
   * @param list
   * @return
   * @throws IOException
   */
  public Integer addPictureList(List<Picture> list){
    Integer num=0;
    for (Picture pic:list) {
      try {
        if(_addPictureByBytes(pic)){
          num++;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return num;
  }


  /**
   * 将图片类转成静态文件的访问路径
   * @param staticResourceName
   * @return
   */
  public List<String>  getUrlsList(List<String> staticResourceName){
    String title = "http://localhost:9999/";
    List<String> urls = new LinkedList<>();
    for (int i = 0; i < staticResourceName.size(); i++) {
      urls.add(title+staticResourceName.get(i));
    };
    return urls;
  }
}
