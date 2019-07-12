package com.marshall.sky.user;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ComponentScans(
    {
        @ComponentScan("com.marshall.sky.core"),
        @ComponentScan("com.marshall.sky.user"),
    }
)
@MapperScans(
    {
        @MapperScan("com.marshall.sky.core.token.user"),
        @MapperScan("com.marshall.sky.user.mapper"),
    }
)
@EnableElasticsearchRepositories(basePackages = "com.marshall.sky")
public class UserApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }
}
