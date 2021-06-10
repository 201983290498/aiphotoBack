package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.AI.entity.AccessToken;
import com.example.demo.AI.entity.FaceDetect;
import com.example.demo.AI.entity.FaceSet;
import com.example.demo.AI.tool.FaceHandler;
import com.example.demo.entity.Face;
import com.example.demo.entity._Base64Picture;
import com.example.demo.repository._Base64PicRep;
import com.example.demo.tool.MyPictureIOS;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootTest
public class AITest {

  @Autowired
  private _Base64PicRep base64PicRep;

  @Autowired
  private MyPictureIOS myPictureIOS;

  @Autowired
  private FaceHandler faceHandler;

  @Autowired
  private RestTemplate restTemplate;

  /**
   * 获取
   * @throws IOException
   */
  @Test
  public void AccessToken_Access() throws IOException {
    String url = "https://smartlib-api-changsha-1.cmecloud.cn:8444/ecloud/ai/oauth/getToken?client_id=CIDC-AK-e44d5186-6963-45ef-84c9-62f1e1e2eafe&client_secret=CIDC-SK-eec8229c-cba7-48f0-bef8-443c5f8ece08";
    ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
    ObjectMapper mapper = new ObjectMapper();
    AccessToken accessToken = mapper.readValue(entity.getBody(), AccessToken.class);
    System.out.println(accessToken.getAccess_token());
  }

  @Test
  public void sdkTest(){
    JSONObject o = (JSONObject) faceHandler.createdFacesSet("cjmcjm-FaceSet", "build a set by sdk");
    System.out.println(o.toString());
    try {
      FaceSet faceSetResp = JSON.toJavaObject(o, FaceSet.class);
      System.out.println(faceSetResp.getFaceStoreId());
      System.out.println(faceSetResp.getCost());
      System.out.println(faceSetResp.getFaceStoreName());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void deletefaceSetTest(){
    boolean response = false;
    response = faceHandler.deleteFaceSet(117328L);
    System.out.println(response);
  }

  @Test
  public void faceDetect(){
    _Base64Picture cjmcjm = base64PicRep.findById("cjmcjm", 6L);
    byte[] bytes = myPictureIOS.B64String2Bytes(cjmcjm);
    JSONObject jsonObject = faceHandler.faceDectect(bytes);
    System.out.println(jsonObject);
  }

  /**
   * json字符窜和类的转换。
   */
  @Test
  public void densityFace(){
    _Base64Picture pic = base64PicRep.findById("cjmcjm",3L);
    byte[] bytes = myPictureIOS.B64String2Bytes(pic);
    FaceDetect faceDetect = faceHandler.densityDetect(bytes);
    System.out.println(faceDetect);
  }

  @Test
  public void addFace(){
    Long faceSet = 117300L;
    _Base64Picture pic = base64PicRep.findById("cjmcjm", 8L);
    byte[] bytes = myPictureIOS.B64String2Bytes(pic);
    JSONObject jsonObject = faceHandler.faceDectect(bytes);
    Face face = faceHandler.addFace(faceSet, bytes,"hhh01");
    System.out.println(face);
  }

  @Test
  public void deleteFace(){
    System.out.println(faceHandler.deleteFace(117300L, 76565L));
  }

  @Test
  public void faceMatch(){
    _Base64Picture  pic1 = base64PicRep.findById("cjmcjm",6L);
    _Base64Picture pic2 = base64PicRep.findById("cjmcjm",8L);
    System.out.println(faceHandler.faceMath(pic1, pic2));
  }

  @Test
  public void faceSearch(){
    _Base64Picture pic = base64PicRep.findById("cjmcjm", 8L);
    faceHandler.faceSearch(pic,"117300,117275");
  }
}
