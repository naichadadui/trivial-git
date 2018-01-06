package com.ecnu.trivial.service;

public interface AdminService extends BaseService {
    int login(String email,String password);
    int register(String nickname,String email,String password);
}
