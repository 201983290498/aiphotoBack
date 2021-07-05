package com.example.demo.AI.tool;

import com.chinamobile.cmss.sdk.ECloudDefaultClient;
import com.chinamobile.cmss.sdk.IECloudClient;
import com.chinamobile.cmss.sdk.http.constant.Region;
import com.chinamobile.cmss.sdk.http.signature.Credential;
import com.chinamobile.cmss.sdk.request.EngineImageClassifyDetectPostRequest;
import com.chinamobile.cmss.sdk.response.EngineImageClassifyDetectResponse;
import com.chinamobile.cmss.sdk.response.bean.EngineClassify;
import com.chinamobile.cmss.sdk.util.JacksonUtil;
import lombok.Data;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Data
public class OnlineAIClassify {

    private String AK;
    private String SK;
    private Credential credentia;
    private IECloudClient eCloudClient;
    private static String[] categy = {"人","动物","自然风景","建筑","交通工具"};
    private static String[] categy1 = {"人","儿童","妇女","婴儿","青年"};
    private static String[] categy2 = {"动物","鼠","牛","虎","兔","蛇","马","羊","鸡","猴","狗","猪","猫","鸟","鹰","虫","鹿","狐","犬","狼",
                                        "鹅","鸭","狮","蝶"};
    private static String[] categy3 = {"自然","山","蓝天","云","植物","风景","林","树","木","湖","水"};
    private static String[] categy4 = {"建筑","楼","房","厦","屋"};
    private static String[] categy5 = {"交通工具","车","船","飞机"};


    public void Init(String ak,String sk){
        AK = ak;
        SK = sk;
        credentia = new Credential(AK,SK);
        eCloudClient = new ECloudDefaultClient(credentia, Region.POOL_SZ);
    }

    public String classifyPic(String b64){
        EngineImageClassifyDetectPostRequest request = new EngineImageClassifyDetectPostRequest();
        request.setImage(b64);
        request.setUserId("");

        String ans = "others";
        try {
            EngineImageClassifyDetectResponse response = eCloudClient.call(request);
            if("OK".equals(response.getState())){
                List<EngineClassify> body = response.getBody();
                List<String> list= Arrays.asList(body.get(0).getClasses().split(","));
                for(String each:list){
                    for(int i=0;i<categy1.length;i++)
                        if(each.matches("(.*)"+categy1[i]+"(.*)")) {
                            return categy[0];
                        }
                    for(int i=0;i<categy2.length;i++)
                        if(each.matches("(.*)"+categy2[i]+"(.*)")) {
                            return categy[1];
                        }
                    for(int i=0;i<categy3.length;i++)
                        if(each.matches("(.*)"+categy3[i]+"(.*)")) {
                            return categy[2];
                        }
                    for(int i=0;i<categy4.length;i++)
                        if(each.matches("(.*)"+categy4[i]+"(.*)")) {
                            return categy[3];
                        }
                    for(int i=0;i<categy5.length;i++)
                        if(each.matches("(.*)"+categy5[i]+"(.*)")) {
                            return categy[4];
                        }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return ans;
    }


    public OnlineAIClassify() {
    }
}
