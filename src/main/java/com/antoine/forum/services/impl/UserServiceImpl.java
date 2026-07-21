package com.antoine.forum.services.impl;

import com.antoine.forum.common.AppResult;
import com.antoine.forum.common.ResultCode;
import com.antoine.forum.dao.UserMapper;
import com.antoine.forum.exception.ApplicationException;
import com.antoine.forum.model.User;
import com.antoine.forum.services.IUserService;
import com.antoine.forum.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public void createNormalUser(User user) {
        if(user == null || StringUtil.isEmpty(user.getUsername())||
        StringUtil.isEmpty(user.getPassword())|| StringUtil.isEmpty(user.getNickname())||
        StringUtil.isEmpty(user.getSalt())){
            log.warn(ResultCode.FAILED_PARAMS_VALIDATE.toString());
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }

        User existuser = userMapper.selectByUserName(user.getUsername());
        if(existuser!=null){
            log.warn(ResultCode.FAILED_USER_EXISTS.toString());
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_USER_EXISTS));
        }

        user.setGender((byte)2);
        user.setArticleCount(0);
        user.setIsAdmin((byte)0);
        user.setState((byte)0);
        user.setDeleteState((byte)0);
        Date date =  new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);

        int row = userMapper.insertSelective(user);
        if(row!=1){
         log.warn(ResultCode.FAILED_CREATE.toString());
         throw new ApplicationException(AppResult.failed(ResultCode.FAILED_CREATE));
        }
        log.info("新用户创建成功，用户名：{}",user.getUsername());


    }
}
