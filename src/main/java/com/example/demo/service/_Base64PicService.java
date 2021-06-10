package com.example.demo.service;

import com.example.demo.AI.entity.FaceDetect;
import com.example.demo.AI.entity.tool.facedetect.FaceDetails;
import com.example.demo.AI.tool.FaceHandler;
import com.example.demo.entity.User;
import com.example.demo.entity._Base64Picture;
import com.example.demo.properties.Properties;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository._Base64PicRep;
import com.example.demo.tool.MyPictureIOS;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Data
@Service("_base64PicService")
public class _Base64PicService {
  @Autowired
  _Base64PicRep pictureRep;
  @Autowired
  MyPictureIOS myPictureIOS;
  @Autowired
  UserRepository userRepository;
  @Autowired
  FaceHandler faceHandler;
  @Autowired
  FaceService faceService;
  @Autowired
  PicFaceService picFaceService;

  /**
   * 获取picture的base64编码
   */
  public List<_Base64Picture> getPublicPictureByCategy(String userName, String categy){
    return _getPictureList(userName, categy, true);
  }

  public List<_Base64Picture> getPrivatePictureByCategy(String userName,String categy){
    return _getPictureList(userName, categy, false);
  }

  /**
   * 添加图片
   * @param list: 添加图片列表
   * @return Integer 成功添加的照片数量
   */
  public Integer addPictureList(List<_Base64Picture> list){
    Integer num=0;
    for (_Base64Picture pic:list) {
      try {
        if(_addPicture(pic)){
          num++;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return num;
  }

  public Integer deletePic(List<Long>list){
    Integer num = 0;
    for(Long id: list){
      _faceDeletePro(id);
      if(pictureRep.deleteById(id))
        num++;
    }
    return num;
  }

  public _Base64Picture findPictrueById(String username,Long Id){
    return pictureRep.findById(username,Id);
  }

  private List<_Base64Picture> _getPictureList(String username, String categy, Boolean ispublic){
    return pictureRep.findList(username,ispublic,categy);
  }
  private Boolean _addPicture(_Base64Picture picture) throws  IOException{
    picture.setId(pictureRep.findId());
    _faceProcess(picture);
    return pictureRep.addInfo(picture);
  }

  /**
   * 代处理
   * @param picture : 对图片进行人脸检测
   */
  private void _faceProcess(_Base64Picture picture){

    FaceDetect faceDetect = faceHandler.densityDetect(myPictureIOS.B64String2Bytes(picture));// 将图片base64转成byte[] 类型，然后检测人脸
    if(faceDetect != null) {//有人脸
      //对对共有照片进行分类,
      if(picture.getIspublic()){
        picture.setIshuman(true);
        AIClassifyProcess();//获取分类
      }
      //获取当前人脸的人脸库，暂时处理为私有的。
      String username = picture.getOwner();
      User user = userRepository.getUser(username);
      Long faceSetId = user.getFaceSet();
      int faceNum = faceService.addFaceList(faceDetect.getFaceDetails(),picture,faceSetId);
      if(faceNum>0){
        picture.setIshuman(true);
      }else{
        picture.setIshuman(false);
      }
    }else if(picture.getIspublic()){
      picture.setIshuman(false);
      AIClassifyProcess();
    }
  }

  void AIClassifyProcess(){}

  void _faceDeletePro(Long picId){
    _Base64Picture picture = pictureRep.findById(picId);
    if(picture == null||!picture.getIshuman())
      return;
    List<Long> facelist = picFaceService.delete(picId);//返回人脸
    for(Long faceId:facelist){
      faceService.delete(faceId);
    }
  }

  public _Base64Picture getPic(Long picId) {
    return pictureRep.findById(picId);
  }

  public List<_Base64Picture> findPictrueByList(List<Long> pictureIds) {
    List<_Base64Picture> pictureList = new LinkedList<>();
    for(Long picId:pictureIds){
      pictureList.add(getPic(picId));
    }
    return pictureList;
  }
}
