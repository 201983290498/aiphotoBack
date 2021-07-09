package com.example.demo.controller;


import com.example.demo.entity._Base64Picture;
import com.example.demo.service.PicFaceService;
import com.example.demo.service._Base64PicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@RestController
public class FaceRecognitionController {
  @Autowired
  PicFaceService picFaceService;
  @Autowired
  _Base64PicService base64PicService;

  @GetMapping("/face/collections")
  public List<Long> getSimilarFace(@RequestParam("id") Long picId,@RequestParam(name="confidence", defaultValue = "0.85",required = false)double confidence ){

    List<_Base64Picture> picList = null;
    List<Long> faceIdlist = new LinkedList<>();
    confidence = 0.56;
    log.info(picId+"匹配人脸置信度为下的图片"+confidence);
    _Base64Picture pic = base64PicService.getPic(picId);
    if(pic==null||!pic.getIshuman()){
      return null;
    }
    log.info(pic.getPersontag());
    String[] faceList = pic.getPersontag().split(",");
    for(String face:faceList)
      faceIdlist.add(Long.valueOf(face));
    List<Long> pictureIds = picFaceService.findSimilar(faceIdlist,pic.getIspublic(),confidence);
    return pictureIds;
  }
}
