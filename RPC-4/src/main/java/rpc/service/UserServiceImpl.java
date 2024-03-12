package rpc.service;


import rpc.common.User;

import java.util.Random;


public class UserServiceImpl implements UserService {

    @Override
    public User getUserByUserId(Integer id) {
        System.out.println("客户端查询ID："+id+"用户");
        Random random=new Random();
        return User.builder().id(id).userName("用户"+id).sex(random.nextBoolean()).build();
    }

    @Override
    public Integer insertUserId(User user) {
        System.out.println("数据插入成功");
        return 1;
    }
}
