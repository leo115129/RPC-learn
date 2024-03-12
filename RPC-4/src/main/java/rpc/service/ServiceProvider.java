package rpc.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {

    /**
     * 一个类可能实现多个接口
     */
    private Map<String,Object> interfaceProvider;

    public ServiceProvider() {
        this.interfaceProvider = new HashMap<>();
    }

    public Object getService(String name){
        return interfaceProvider.get(name);
    }

    /**
     * 依次将一个类中的接口放入其中
     * @param service
     */
    public void provideServiceInterface(Object service){
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for (Class<?> i : interfaces) {
            interfaceProvider.put(i.getName(),service);
        }
    }
}
