package com.ecnu.trivial.service;

import com.ecnu.trivial.TrivialApplication;
import com.ecnu.trivial.model.User;
import com.ecnu.trivial.vo.UserVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TrivialApplication.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void if_user_is_registered_then_register_result_is_negative_one() throws Exception {
        int registerResult = userService.register("cqh","635520704@qq.com","12345");
        Assert.assertEquals(-1, registerResult);
    }

    @Test
    public void if_user_registered_success_then_register_result_is_4() throws Exception {
        int registerResult = userService.register("444","444","4444");
        Assert.assertEquals(4, registerResult);
    }

    @Test
    public void if_user_does_not_exist_then_login_result_is_negative_one() throws Exception {
        int loginResult = userService.login("empty","empty");
        Assert.assertEquals(-1, loginResult);
    }

    @Test
    public void if_user_login_success_then_login_result_is_user_id() throws Exception {
        int loginResult = userService.login("635520704@qq.com","12345");
        Assert.assertEquals(1, loginResult);
    }

    @Test
    public void if_user_enter_wrong_password_then_login_result_is_zero() throws Exception {
        int loginResult = userService.login("635520704@qq.com","wrong");
        Assert.assertEquals(0, loginResult);
    }

    @Test
    public void search_user_by_search_name_cqh_by_email_empty_by_page_number_1() throws Exception {
        List<UserVo> searchUser = userService.searchUserBySearchKeyByPage("cqh","",1,10);
        Assert.assertEquals(1, searchUser.size());
    }

    @Test
    public void max_page_should_be_1_if_user_enter_search_name_empty_and_email_empty_and_page_size_10() throws Exception {
        int maxPage = userService.getMaxPageNumberBySearchKey("","",10);
        Assert.assertEquals(1, maxPage);
    }

    @Test
    public void get_all_user_ranking_by_score_desc() throws Exception {
        List<User> userList = userService.getAllUsersOrderByScore();
        boolean isScoreDesc = false;
        Assert.assertEquals(4, userList.size());
        for(int i=0;i<userList.size()-1;i++){
            if(userList.get(i).getScore()>=userList.get(i+1).getScore())
                isScoreDesc = true;

        }
        Assert.assertTrue(isScoreDesc);
    }


}