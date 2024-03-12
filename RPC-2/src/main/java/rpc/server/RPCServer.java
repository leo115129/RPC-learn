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
 *  * RPC��������
 *  * �������ˣ�����ServerSocket��������ͻ��˵����ӣ�BIO��������������֮�󣬿���һ���߳�������socket����Ļ�ȡ����
 *  * �������Ϊtargat����ʼ���������������ȡ�ӿͻ��˴�������id������������readInt()��ȡ������
 *  * ����getUserByUserId����User��ֵ֮�󷵻�User���󣬰�Client��Ҫ��User����ͨ����������ظ��ͻ���Client��
 *  * �����ˢ�£�flush����
 *  *
 *  * ServerSocket�����ڷ������ˣ������ͻ�������
 *  * ServerSocket.accept()����һ�������������������������������ȡ��һ���ͻ�����������Ȼ�󴴽���ͻ����ӵ�Socket����
 *  *                       ���������ء����������û����������accept()�����ͻ�һֱ�ȴ���ֱ�����յ�����������ŷ��ء�
 *  * java.io.ObjectInputStream.readInt()��������ȡһ��32λ��int
 *  * java.io.ObjectOutputStream.writeObject(Object obj): �˷�����ָ���Ķ���д��ObjectOutputStream���ö�����࣬���ǩ����
 *  *                                                     �Լ��༰�����г����͵ķ�˲̬�ͷǾ�̬�ֶε�ֵ��д�롣
 */
public class RPCServer {
    public static void main(String[] args) throws IOException {
        // ��ʼ�����ͻ���Client����Ҫ�ķ���:UserServiceImpl
        UserServiceImpl userService=new UserServiceImpl();
        // ����ServerSocket���󣬶˿ں�Ҫ��Clientһ��
        ServerSocket serverSocket=new ServerSocket(8899);
        // BIO�ķ�ʽ����Socket��������֮�󷵻�Socket����
        while(true){
            Socket socket=serverSocket.accept();

            new Thread(()->{
                try {
                    // ͨ��socket��ȡ���������
                    ObjectInputStream inputStream=new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream outputStream=new ObjectOutputStream(socket.getOutputStream());
                    // ��ȡ�ͻ��˴�������request
                    RPCRequest rpcRequest= (RPCRequest) inputStream.readObject();
                    // ������÷���
                    Method method = userService.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamsTypes());
                    Object invoke = method.invoke(userService, rpcRequest.getParams());

                    // ���ظ��ͻ���
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
