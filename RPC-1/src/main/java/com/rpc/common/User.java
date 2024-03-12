package com.rpc.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 *  '@Builder' ������ģʽ�ֽн�����ģʽ������˵������һ��������һ�����������û����������湹����ϸ�ڣ�
 *  ��ȴ���Ծ�ϸ�ؿ��ƶ���Ĺ�����̡�
 *  * '@NoArgsConstructor' ����һ���޲ι��췽��
 *  * '@AllArgsConstructor' ʹ�ú����һ�����캯�����ù��캯�����������������ֶ����Բ���
 *  * '@Data' �൱�� @Getter @Setter
 *      @RequiredArgsConstructor //����@Autowired
 *      @ToString @EqualsAndHashCode��5��ע��ĺϼ���
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String userName;
    private Boolean sex;
}
