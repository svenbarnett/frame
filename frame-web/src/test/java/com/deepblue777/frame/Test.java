package com.deepblue777.frame;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 一句话简单描述
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/12 18:26
 * @since 1.0
 */


@SpringBootApplication
@MapperScan(basePackages = "com.deepblue777.frame.*.mapper")
public class Test {
  public static void main(String args[]){
    SpringApplication.run(Test.class, args);
  }
}