package com.ecnu.trivial.controller.api;

import com.ecnu.trivial.service.UserService;
import com.ecnu.trivial.vo.UserVo;
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

    @RequestMapping(value="/searchUserByName", method = RequestMethod.POST)
    public Map searchUserByName(@RequestParam("searchName")String searchKey){
        Map<String,Object> result = new HashMap<>();
        int searchResult = 0;
        String message;
        List<UserVo> searchUsers = userService.searchUserByName(searchKey);
        searchResult = searchUsers.size();
        switch (searchResult){
            case 0:
                message = "未找到符合条件的用户";
                break;
            default:
                /*如果符合条件用户存*/
                message = "找到了以下用户";
                break;
        }
        result.put("searchResult", searchResult);
        result.put("searchUsers",searchUsers);
        result.put("returnMessage", message);
        return result;
    }

    @RequestMapping(value="/searchUserByEmail", method = RequestMethod.POST)
    public Map searchUserByEmail(@RequestParam("searchEmail")String searchKey){
        Map<String,Object> result = new HashMap<>();
        int searchResult = 0;
        String message;
        List<UserVo> searchUsers = userService.searchUserByEmail(searchKey);
        searchResult = searchUsers.size();
        switch (searchResult){
            case 0:
                message = "未找到符合条件的用户";
                break;
            default:
                /*如果符合条件用户存*/
                message = "找到了以下用户";
                break;
        }
        result.put("searchResult", searchResult);
        result.put("searchUsers",searchUsers);
        result.put("returnMessage", message);
        return result;
    }

    @RequestMapping(value="/selectUserOrderByScore", method = RequestMethod.POST)
    public Map sortUserByScore(@RequestParam("pageNumber")int pageNumber,@RequestParam("pageSize")int pageSize){
        Map<String,Object> result = new HashMap<>();
        int sortResult = 0;
        String message;
        List<UserVo> usersVos = userService.getAllUsersOrderByScoreByPage(pageNumber,pageSize);
        sortResult = usersVos.size();
        switch (sortResult){
            case 0:
                message = "用户不存在";
                break;
            default:
                /*如果符合条件用户存*/
                message = "按分数排序成功";
                break;
        }
        result.put("sortResult", sortResult);
        result.put("sortUsers",usersVos);
        result.put("returnMessage", message);
        return result;
    }
}
