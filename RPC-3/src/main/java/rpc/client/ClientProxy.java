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
 * 客户端代理：把动态代理封装request对象
 *
 * '@AllArgsConstructor'：它是lombok中的注解。使用后添加一个构造函数，该构造函数含有所有已声明字段属性参数
 *                        （这也就是为什么ClientProxy明明没定义构造函数，但RPCClient还可以再创建ClientProxy时，
 *                        通过构造函数传参给 host 和 port。）
 */
@AllArgsConstructor
public class ClientProxy implements InvocationHandler {

    private String host;

    private int port;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 构建RPCRequest对象，初始化其中的四个重要参数，使用了lombok中的builder。
        // 初始化interfaceName。初始化methodName，初始化params，，初始化paramsTypes
        RPCRequest rpcRequest=RPCRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args)
                .paramsTypes(method.getParameterTypes())
                .build();
        // 调用IOClient，通过输入输出流进行request的数据传输，并返回服务器端传来的response
        RPCResponse rpcResponse = IOClient.sendRequest(host, port, rpcRequest);
        System.out.println("response:" +rpcResponse);
          // 获取RPCResponse中的目标数据（因为RPCResponse中除了目标数据，还有状态码和状态信息这些非目标数据）
        return rpcResponse.getData();
    }

    /**
     * 传入Client需要的服务的class反射对象
     */
    <T> T getProxy(Class<T> clazz) {
        // 传入目标接口的类加载器，目标接口，和InvocationHandler（的实现类，也就是本类，this），生成动态代理类实例
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }
}


