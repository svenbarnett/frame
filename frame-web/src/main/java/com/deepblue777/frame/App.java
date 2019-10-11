package com.deepblue777.frame;

import com.deepblue777.frame.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 框架主应用文件入口
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/11 15:40
 * @since 1.0
 */

@SpringBootApplication
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
