package rpc.service;

import rpc.common.User;

public interface UserService {
    User getUserByUserId(Integer id);

    Integer insertUserId(User user);
}
