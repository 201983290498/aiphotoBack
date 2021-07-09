package com.example.demo.AI.tool;
import com.alibaba.fastjson.JSON;
import com.example.demo.AI.entity.tool.FaceMatch;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.bcop.api.sdk.ai.core.constant.Region;
import com.chinamobile.bcop.api.sdk.ai.core.exception.ClientException;
import com.chinamobile.bcop.api.sdk.ai.core.model.CommonJsonObjectResponse;
import com.chinamobile.bcop.api.sdk.ai.core.signature.AccessTokenResponse;
import com.chinamobile.bcop.api.sdk.ai.facebody.AiFaceBody;
import com.example.demo.AI.entity.*;
import com.example.demo.entity.Face;
import com.example.demo.entity._Base64Picture;
import com.example.demo.tool.MyPictureIOS;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

public class FaceHandler {
  private static AiFaceBody aiFaceBody;


  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private MyPictureIOS myPictureIOS;

  private static String accessKey = "CIDC-AK-e44d5186-6963-45ef-84c9-62f1e1e2eafe";
  private static String secretKey = "CIDC-SK-eec8229c-cba7-48f0-bef8-443c5f8ece08";
  private String access_token = null;//需要通过请求获取，有bug，因为access是暂时的需要实时更新

  public FaceHandler() {

  }

  public static AiFaceBody getInstance(){
    if(aiFaceBody == null){
      aiFaceBody =  new AiFaceBody(Region.POOL_CS, accessKey, secretKey);
    }
    return aiFaceBody;
  }

  /**
   *  人脸解读，可以读出多个细节，没有人脸时返回null
   * @param imgpath :
   * @return
   */
  public JSONObject faceDectect(String imgpath) {
    aiFaceBody = FaceHandler.getInstance();
    CommonJsonObjectResponse response = null;
    try {
      response = aiFaceBody.faceDetect(imgpath, null);
    } catch (ClientException e) {
      e.printStackTrace();
    }
    return  response.getCommonResult();
  }
  public JSONObject faceDectect(byte[] bytes){
    aiFaceBody = FaceHandler.getInstance();
    CommonJsonObjectResponse response = null;
    try {
      response = aiFaceBody.faceDetect(bytes,null);
    } catch (ClientException e) {
      e.printStackTrace();
    }
    return response.getCommonResult();
  }

  /**
   * 检测人脸密度,返回值一定有一个或者多个，但是confidence较低，也可能时null,待定。
   * @param bytes
   * @return
   */
  public FaceDetect densityDetect(byte[] bytes){
    aiFaceBody = getInstance();
    CommonJsonObjectResponse response = null;
    try {
      response = aiFaceBody.densityDetect(bytes, null);
    } catch (ClientException e) {
      e.printStackTrace();
    }
    System.out.println(response.getCommonResult());
    FaceDetect faceDetect = JSON.toJavaObject(response.getCommonResult(),FaceDetect.class);
    return faceDetect;
  }

  /**
   * 往人脸库中增加图片。返回人脸类
   * @return
   */
  public Face addFace(Long faceSetId,byte[] bytes,String faceName){
    aiFaceBody = getInstance();
    CommonJsonObjectResponse response = null;
    Face face = null;
    try {
      response = aiFaceBody.createFaceToFile(Math.toIntExact(faceSetId), bytes, faceName, "null", null, null);
      if(!response.getState().equalsIgnoreCase("ok"))
        return null;
      AddFace addFace = JSONObject.toJavaObject(response.getCommonResult(), AddFace.class);
      face = new Face(addFace.getFaceName(),addFace.getFaceSetId(),addFace.getFaceId());
    } catch (ClientException e) {
      e.printStackTrace();
    }
    return face;
  }

