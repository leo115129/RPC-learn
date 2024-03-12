package rpc.service;

import rpc.common.Blog;

public class BlogServiceImpl implements BlogService{
    @Override
    public Blog getBlog(Integer id) {
        System.out.println("客户端查询了"+id+"的博客");
        return Blog.builder()
                .id(id)
                .title("My Blog")
                .userId(1)
                .build();
    }
}
