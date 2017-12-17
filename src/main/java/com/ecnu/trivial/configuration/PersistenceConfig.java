package com.ecnu.trivial.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan("com.ecnu.trivial.mapper")
@EnableTransactionManagement
public class PersistenceConfig {

}