  /**
   * 删除人脸库中的人脸,post请求发送接送字符窜
   * @param faceSetId
   * @param faceId
   */
  public boolean deleteFace(Long faceSetId,Long faceId){
    String url = "https://smartlib-api-changsha-1.cmecloud.cn:8444/ecloud/ai/v1/face/v1/faceset/user/delete?access_token="+access_token;
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    String content = "{\"faceId\":"+ faceId +",\"faceSetId\":"+faceSetId+"}";
    HttpEntity<String> request = new HttpEntity<>(content,headers);
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
    ObjectMapper mapper = new ObjectMapper();
    try {
      DeleteFace deleteFace = mapper.readValue(responseEntity.getBody(),DeleteFace.class);
      if(deleteFace.getState().equalsIgnoreCase("ok"))
        return true;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * 找不到人脸会报错，查看两个人脸的相似度
   * @param pic1
   * @param pic2
   * @return
   */
  public double faceMath(_Base64Picture pic1,_Base64Picture pic2){
    aiFaceBody = FaceHandler.getInstance();
    CommonJsonObjectResponse response = null;
    byte[] bytes1 = myPictureIOS.B64String2Bytes(pic1);
    byte[] bytes2 = myPictureIOS.B64String2Bytes(pic2);
    try {
      response = aiFaceBody.faceMatch(bytes1,bytes2,null);
      if(!response.getState().equalsIgnoreCase("ok")){
        return 0.0;
      }
    } catch (ClientException e) {
      e.printStackTrace();
    }
    String[] split = response.getCommonResult().toJSONString().split(",");

    for (int i = 0; i < split.length; i++) {
      if(split[i].startsWith("\"confidence\"")) {
        String tag = split[i];
        return Double.parseDouble(tag.split(":")[1]);
      }
    }
    return 0;
  }

  public List<FaceMatch> faceSearch(byte[] bytes,String faceSetId){
    return faceSearch(bytes,faceSetId,6);
  }
  public List<FaceMatch> faceSearch(byte[] bytes,String faceSetId,int numbers){
    CommonJsonObjectResponse response = null;
    FaceMatchList faceMatchList = null;
    aiFaceBody = getInstance();
    try {
      response = aiFaceBody.faceSearch(bytes, faceSetId, numbers, null, null);
      if(!response.getState().equalsIgnoreCase("ok"))
        return null;
    } catch (ClientException e) {
      e.printStackTrace();
    }
    String strTem = response.getCommonResult().toString();
    int index = strTem.indexOf("\"results\":");
    String tag = strTem.substring(index+10,strTem.length()-1);
    return JSON.parseArray(tag, FaceMatch.class);
  }
  /** 在数据库中查找最相似的图片。
   *
   * @param pic
   * @param faceSetId
   * @param numbers
   */
  public List<FaceMatch> faceSearch(_Base64Picture pic,String faceSetId,int numbers){
    byte[] bytes = myPictureIOS.B64String2Bytes(pic);
    return faceSearch(bytes,faceSetId,numbers);
  }

  public List<FaceMatch> faceSearch(_Base64Picture pic,String faceSetId){
    return faceSearch(pic,faceSetId,6);
  }

  /**
   * 这里的参数appkey待定,添加删除人脸库
   * 输入facesetName faceSetDescip
   * @return 返回值为faceStoreId(int) faceStoreName(String) cost(int）
   * 组成的json字符窜
   * */
  public JSONObject createdFacesSet(String faceSetName, String facesetDesc){
    aiFaceBody = FaceHandler.getInstance();
    CommonJsonObjectResponse  response = null;
    try {
      /**
       * 这里的参数appkey待定
       */
      response = aiFaceBody.createFaceSet(faceSetName,facesetDesc,null,null);

    } catch (ClientException e) {
      e.printStackTrace();
    }
    return response.getCommonResult();
  }
  public boolean deleteFaceSet(Long Id){
    String url = "https://smartlib-api-changsha-1.cmecloud.cn:8444/ecloud/ai/v1/face/v1/faceset/delete?access_token=" + access_token;
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    String content = "{\"faceSetIds\":"+ Id+"}";
    HttpEntity<String>  request = new HttpEntity<>(content,headers);
    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
    ObjectMapper mapper = new ObjectMapper();
    try {
      DeleteSet deleteSet = mapper.readValue(response.getBody(), DeleteSet.class);
      if(deleteSet.getState().equalsIgnoreCase("ok"))
        return true;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }




  public AiFaceBody getAiFaceBody() {
    return aiFaceBody = getInstance();
  }
  public void setAiFaceBody(AiFaceBody aiFaceBody) {
    FaceHandler.aiFaceBody = aiFaceBody;
  }
  public static String getAccessKey() {
    return accessKey;
  }
  public static void setAccessKey(String accessKey) {
    FaceHandler.accessKey = accessKey;
  }
  public static String getSecretKey() {
    return secretKey;
  }
  public static void setSecretKey(String secretKey) {
    FaceHandler.secretKey = secretKey;
  }
  public String getTemAccess_token() {
    return access_token;
  }
  public void setTemAccess_token(String temAccess_token) {
    access_token = temAccess_token;
  }
  public void init(){
//    RestTemplateBuilder builder = new RestTemplateBuilder();
//    RestTemplate restTemplate1 = builder.build();
//    String url = "https://smartlib-api-changsha-1.cmecloud.cn:8444/ecloud/ai/oauth/getToken?client_id=CIDC-AK-e44d5186-6963-45ef-84c9-62f1e1e2eafe&client_secret=CIDC-SK-eec8229c-cba7-48f0-bef8-443c5f8ece08";
//    ResponseEntity<String> entity = restTemplate1.getForEntity(url, String.class);
//    ObjectMapper mapper = new ObjectMapper();
//    try {
//      AccessToken accessToken1 = mapper.readValue(entity.getBody(), AccessToken.class);
//      access_token = accessToken1.getAccess_token();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
    aiFaceBody = getInstance();
    AccessTokenResponse accessToken = aiFaceBody.getAccessToken();
    access_token = accessToken.getAccess_token();
  }
}
