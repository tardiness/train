package com.tardiness.netty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: shishaopeng
 * @project: train
 * @data: 2020/7/27 10:18
 * @Description:
 */
@SpringBootApplication
@ComponentScan({"com.tardiness.netty.server"})
public class NettyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class);
    }
}
