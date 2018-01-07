package com.ecnu.trivial.mapper;

import com.ecnu.trivial.model.User;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hp on 2018/1/7.
 */
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void deleteByPrimaryKey() throws Exception {

    }
}