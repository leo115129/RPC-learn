package rpc.client;

import rpc.common.RPCRequest;
import rpc.common.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * * IO Client���ײ��ͨ��
 *  * ͨ��Socket��������� RPCRequest �����������ˣ����յ��������˴����� RPCResponse��������� RPCResponse
 *  *
 */
public class IOClient {
    public static RPCResponse sendRequest(String host, int port, RPCRequest request) throws IOException, ClassNotFoundException {

        // �����ӣ�����Socket���󣬶���host��port
        Socket socket = new Socket(host, port);

        // �����������������
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        System.out.println("request: " + request);

        // �����д��request�Զ���ˢ�������
        objectOutputStream.writeObject(request);
        objectOutputStream.flush();

        // ͨ����������readObject�������õ��������˴�����RPCResponse��������RPCResponse����
        RPCResponse response = (RPCResponse) objectInputStream.readObject();

        return response;

    }
}
