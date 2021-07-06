package com.example.demo.controller;


import com.example.demo.entity._Base64Picture;
import com.example.demo.service.UserService;
import com.example.demo.service._Base64PicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class _Base64PicController {
  @Autowired
  private _Base64PicService service;

  @Autowired
  UserService userService;

  @PostMapping("/b64pictures/addlist")
  public Integer upLoadPictures(@RequestBody List<_Base64Picture> pictureList){
    log.info(pictureList.size()+"");
    return service.addPictureList(pictureList);
  }

  @GetMapping("/b64pictures")
  public List<Long> getCategyUrl(@RequestParam("username")String username, @RequestParam("categy") String categy, @RequestParam(name="ispublic", defaultValue = "false") String ispublic){
    List<_Base64Picture> pictureList = null;
    log.info(username+"正在获取"+categy+"相关的照片"+ispublic);
    if(ispublic.equals("false")){
      pictureList = service.getPrivatePictureByCategy(username,categy);
    }else{
      pictureList = service.getPublicPictureByCategy(username,categy);
    }
    List<Long> numberlist = new LinkedList<>();
    for(_Base64Picture each:pictureList){
      numberlist.add(each.getId());
    }
    return numberlist;
  }
//  @PostMapping("/b64pictures/classified")
//  public List<_Base64Picture> getClassifiedPicFromPri(@RequestBody Map<String,Object> data){
//    String username = (String) data.get("username");
//    //待添加独立密码
//    String password = (String) data.get("pripassword");
//    if(userService.check_in_Ex(username,password)) {
//      String categy = (String) data.get("categy");
//      Boolean ispublic = data.containsKey("ispublic") ? true : (Boolean) data.get("ispublic");
//      List<_Base64Picture> pictureList = null;
//      log.info(username + "正在获取" + categy + "相关的照片" + ispublic);
//      pictureList = service.getClassifiedPic(username, categy);
//      return pictureList;
//    }else{
//      return null;
//    }
//  }
  @PostMapping("/b64pictures/classified")
  public List<Long> getClassifiedPicFromPri(@RequestBody Map<String,Object> data){
    String username = (String) data.get("username");
    //待添加独立密码
    String password = (String) data.get("pripassword");
    if(userService.check_in_Ex(username,password)) {
      String categy = (String) data.get("categy");
      Boolean ispublic = data.containsKey("ispublic") ? true : (Boolean) data.get("ispublic");
      List<_Base64Picture> pictureList = null;
      log.info(username + "正在获取" + categy + "相关的照片" + ispublic);
      pictureList = service.getClassifiedPic(username, categy);
      List<Long> numberlist = new LinkedList<>();
      for(_Base64Picture each:pictureList){
        numberlist.add(each.getId());
      }
      return numberlist;
    }else{
      return null;
    }
  }

  @GetMapping("/b64picture")
  public _Base64Picture getPicture(@RequestParam("username")String username,@RequestParam("id")Long Id ){
    log.info(username+Id);
    _Base64Picture pictrueById = service.findPictrueById(username, Id);
    return pictrueById;
  }

  //修改，需要添加一个参数，人名
  @PostMapping("/b64pictures/delete")
  public Integer delPicById(@RequestBody List<Long> list){
    log.info("需要删除的图片列表便编号"+list);
    return service.deletePic(list);
  }

  @GetMapping("/b64picture/setCollected")
  public Boolean setLiked(@RequestParam("id") Long id,@RequestParam("rank") int rank){
    return service.addCollected(id,rank);
  }

  @GetMapping("/b64picture/quitCollected")
  public Boolean delLiked(@RequestParam("id") Long id){
    return service.quitCollected(id);
  }

  @PostMapping("/b64picture/setRemark")
  public Boolean setRemark(@RequestBody Map<String,Object> data){
    Integer id = (Integer) data.get("id");
    Long id1 = Long.valueOf(id);
    String remark = (String) data.get("remark");
    return service.setRemark(id1,remark);
  }
}
