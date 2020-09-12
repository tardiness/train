package com.tardiness.netty.client;

import com.tardiness.netty.protobuf.UserMsg;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: shishaopeng
 * @project: train
 * @data: 2020/7/27 15:38
 * @Description:
 */
@ChannelHandler.Sharable
@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private AtomicInteger count = new AtomicInteger(1);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接成功: " + new Date());
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof UserMsg.User) {
            try {
                UserMsg.User user = (UserMsg.User) msg;

                log.info("接收到信息: age:[{}],name:[{}],status:[{}],id:[{}]",user.getAge(),user.getName(),user.getState(),user.getId());

                user = user.toBuilder().setState(1).build();

                ctx.writeAndFlush(user);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ReferenceCountUtil.release(msg);
            }

        } else {
            log.info("未知内容"+msg);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info("第[{}]次 请求心跳连接",count);
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent stateEvent = (IdleStateEvent) evt;
            if (IdleState.WRITER_IDLE.equals(stateEvent.state()) && count.get() < 10) {
                UserMsg.User user = UserMsg.User.newBuilder().setState(2).build();
                ctx.writeAndFlush(user);
            }
            count.getAndIncrement();
        }
    }
}
