package com.example.demo.controller;

import com.example.demo.service.ImageClassifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

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

  @GetMapping("/categy/create")
  public Boolean createCategy(String username,String categy){
    log.info(username+"正在添加"+categy);
    return service.addCategy(username,categy);
  }

}
