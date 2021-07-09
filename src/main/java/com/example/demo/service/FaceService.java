package com.example.demo.service;


import com.example.demo.AI.entity.tool.FaceMatch;
import com.example.demo.AI.entity.tool.facedetect.FaceDetails;
import com.example.demo.AI.tool.FaceHandler;
import com.example.demo.entity.Face;
import com.example.demo.entity.PicFace;
import com.example.demo.entity._Base64Picture;
import com.example.demo.properties.Properties;
import com.example.demo.repository.FaceRep;
import com.example.demo.tool.MyPictureIOS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service("faceService")
public class FaceService {
  @Autowired
  FaceHandler faceHandler;
  @Autowired
  FaceRep faceRep;
  @Autowired
  MyPictureIOS myPictureIOS;
  @Autowired
  Properties properties;
  @Autowired
  PicFaceService picFaceService;

  public int addFaceList(List<FaceDetails>details, _Base64Picture picture,Long facesetId){
    int num = 0;
    for(FaceDetails detail: details){//先对人脸遍历
      double confidence = detail.getConfidence();//获取图片的存在人脸的置信度
      if(confidence<properties.getConfidence())//r
        continue;
      num++;//有人脸了
      try {//截取图片
        byte[] face = myPictureIOS.picCut(picture.getB64(),detail);
        List<FaceMatch> list = faceHandler.faceSearch(face, facesetId + "");//在图库中搜素这张图片
        //按照不同等级添加人脸
        String persontag = picture.getPersontag();//设置person的人脸这个多指属性
        if(list==null||list.get(0).getConfidence()<properties.getConfidence()){
          Long faceId = addFace(facesetId,face,picture.getId()+"的第"+num+"个人脸");
          if(persontag == null)
            persontag = faceId +"";
          else
            persontag += "," + faceId;
          picFaceService.add(new PicFace(faceId,picture.getId(),picture.getIspublic(),1.0));
        }else{
          for(int i = 0;i<list.size()&&i<2;i++){//对于每张照片，我们只匹配，我们只挑出来cofidence较高的选择
            FaceMatch faceMatch = list.get(i);
            if(i==0){
              faceRep.changeNumber(faceMatch.getFaceId(),1);
              if(persontag == null)
                persontag = faceMatch.getFaceId() +"";
              else
                persontag += "," + faceMatch.getFaceId();
              picFaceService.add(new PicFace(faceMatch.getFaceId(),picture.getId(),picture.getIspublic(),faceMatch.getConfidence()));
            }
            else{
              faceRep.changeNumber(faceMatch.getFaceId(),1);
              picFaceService.add(new PicFace(faceMatch.getFaceId(),picture.getId(),picture.getIspublic(),faceMatch.getConfidence()));
            }
          }
        }
//        只保存最相似的图片
        picture.setPersontag(persontag);
      } catch (IOException e) {
        log.info(picture.getOwner() + "的图片" + picture.getPicname() + "截取人脸失败");
        e.printStackTrace();
      }
    }
    return num;
  }
  //往人脸库中添加新的人脸
  public Long addFace(Long faceSet,byte[] face,String faceName){
    Face face1 = faceHandler.addFace(faceSet, face,faceName);
    face1.setNumber(1);
    faceRep.insert(face1);
    return face1.getFaceId();
  }

  public boolean delete(Long faceId){
    Long number = faceRep.changeNumber(faceId, -1);
    if(number==0){
      faceRep.delete(faceId);
    }
    return true;
  }
}
