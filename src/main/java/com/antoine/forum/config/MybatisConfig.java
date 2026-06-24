package com.antoine.forum.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.antoine.forum.dao")
public class MybatisConfig {

}
