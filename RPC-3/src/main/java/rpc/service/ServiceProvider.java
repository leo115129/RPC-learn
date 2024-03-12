package rpc.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {

    /**
     * һ�������ʵ�ֶ���ӿ�
     */
    private Map<String,Object> interfaceProvider;

    public ServiceProvider() {
        this.interfaceProvider = new HashMap<>();
    }

    public Object getService(String name){
        return interfaceProvider.get(name);
    }

    /**
     * ���ν�һ�����еĽӿڷ�������
     * @param service
     */
    public void provideServiceInterface(Object service){
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for (Class<?> i : interfaces) {
            interfaceProvider.put(i.getName(),service);
        }
    }
}
