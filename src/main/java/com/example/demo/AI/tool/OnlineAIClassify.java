package com.example.demo.AI.tool;

import lombok.Data;

@Data
public class OnlineAIClassify {

    private String AK;
    private String SK;

    public void Init(String ak,String sk){
        AK = ak;
        SK = sk;
    }


    public OnlineAIClassify() {
    }
}
