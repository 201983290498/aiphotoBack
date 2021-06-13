package com.example.demo;


import com.example.demo.entity._Base64Picture;
import com.example.demo.repository._Base64PicRep;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class picTest {
    @Autowired
    private _Base64PicRep base64PicRep;

    @Test
    public void testCreate(){
        base64PicRep.createTable();
    }

    @Test
    public void testInsert(){
        _Base64Picture base64Picture = new _Base64Picture(1L,"hjw","hhh","dsad",false,"dsa","das",true);
        try {
            base64PicRep.addInfo(base64Picture);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
