package com.tardiness.netty.client;

import com.tardiness.netty.protobuf.UserMsg;
import com.tardiness.netty.server.ServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * @author: shishaopeng
 * @project: train
 * @data: 2020/7/27 15:52
 * @Description:
 */
@Service("myClient")
@Slf4j
public class MyClient {

    @Value("${server.bind_address}")
    private String address;

    @Value("${server.bind_port}")
    private Integer port;

    private Boolean initFlag;

    private NioEventLoopGroup group;
    private ChannelFuture channelFuture;

    @PostConstruct
    public void init() {
        group = new NioEventLoopGroup();
        this.doConnect(group);
    }

    @PreDestroy
    public void destroy() {
        try {
            channelFuture.channel().closeFuture().sync();
            log.info("关闭客户端");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    /**
     * 连接
     * @param group
     */
    private void doConnect(NioEventLoopGroup group) {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new IdleStateHandler(0,4,0, TimeUnit.SECONDS));

                        pipeline.addLast(new ProtobufVarint32FrameDecoder());
                        pipeline.addLast(new ProtobufDecoder(UserMsg.User.getDefaultInstance()));
                        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                        pipeline.addLast(new ProtobufEncoder());

                        pipeline.addLast(new ClientHandler());
                    }
                });

        channelFuture = bootstrap.connect(address,port).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    log.info("连接成功!");
                } else {
                    EventLoop loop = future.channel().eventLoop();
                    loop.schedule(new Runnable() {
                        @Override
                        public void run() {
                            doConnect(group);
                        }
                    },10,TimeUnit.SECONDS);
                }
            }
        });
    }


}
