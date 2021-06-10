package com.example.demo.controller;

import com.example.demo.entity.Picture;
import com.example.demo.entity._Base64Picture;
import com.example.demo.service.PictureService;
import com.example.demo.tool.MyPictureIOS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@RestController
public class PictureController {

  @Autowired
  private PictureService service;

  @Autowired
  private MyPictureIOS myPictureIOS;


  @PostMapping("/pictures/addlist")
  public Integer upLoadPictures(@RequestBody List<_Base64Picture> pictureList){
    List<Picture> list = new LinkedList<>();
    for(_Base64Picture pic : pictureList){
      list.add(pic.Base64ToBytes());
    }
    log.info(list.size()+"");
    myPictureIOS.readBin2Image(list.get(0));
    return service.addPictureList(list);
  }

  @GetMapping("/pictures")
  public List<String> getCategyUrl(@RequestParam("username")String username,@RequestParam("categy") String categy, @RequestParam(name="ispublic", defaultValue = "false") String ispublic){
    List<String> name,urls;
    log.info(username+"正在获取"+categy+"相关的照片"+ispublic);
   if(ispublic.equals("false")){
      name = service.getPrivatePictureByCategy(username,categy);
    }else{
      name = service.getPublicPictureByCategy(username,categy);
    }
   urls = service.getUrlsList(name);
    return urls;
  }
}
