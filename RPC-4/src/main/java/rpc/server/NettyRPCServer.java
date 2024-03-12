package rpc.server;

import io.netty.channel.nio.NioEventLoopGroup;
import rpc.service.ServiceProvider;

public class NettyRPCServer implements RPCServer{

    private ServiceProvider serviceProvider;
    @Override
    public void start(int port) {
        // netty�����߳��鸺��������(TCP/IP����)��work������������
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        System.out.println("Netty�����������");
    }

    @Override
    public void stop() {

    }
}
