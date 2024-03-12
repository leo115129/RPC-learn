package rpc.server;

import rpc.service.ServiceProvider;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRPCRPCServer implements RPCServer{

    private final ThreadPoolExecutor threadPoolExecutor;

    private ServiceProvider serviceProvider;

    public ThreadPoolRPCRPCServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
        this.threadPoolExecutor = new ThreadPoolExecutor(10, 100, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));
    }

    @Override
    public void start(int port) {
        System.out.println("线程池服务端启动了");
        try {
            ServerSocket serverSocket=new ServerSocket(port);
            while (true) {
                threadPoolExecutor.execute(new WorkThread(serverSocket.accept(),this.serviceProvider));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop() {

    }
}
