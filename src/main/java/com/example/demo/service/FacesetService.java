package com.example.demo.service;


import com.example.demo.AI.tool.FaceHandler;
import com.example.demo.repository.FaceSetRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("facesetService")
public class FacesetService {
  //注入一些内容
  @Autowired
  FaceSetRep faceSetRep;
  @Autowired
  FaceHandler faceHandler;

}
