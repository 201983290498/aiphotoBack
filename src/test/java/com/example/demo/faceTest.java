package com.example.demo;

import com.example.demo.entity.Face;
import com.example.demo.repository.FaceRep;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class faceTest {
  @Autowired
  FaceRep faceRep;

  @Test
  void testAdd(){
    System.out.println(faceRep.insert(new Face(3L, 2L, null, 0)));
  }

  @Test
  void testFind(){
    System.out.println(faceRep.findById(3L));
  }

  @Test
  void changeTest(){
    System.out.println(faceRep.findByFaceId(2L));
    System.out.println(faceRep.findByFaceSetId(2L));
    System.out.println(faceRep.changeNumber(2L, -2));
  }
}
