package rpc.server;

import rpc.service.*;

import java.util.HashMap;
import java.util.Map;

public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();

        Map<String, Object> serviceProvide = new HashMap<>();
        serviceProvide.put("rpc.service.UserService",userService);
        serviceProvide.put("rpc.service.BlogService",blogService);
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);  // ��userService���� serviceProvider
        serviceProvider.provideServiceInterface(blogService);  // ��blogService���� serviceProvider

        RPCServer RPCServer = new ThreadPoolRPCRPCServer(serviceProvider);
        RPCServer.start(8899);
    }
}
