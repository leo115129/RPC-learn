package rpc.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RPCRequest implements Serializable {
    // �����ࣨ�ӿڣ������ͻ���ֻ֪���ӿ������ڷ�������ýӿ���ָ��ʵ����
    private String interfaceName;
    // ������
    private String methodName;
    // �����б�
    private Object[] params;
    // ��������
    private Class<?>[] paramsTypes;
}
