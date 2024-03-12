package rpc.service;

import rpc.common.Blog;

public class BlogServiceImpl implements BlogService{
    @Override
    public Blog getBlog(Integer id) {
        System.out.println("�ͻ��˲�ѯ��"+id+"�Ĳ���");
        return Blog.builder()
                .id(id)
                .title("My Blog")
                .userId(1)
                .build();
    }
}
