package com.example.blq.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 〈mybatis-plus配置累〉
 *
 * @author xmd
 * @create 2019/5/25
 * @since 1.0.0
 */
@Configuration
@MapperScan("com.example.blq.mapper")
public class MyBatisConfig {
}