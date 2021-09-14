package com.mobai.community.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mobai.community.dto.AccessTokenDTO;
import com.mobai.community.dto.GiteeUser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class GiteeProvider {
    private final MediaType MEDIATYPEJSON = MediaType.get("application/json; charset=utf-8");

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        OkHttpClient client = new OkHttpClient();
        String json = JSON.toJSONString(accessTokenDTO);
        RequestBody body = RequestBody.create(MEDIATYPEJSON, json);
        Request request = new Request.Builder()
                .url("https://gitee.com/oauth/token?grant_type=authorization_code")
                .post(body)
                .build();
        try {
                Response response = client.newCall(request).execute();
                String string = response.body().string();
                JSONObject jsonObject = JSON.parseObject(string);
            return jsonObject.getString("access_token");

        } catch (Exception e) {
            log.info("获取access—token失败");
            e.printStackTrace();
        }
        return null;
    }

    public GiteeUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://gitee.com/api/v5/user"+"?access_token="+accessToken)
                .build();
//+" OAUTH-TOKEN"
        try {
            Response response = client.newCall(request).execute();
            String userJson = response.body().string();
            //System.out.println("获取User的返回信息："+userJson);
            return com.alibaba.fastjson.JSON.parseObject(userJson, GiteeUser.class);
        } catch (IOException e) {
            //log.info("获取user信息失败");
           // e.printStackTrace();
        }
        return null;
    }


}
