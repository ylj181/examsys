package com.qfedu.examsys.aftercontroller;

import com.qfedu.examsys.pojo.JsonResult;
import com.qfedu.examsys.pojo.User;
import com.qfedu.examsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    //登录

    @CrossOrigin
    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(String username, String password){
        User user = userService.login( username );
        String password1 = user.getPassword();

        return new JsonResult( 1,user );

    }

    //退出
    public JsonResult loout(HttpSession session){
        session.removeAttribute( "user" );
        session.invalidate();
        return new JsonResult( 1,"退出成功，客官再来玩啊~~~" );
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

    //管理员给老师进行注册 自动注册成老师
    @RequestMapping("/addTeacher")
    @ResponseBody
    public JsonResult addTeacher(User record){
        record.setRid(2);
        Date date = new Date( );
        record.setCreatetime( date );
        userService.insert(record);

        return new JsonResult( 1,"添加成功" );
    }


    //前端给出rid  查询出不同角色信息
    @RequestMapping("/findAllRole.do")
    @ResponseBody
    public JsonResult findAllRole(Integer rid){
        List<User> userList = userService.findAllRole( rid );
        return new JsonResult( 1,userList );
    }

    //修改个人密码
    @RequestMapping("/updatePassword.do")
    public void updatePassword(HttpSession session, HttpServletResponse response , String password) throws IOException {
        User user = (User) session.getAttribute( "user" );
        user.setPassword( password );
        userService.updatePassword( user );
        session.removeAttribute( "user" );
        response.sendRedirect( "login.html" );
    }

    //管理员删除指定id用户
    @RequestMapping("/deleteByPrimaryKey.do")
    @ResponseBody
    public JsonResult deleteByPrimaryKey(Integer id){
        userService.deleteByPrimaryKey( id );
        return new JsonResult( 1,"删除成功" );
    }

    //查出rid前端进行rid角色判断 隐藏不同权限菜单
    @CrossOrigin
    @RequestMapping("/findUserRid.do")
    @ResponseBody
    public JsonResult findUserRid(Integer id){
        System.out.println(id);


        return new JsonResult( 1,id );
    }

    /**
     * 根据用户Id查询用户信息
     * @param id 用户Id
     * @return  map
     */
    @RequestMapping("/findUserById.do")
    @ResponseBody
    public Map<String,Object> findUserById(Integer id){
        Map<String, Object> map = new HashMap<>();

        User user = userService.findUserById(id);

        if (user != null){
            map.put("code",1);
            map.put("info",user);
        }

        return map;
    }

}
