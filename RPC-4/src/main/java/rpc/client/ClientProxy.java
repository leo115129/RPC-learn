package rpc.client;

import rpc.common.RPCRequest;
import rpc.common.RPCResponse;
import lombok.AllArgsConstructor;
import rpc.client.IOClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zwy
 *
 * �ͻ��˴����Ѷ�̬�����װrequest����
 *
 * '@AllArgsConstructor'������lombok�е�ע�⡣ʹ�ú����һ�����캯�����ù��캯�����������������ֶ����Բ���
 *                        ����Ҳ����ΪʲôClientProxy����û���幹�캯������RPCClient�������ٴ���ClientProxyʱ��
 *                        ͨ�����캯�����θ� host �� port����
 */
@AllArgsConstructor
public class ClientProxy implements InvocationHandler {

    private String host;

    private int port;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // ����RPCRequest���󣬳�ʼ�����е��ĸ���Ҫ������ʹ����lombok�е�builder��
        // ��ʼ��interfaceName����ʼ��methodName����ʼ��params������ʼ��paramsTypes
        RPCRequest rpcRequest=RPCRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args)
                .paramsTypes(method.getParameterTypes())
                .build();
        // ����IOClient��ͨ���������������request�����ݴ��䣬�����ط������˴�����response
        RPCResponse rpcResponse = IOClient.sendRequest(host, port, rpcRequest);
        System.out.println("response:" +rpcResponse);
          // ��ȡRPCResponse�е�Ŀ�����ݣ���ΪRPCResponse�г���Ŀ�����ݣ�����״̬���״̬��Ϣ��Щ��Ŀ�����ݣ�
        return rpcResponse.getData();
    }

    /**
     * ����Client��Ҫ�ķ����class�������
     */
    <T> T getProxy(Class<T> clazz) {
        // ����Ŀ��ӿڵ����������Ŀ��ӿڣ���InvocationHandler����ʵ���࣬Ҳ���Ǳ��࣬this�������ɶ�̬������ʵ��
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }
}


