package rpc.server;

import io.netty.channel.nio.NioEventLoopGroup;
import rpc.service.ServiceProvider;

public class NettyRPCServer implements RPCServer{

    private ServiceProvider serviceProvider;
    @Override
    public void start(int port) {
        // netty服务线程组负责建立连接(TCP/IP连接)，work负责具体的请求
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        System.out.println("Netty服务端启动了");
    }

    @Override
    public void stop() {

    }
}
