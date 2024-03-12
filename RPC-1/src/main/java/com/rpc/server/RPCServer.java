package com.rpc.server;

import com.rpc.common.User;
import com.rpc.service.UserServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        try {
            // ����ServerSocket���󣬶˿ں�Ҫ��Clientһ��
            ServerSocket serverSocket = new ServerSocket(8899);
            System.out.println("������������");
            // BIO�ķ�ʽ����Socket��������֮�󷵻�Socket����
            while (true) {
                Socket socket = serverSocket.accept();
                // ����������֮�󣬿���һ���߳�������
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // socket����Ļ�ȡ�����������Ϊtargat����ʼ�����������
                            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                            // ��ȡ�ӿͻ��˴�������id����readInt��ȡ����������getUserByUserId����User��ֵ֮�󷵻�User����
                            Integer id = ois.readInt();
                            User userByUserId = userService.getUserByUserId(id);  // �⣡����Client��ҪServer���õķ�����
                            // ��Client��Ҫ��User���󷵻ظ��ͻ���Client
                            oos.writeObject(userByUserId);
                            oos.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("��IO�ж�ȡ���ݴ���");
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
