package com.ecnu.trivial.controller.api;

import com.ecnu.trivial.dto.BaseJson;
import com.ecnu.trivial.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 11022 on 2017/8/20.
 */
public class APIBaseController{
    private static final String RESOURCE_NOT_FOUND = "没有找到资源";
    private static final String NOT_AUTHORIZED = "没有权限";
    private static final String WRONG_PARAM = "参数错误";

    private static final String USER_ID="userId";
    private static final String ROOM_ID="roomId";
    private static final String ADMIN_ID="adminId";

    public static final int PAGE_SIZE = 10;

    @Autowired
    protected HttpServletRequest request;

    public int getCurrentUserID(){
        Object o=request.getSession().getAttribute(USER_ID);
        if(o==null){
            throw new ResourceNotFoundException();
        }
        return Integer.parseInt(o.toString());
    }

    public int getCurrentAdminID(){
        Object o=request.getSession().getAttribute(ADMIN_ID);
        if(o==null){
            throw new ResourceNotFoundException();
        }
        return Integer.parseInt(o.toString());
    }

    public int getCurrentRoomID(){
        Object o=request.getSession().getAttribute(ROOM_ID);
        if(o==null){
            throw new ResourceNotFoundException();
        }
        return Integer.parseInt(o.toString());
    }

    protected BaseJson getUnauthorizedResult() {
        BaseJson baseJson = new BaseJson();
        baseJson.setReturnCode("E.1");
        baseJson.setErrorMessage(NOT_AUTHORIZED);
        return baseJson;
    }

    protected BaseJson getResourceNotFoundResult() {
        BaseJson baseJson = new BaseJson();
        baseJson.setReturnCode("E.2");
        baseJson.setErrorMessage(RESOURCE_NOT_FOUND);
        return baseJson;
    }

    protected BaseJson getWrongParamResult() {
        BaseJson baseJson = new BaseJson();
        baseJson.setReturnCode("E.3");
        baseJson.setErrorMessage(WRONG_PARAM);
        return baseJson;
    }
}
