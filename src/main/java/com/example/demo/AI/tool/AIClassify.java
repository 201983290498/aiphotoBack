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
        String content = "{\"picture\":"+ b64 +"}";
        HttpEntity<String> request = new HttpEntity<>(content,headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url,request,String.class);
        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(response.getBody());
        return null;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
