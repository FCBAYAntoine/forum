package com.antoine.forum.controller;


import com.antoine.forum.common.AppResult;
import com.antoine.forum.common.ResultCode;
import com.antoine.forum.model.User;
import com.antoine.forum.services.IUserService;
import com.antoine.forum.utils.MD5Util;
import com.antoine.forum.utils.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "用户接口")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userservice;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public AppResult register(@ApiParam("用户名") @RequestParam("username") @NonNull String username,
                              @ApiParam("昵称") @RequestParam("nickname") @NonNull String nickname,
                              @ApiParam("密码") @RequestParam("password") @NonNull String password,
                              @ApiParam("确认密码") @RequestParam("passwordRepeat") @NonNull String passwordRepeat){

//        if(StringUtil.isEmpty(username)|| StringUtil.isEmpty(password)
//                || StringUtil.isEmpty(nickname)|| StringUtil.isEmpty(passwordRepeat)){
//            return AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE);
//        }

        if(!password.equals(passwordRepeat)){
            log.warn(ResultCode.FAILED_TWO_PWD_NOT_SAME.toString());
            return AppResult.failed(ResultCode.FAILED_TWO_PWD_NOT_SAME);
        }

        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);

        String salt = UUIDUtil.UUID_32();
        String encryptPassword = MD5Util.md5Salt(password, salt);
        user.setPassword(encryptPassword);
        user.setSalt(salt);

        userservice.createNormalUser(user);

        return AppResult.success();
    }
}
