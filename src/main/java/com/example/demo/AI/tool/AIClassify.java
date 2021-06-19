package com.example.demo.AI.tool;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

public class AIClassify {
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public List<String> classifyPic(String[] b64){
        List<String> ans = new LinkedList<>();
        for(String each : b64){
            ans.add(classifyPic(each));
        }
        return ans;
    }

    public String classifyPic(String b64){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        System.out.println(url);
        String content = "{\"b64\":"+"\""+ b64 +"\"}";
        HttpEntity<String> request = new HttpEntity<>(content,headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url,request,String.class);
        String requestBody = response.getBody();
        return ChangeFromMyModelResutl(requestBody);
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String ChangeFromMyModelResutl(String result){
        System.out.println("puthon模型的预测结果为:"+result);
        if(result.equals("plane")||result.equals("ship")||result.equals("truck")||result.equals("car"))
            return "交通工具";
        else if(result.equals("bird")||result.equals("cat")||result.equals("deer")||result.equals("dog")||result.equals("horse")||result.equals("frog"))
            return "动物";
        else
            return "other";
    }

}

