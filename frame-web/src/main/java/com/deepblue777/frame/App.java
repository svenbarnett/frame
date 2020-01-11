package com.deepblue777.frame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 框架主应用文件入口
 *
 * @author pswen/3197544360@qq.com
 * @date 2019/10/11 15:40
 * @since 1.0
 */

@SpringBootApplication
// 使用tkmapper的扫描组件
@MapperScan(basePackages = {"com.deepblue777.frame.mapper"})
public class App extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("" +
                " _____  ______                        \n" +
                "|  __ \\|  ____|                       \n" +
                "| |  | | |__ _ __ __ _ _ __ ___   ___ \n" +
                "| |  | |  __| '__/ _` | '_ ` _ \\ / _ \\\n" +
                "| |__| | |  | | | (_| | | | | | |  __/\n" +
                "|_____/|_|  |_|  \\__,_|_| |_| |_|\\___|");
        System.out.println("starting ok！\n");

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(App.class);
    }
}
