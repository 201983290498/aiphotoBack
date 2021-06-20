package com.example.demo;

import com.example.demo.AI.tool.AIClassify;
import com.example.demo.AI.tool.OnlineAIClassify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClassifyTest {

    String b64 = "";
    String pic1 = "";
    String pic2 = "";

    @Autowired
    AIClassify aiClassify;

    @Autowired
    OnlineAIClassify imgCfy;

    @Test
    public void test(){
        System.out.println(aiClassify.classifyPic(b64));
    }

    @Test
    public void Online_test(){
        imgCfy.classifyPic(b64);
        imgCfy.classifyPic(pic1);
        imgCfy.classifyPic(pic2);
    }


}
