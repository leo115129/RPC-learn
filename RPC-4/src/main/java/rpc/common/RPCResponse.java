package rpc.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * �����˷������˸��ͻ��˻�Ӧ�ĳ��� RPCResponse�������������֣�
 * 1.״̬��Ϣ��״̬��int code��״̬��ϢString message
 * 2.�������ݣ�Object data
 * ���⻹����success��������RPCResponse�����code״̬���ʼ��Ϊ200��data��ʼ��Ϊ�����data���󷵻�RPCResponse
 * ������fail��������RPCResponse�����code��ʼ��Ϊ500����״̬��Ϣmessage��ʼ��Ϊ"��������������"���󷵻�RPCResponse����
 *
 * �ϸ�������response�������User������Ȼ��һ��Ӧ�������ǲ�����ֻ����һ�����͵�����
 * �ɴ����ǽ������������ΪObject
 * RPC��Ҫ�������紫�䣬�п���ʧ�ܣ�������http������״̬���״̬��Ϣ��ʾ������óɹ�����ʧ��
 */
@Data
@Builder
public class RPCResponse implements Serializable {
    // ״̬��Ϣ
    private int code;

    private String message;
    // ��������
    private Object data;

    public static RPCResponse success(Object data) {
        return RPCResponse.builder().code(200).data(data).build();
    }

    public static RPCResponse fail() {
        return RPCResponse.builder().code(500).message("��������������").build();
    }
}
