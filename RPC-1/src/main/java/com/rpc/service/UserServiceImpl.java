package com.rpc.service;


import com.rpc.common.User;

import java.util.Random;


public class UserServiceImpl implements UserService {

    @Override
    public User getUserByUserId(Integer id) {
        System.out.println("�ͻ��˲�ѯID��"+id+"�û�");
        Random random=new Random();
        return User.builder().id(id).userName("�û�"+id).sex(random.nextBoolean()).build();
    }
}
