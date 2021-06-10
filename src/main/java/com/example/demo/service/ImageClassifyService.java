package com.example.demo.service;

import com.example.demo.repository.ImageClassficationRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("imgClassifyService")
public class ImageClassifyService {

  @Autowired
  public ImageClassficationRep imageClassficationRep;

  /**
   * 查找某个人的私人类记录
   */
  public List<String> getCategyListByName(String userName,boolean ispublic){
    return imageClassficationRep.getCategy(userName,ispublic);
  }

  public List<String> getPublicCategyList(){
    return imageClassficationRep.findPublicCategy();
  }

  /**
   * 添加私人分类
   * @param userName
   * @param categy
   * @return
   */
  public boolean addCategy(String userName,String categy){
    if(!userName.equals("admin"))
      return imageClassficationRep.addInfo(userName,categy);
    else return false;
  }

  /**
   * 删除分类
   * @param userName
   * @param categy
   * @return
   */
  public boolean deleteCategy(String userName, String categy){
    if(!userName.equals("admin"))
      return imageClassficationRep.deleteInfo(userName,categy);
    else
      return false;
  }
}
