package com.ecnu.trivial.controller.api;

import com.ecnu.trivial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
}
