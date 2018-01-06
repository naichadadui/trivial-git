package com.ecnu.trivial.util;

import com.ecnu.trivial.model.Admin;
import com.ecnu.trivial.model.AdminLog;
import com.ecnu.trivial.model.User;
import com.ecnu.trivial.vo.AdminLogVo;
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

    public static AdminLogVo parse(AdminLog adminLog){
        AdminLogVo adminLogVo = new AdminLogVo();
        try {
            BeanUtils.copyProperties(adminLogVo, adminLog);
        } catch (Exception e) {

        }
        return adminLogVo;
    }

}
