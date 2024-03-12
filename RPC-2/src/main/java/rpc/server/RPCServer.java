package rpc.server;

import rpc.common.RPCRequest;
import rpc.common.RPCResponse;
import rpc.common.User;
import rpc.service.UserServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  * RPC服务器端
 *  * 服务器端：创建ServerSocket对象监听客户端的连接（BIO），监听到连接之后，开启一个线程来处理，socket对象的获取输入
 *  * 输出流作为targat，初始化输入输出流，读取从客户端传过来的id，用输入流的readInt()读取出来，
 *  * 调用getUserByUserId，给User赋值之后返回User对象，把Client想要的User对象通过输出流返回给客户端Client，
 *  * 输出流刷新（flush）。
 *  *
 *  * ServerSocket：用于服务器端，监听客户端连接
 *  * ServerSocket.accept()：是一个阻塞方法，方法从连接请求队列中取出一个客户的连接请求，然后创建与客户连接的Socket对象，
 *  *                       并将它返回。如果队列中没有连接请求，accept()方法就会一直等待，直到接收到了连接请求才返回。
 *  * java.io.ObjectInputStream.readInt()：方法读取一个32位的int
 *  * java.io.ObjectOutputStream.writeObject(Object obj): 此方法将指定的对象写入ObjectOutputStream。该对象的类，类的签名，
 *  *                                                     以及类及其所有超类型的非瞬态和非静态字段的值被写入。
 */
public class RPCServer {
    public static void main(String[] args) throws IOException {
        // 初始化（客户端Client）需要的服务:UserServiceImpl
        UserServiceImpl userService=new UserServiceImpl();
        // 创建ServerSocket对象，端口号要和Client一致
        ServerSocket serverSocket=new ServerSocket(8899);
        // BIO的方式监听Socket，监听到之后返回Socket对象
        while(true){
            Socket socket=serverSocket.accept();

            new Thread(()->{
                try {
                    // 通过socket获取输入输出流
                    ObjectInputStream inputStream=new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream outputStream=new ObjectOutputStream(socket.getOutputStream());
                    // 读取客户端传过来的request
                    RPCRequest rpcRequest= (RPCRequest) inputStream.readObject();
                    // 反射调用方法
                    Method method = userService.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamsTypes());
                    Object invoke = method.invoke(userService, rpcRequest.getParams());

                    // 返回给客户端
                    outputStream.writeObject(RPCResponse.success(invoke));
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
