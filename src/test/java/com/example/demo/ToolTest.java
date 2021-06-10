package com.example.demo;

import com.example.demo.AI.entity.Rectange;
import com.example.demo.AI.entity.tool.detect.Point;
import com.example.demo.AI.entity.tool.facedetect.FaceDetails;
import com.example.demo.entity._Base64Picture;
import com.example.demo.repository._Base64PicRep;
import com.example.demo.tool.MyPictureIOS;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class ToolTest {
  @Autowired
  MyPictureIOS pictureIOS;

  @Autowired
  _Base64PicRep base64PicRep;

  @Test
  void picCut(){
    _Base64Picture picture = base64PicRep.findById("cjmcjm", 6L);
    try {
      pictureIOS.picCut(picture.getB64(),new FaceDetails(550,8,481,534,0));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
