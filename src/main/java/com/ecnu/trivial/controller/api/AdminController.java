package com.ecnu.trivial.controller.api;

import com.ecnu.trivial.controller.web.BaseController;
import com.ecnu.trivial.service.AdminService;
import com.ecnu.trivial.vo.AdminLogVo;
import com.ecnu.trivial.vo.UserVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ecnu.trivial.controller.api.APIBaseController.PAGE_SIZE;

@RestController
@RequestMapping("/api/homepage")
public class AdminController extends BaseController{
    @Autowired
    private AdminService adminService;

    @RequestMapping(value="/admin/login", method = RequestMethod.POST)
    public Map login(@RequestParam("email")String email, @RequestParam("password") String password){
        Map<String,Object> result = new HashMap<>();
        int loginResult = 0;
        String message;
        loginResult = adminService.login(email,password);
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
                session.setAttribute("adminId", loginResult);
                message = "登录成功";
                break;
        }
        result.put("adminId", loginResult);
        result.put("returnMessage", message);
        return result;
    }

    @RequestMapping(value="/admin/register", method = RequestMethod.POST)
    public Map register(@RequestParam("nickname")String nickname,@RequestParam("email")String email,@RequestParam("password") String password){
        Map<String,Object> result = new HashMap<>();
        int registerResult = 0;
        String message;
        registerResult = adminService.register(nickname,email,password);
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
        result.put("adminId", registerResult);
        result.put("returnMessage", message);
        return result;
    }

    /*
    * 当用户输入搜索关键词并搜索时，
    * 返回第一页的搜索结果searchUsers
    * 并且返回总共的页码数maxPageNumber
    * */
    @RequestMapping(value="/searchAdminLogBySearchKey", method = RequestMethod.POST)
    public Map searchAdminLogBySearchKey(@RequestParam("searchId")int searchId,@RequestParam("pageNumber")int pageNumber){
        Map<String,Object> result = new HashMap<>();
        List<AdminLogVo> adminLogVos = adminService.getAdminLogsBySearchKeyByPage(searchId,pageNumber,PAGE_SIZE);
        int maxPageNumber = adminService.getMaxPageNumberBySearchKey(searchId,PAGE_SIZE);
        result.put("maxPageNumber", maxPageNumber);
        result.put("searchAdminLogs", adminLogListToJSONArray(adminLogVos).toString());
        System.out.println(adminLogListToJSONArray(adminLogVos).toString());
        return result;
    }

    /*
    * 当用户跳转页面时
    * 返回该页的搜索结果searchAdminLogs
    * */
    @RequestMapping(value="/admin/getAdminLogBySearchKeyByPageNumber", method = RequestMethod.POST)
    public Map getAdminLogByPageNumber(@RequestParam("adminId")int adminId,@RequestParam("pageNumber")int pageNumber){
        Map<String,Object> result = new HashMap<>();
        List<AdminLogVo> searchAdminLogs = adminService.getAdminLogsBySearchKeyByPage(adminId,pageNumber,PAGE_SIZE);
        result.put("searchAdminLogs",adminLogListToJSONArray(searchAdminLogs).toString());
        System.out.println(adminLogListToJSONArray(searchAdminLogs).toString());
        return result;
    }

    private JSONArray adminLogListToJSONArray(List<AdminLogVo> adminLogVos){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        int i = 1;
        for(AdminLogVo adminLogVo:adminLogVos){
            jsonObject.put("id",i);
            jsonObject.put("name",adminLogVo.getAdminName());
            switch (adminLogVo.getActionType()){
                case 0:
                    jsonObject.put("actionType","删除");
                    break;
                case 1:
                    jsonObject.put("actionType","添加");
                    break;
                default:
                    break;
            }
            jsonObject.put("submitTime",adminLogVo.getSubmitTime());
            jsonArray.add(jsonObject);
            i++;
        }
        return jsonArray;
    }

}
