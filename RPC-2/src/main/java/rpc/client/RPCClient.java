package rpc.client;

import rpc.common.RPCRequest;
import rpc.common.User;
import rpc.service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * RPC客户端：调用服务器端的方法
 *  * 客户端建立socket连接，标定主机ip地址，指定程序使用的端口号，
 *  * 将socket作为输入输出流的target来创建输入输出流对象，
 *  * 客户端通过输出流传id给服务器，刷新流。
 *  * 客户端通过输入流获取服务器的的返回对象，打印。
 *  *
 *  * host:主机名，用于回送地址。主机名对应的IP地址，可以和别人通信（一个主机是一栋楼，这栋楼的名字是这个主机的ip地址）
 *  * port:端口号（一栋楼有很多个房间可以使用，这就是端口。一个程序就是一个人，如果要跟另外一个主机通信，
 *  *              需要开一个房间给他的程序使用）
 *  * Socket.getInputStream()：方法得到一个输入流，客户端的Socket对象上的getInputStream()方法
 *  *                          得到的输入流其实就是从服务器端发回的数据流。
 *  * Socket.GetOutputStream()：方法得到一个输出流，客户端Socket对象上的getOutputStream()方法
 *  *                           返回的输出流,就是将要发送到服务器端的数据流（其实是一个缓冲区，暂时存储将要发送过去的数据）。
 *  * java.io.ObjectOutputStream.flush()：此方法刷新流。这将写入所有缓冲的输出字节并刷新到基础流。
 *  * java.io.ObjectInputStream.readObject()：方法从ObjectInputStream中读取对象。读取该对象的类，类签名，类及其所有超类型的
 *  *                                         非瞬态和非静态字段的值。默认的反序列化的类可以使用writeObject和readObject方法被重写。
 *  *                                          由此对象引用的对象被传递地读，这样对象的完全等价的图形是由readObject重建。
 */
public class RPCClient {
    public static void main(String[] args) {
        // 初始化主机名ip和端口号port
        ClientProxy clientProxy=new ClientProxy("127.0.0.1",8899);
        UserService proxy = clientProxy.getProxy(UserService.class);//反射获得代理

        // 服务的方法1：通过id获取User
        User userByUserId = proxy.getUserByUserId(100);
        System.out.println(userByUserId);

        // 服务的方法2：（假装）插入一个User数据
        Integer t = proxy.insertUserId(new User(100, "张三", true));
        System.out.println(t);
    }
}
