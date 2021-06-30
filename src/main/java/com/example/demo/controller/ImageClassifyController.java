package com.example.demo.controller;

import com.example.demo.service.ImageClassifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
class ImageClassifyController {

  @Autowired
  private ImageClassifyService service;

  @GetMapping("/categy")
  public List<String> findCatelists(@RequestParam(name = "username") String username,@RequestParam("ispublic") boolean ispublic){
    log.info(username);//记录日志
    return service.getCategyListByName(username,ispublic);
  }

  @GetMapping("/categy/delete")
  public Boolean deleteCatefy(String username,String categy){
    log.info(username+"正在删除"+categy);
    return service.deleteCategy(username,categy);
  }

  @PostMapping("/categy/create")
  public Boolean createCategy(@RequestBody Map<String, Object> data){
    String username = (String) data.get("username");
    String categy = (String) data.get("categy");
    String remark = (String) data.get("remark");
    log.info(username+"正在添加"+categy);
    return service.addCategy(username,categy,remark);
  }

}
