package com.ecnu.trivial.util;

import com.ecnu.trivial.model.User;
import com.ecnu.trivial.vo.UserVo;
import org.apache.commons.beanutils.BeanUtils;

public final class ObjectParse {
    private ObjectParse() {
    }

    public static UserVo parse(User user) {
        UserVo userVo = new UserVo();
        try {
            BeanUtils.copyProperties(userVo, user);
        } catch (Exception e) {

        }
        return userVo;
    }

}
