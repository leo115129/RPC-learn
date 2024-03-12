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
 * RPC�ͻ��ˣ����÷������˵ķ���
 *  * �ͻ��˽���socket���ӣ��궨����ip��ַ��ָ������ʹ�õĶ˿ںţ�
 *  * ��socket��Ϊ�����������target�������������������
 *  * �ͻ���ͨ���������id����������ˢ������
 *  * �ͻ���ͨ����������ȡ�������ĵķ��ض��󣬴�ӡ��
 *  *
 *  * host:�����������ڻ��͵�ַ����������Ӧ��IP��ַ�����Ժͱ���ͨ�ţ�һ��������һ��¥���ⶰ¥�����������������ip��ַ��
 *  * port:�˿ںţ�һ��¥�кܶ���������ʹ�ã�����Ƕ˿ڡ�һ���������һ���ˣ����Ҫ������һ������ͨ�ţ�
 *  *              ��Ҫ��һ����������ĳ���ʹ�ã�
 *  * Socket.getInputStream()�������õ�һ�����������ͻ��˵�Socket�����ϵ�getInputStream()����
 *  *                          �õ�����������ʵ���Ǵӷ������˷��ص���������
 *  * Socket.GetOutputStream()�������õ�һ����������ͻ���Socket�����ϵ�getOutputStream()����
 *  *                           ���ص������,���ǽ�Ҫ���͵��������˵�����������ʵ��һ������������ʱ�洢��Ҫ���͹�ȥ�����ݣ���
 *  * java.io.ObjectOutputStream.flush()���˷���ˢ�������⽫д�����л��������ֽڲ�ˢ�µ���������
 *  * java.io.ObjectInputStream.readObject()��������ObjectInputStream�ж�ȡ���󡣶�ȡ�ö�����࣬��ǩ�����༰�����г����͵�
 *  *                                         ��˲̬�ͷǾ�̬�ֶε�ֵ��Ĭ�ϵķ����л��������ʹ��writeObject��readObject��������д��
 *  *                                          �ɴ˶������õĶ��󱻴��ݵض��������������ȫ�ȼ۵�ͼ������readObject�ؽ���
 */
public class RPCClient {
    public static void main(String[] args) {
        // ��ʼ��������ip�Ͷ˿ں�port
        ClientProxy clientProxy=new ClientProxy("127.0.0.1",8899);
        UserService proxy = clientProxy.getProxy(UserService.class);//�����ô���

        // ����ķ���1��ͨ��id��ȡUser
        User userByUserId = proxy.getUserByUserId(100);
        System.out.println(userByUserId);

        // ����ķ���2������װ������һ��User����
        Integer t = proxy.insertUserId(new User(100, "����", true));
        System.out.println(t);
    }
}
