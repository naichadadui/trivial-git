package com.ecnu.trivial.service;

import com.ecnu.trivial.model.Admin;
import com.ecnu.trivial.model.User;

public interface BaseService {
    public User getCurrentUser(Integer userId);
    public Admin getCurrentAdmin(Integer adminId);
}
