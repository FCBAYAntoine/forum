package com.antoine.forum.controller;


import com.antoine.forum.common.AppResult;
import com.antoine.forum.exception.ApplicationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@Api(tags = "测试接口")
@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperation("测试接口1 显示你好spring")
    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }

    @ApiOperation("测试接口4 根据姓名显示你好信息")
    @PostMapping("/hellobyname")
    public String hellobyname(@ApiParam ("姓名") @RequestParam("name") String name){
        return "hello " + name;
    }

    @ApiOperation("测试接口2 显示抛出异常信息")
    @GetMapping("/exception")
    public AppResult excpetion() throws Exception{
        throw new Exception("这是一个EXCPTION");
    }

    @ApiOperation("测试接口3 显示自定义异常信息")
    @GetMapping("/appException")
    public AppResult applicationException(){
        throw new ApplicationException("这是一个APPEXCEPTION");
    }
}
