package com.logistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author 2891517520@qq.com
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class LogisticsApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(LogisticsApplication.class, args);
        System.out.println("Application启动成功~");
    }
}
