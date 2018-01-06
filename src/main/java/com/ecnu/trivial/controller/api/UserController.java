package com.ecnu.trivial.controller.api;

import com.ecnu.trivial.service.UserService;
import com.ecnu.trivial.vo.UserVo;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/homepage")
public class UserController extends APIBaseController{
    @Autowired
    private UserService userService;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public Map login(@RequestParam("email")String email,@RequestParam("password") String password){
        Map<String,Object> result = new HashMap<>();
        int loginResult = 0;
        String message;
        loginResult = userService.login(email,password);
        switch (loginResult){
            case -1:
                message = "没有该用户";
                break;
            case 0:
                message = "密码错误";
                break;
            default:
                /*如果该用户存在且密码正确*/
                HttpSession session = request.getSession();
                session.setAttribute("userId", loginResult);
                message = "登录成功";
                break;
        }
        result.put("userId", loginResult);
        result.put("returnMessage", message);
        return result;
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public Map register(@RequestParam("nickname")String nickname,@RequestParam("email")String email,@RequestParam("password") String password){
        Map<String,Object> result = new HashMap<>();
        int registerResult = 0;
        String message;
        registerResult = userService.register(nickname,email,password);
        switch (registerResult){
            case -1:
                message = "该邮箱已被注册";
                break;
            case 0:
                message = "注册失败";
                break;
            default:
                message = "注册成功";
                break;
        }
        result.put("userId", registerResult);
        result.put("returnMessage", message);
        return result;
    }

    /*
    * 当用户输入搜索关键词并搜索时，
    * 返回第一页的搜索结果searchUsers
    * 并且返回总共的页码数maxPageNumber
    * */
    @RequestMapping(value="/searchUserBySearchKey", method = RequestMethod.POST)
    public Map searchUserBySearchKey(@RequestParam("searchName")String searchName,@RequestParam("searchEmail")String searchEmail,@RequestParam("pageNumber")int pageNumber){
        Map<String,Object> result = new HashMap<>();
        List<UserVo> searchUsers = userService.searchUserBySearchKeyByPage(searchName,searchEmail,pageNumber,PAGE_SIZE);
        int maxPageNumber = userService.getMaxPageNumberBySearchKey(searchName,searchEmail,PAGE_SIZE);
        result.put("searchPageNumber", maxPageNumber);
        result.put("searchUsers",searchUsers);
        return result;
    }

    /*
    * 当用户跳转页面时
    * 返回该页的搜索结果searchUsers
    * */
    @RequestMapping(value="/getUsersBySearchKeyByPageNumber", method = RequestMethod.POST)
    public Map getUsersByPageNumber(@RequestParam("searchName")String searchName,@RequestParam("searchEmail")String searchEmail,@RequestParam("pageNumber")int pageNumber){
        Map<String,Object> result = new HashMap<>();
        List<UserVo> searchUsers = userService.searchUserBySearchKeyByPage(searchName,searchEmail,pageNumber,PAGE_SIZE);
        result.put("searchUsers",searchUsers);
        return result;
    }
}
