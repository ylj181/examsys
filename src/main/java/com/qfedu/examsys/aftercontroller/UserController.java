package com.qfedu.examsys.aftercontroller;

import com.qfedu.examsys.pojo.JsonResult;
import com.qfedu.examsys.pojo.User;
import com.qfedu.examsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //登录

    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(String username,String password){
        User user = userService.login( username );
        String password1 = user.getPassword();
        if (password == password1){
            return new JsonResult( 1,"登陆成功" );
        }else {
            return new JsonResult( 0,"账号或密码错误" );
        }
    }

    //注册  默认注册成为学生

    @RequestMapping("/addUser")
    @ResponseBody
    public JsonResult insert(User record){
        record.setRid(3);
        Date date = new Date( );
        record.setCreatetime( date );
        userService.insert(record);

        return new JsonResult( 1,"添加成功" );
    }

    //前端给出rid  查询出
    @RequestMapping("/findAllRole.do")
    @ResponseBody
    public JsonResult findAllRole(Integer rid){
        List<User> userList = userService.findAllRole( rid );
        return new JsonResult( 1,userList );
    }


}
