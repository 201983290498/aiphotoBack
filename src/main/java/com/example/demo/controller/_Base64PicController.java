package com.example.demo.controller;


import com.example.demo.entity._Base64Picture;
import com.example.demo.service._Base64PicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@RestController
public class _Base64PicController {
  @Autowired
  private _Base64PicService service;

  @PostMapping("/b64pictures/addlist")
  public Integer upLoadPictures(@RequestBody List<_Base64Picture> pictureList){
    log.info(pictureList.size()+"");
    return service.addPictureList(pictureList);
  }

  @GetMapping("/b64pictures")
  public List<_Base64Picture> getCategyUrl(@RequestParam("username")String username, @RequestParam("categy") String categy, @RequestParam(name="ispublic", defaultValue = "false") String ispublic){
    List<_Base64Picture> pictureList = null;
    log.info(username+"正在获取"+categy+"相关的照片"+ispublic);
    if(ispublic.equals("false")){
      pictureList = service.getPrivatePictureByCategy(username,categy);
    }else{
      pictureList = service.getPublicPictureByCategy(username,categy);
    }
    return pictureList;
  }

  @GetMapping("/b64picture")
  public _Base64Picture getPicture(@RequestParam("username")String username,@RequestParam("id")Long Id ){
    log.info(username+Id);
    return service.findPictrueById(username,Id);
  }

  //修改，需要添加一个参数，人名
  @PostMapping("/b64pictures/delete")
  public Integer delPicById(@RequestBody List<Long> list){
    log.info("需要删除的图片列表便编号"+list);
    return service.deletePic(list);
  }
}
