package com.mobai.community.controller;

import com.mobai.community.dto.AccessTokenDTO;
import com.mobai.community.dto.GiteeUser;
import com.mobai.community.provider.GiteeProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * AuthorizeController：与用户授权登录有关的操作
 * 1）
 * 2）
 * 3）
 *
 */
@Slf4j
@Controller
public class AuthorizeController {
    @Autowired
    private GiteeProvider giteeProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("59cb5baf63c749d31778bfa1d36f406e22be05685dd790d85afb88d25f936181");
        accessTokenDTO.setClient_secret("40d3bc3075fa5ffb25c463cbc1dca7ae9d1f67b73d34c36b695ac692ce3fcbf9");
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setCode(code);
        String accessToken = giteeProvider.getAccessToken(accessTokenDTO);
        GiteeUser user = giteeProvider.getUser(accessToken);

        return "index";
    }


}
