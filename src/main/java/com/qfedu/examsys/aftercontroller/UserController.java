package com.qfedu.examsys.aftercontroller;

import com.github.pagehelper.Page;
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


    //登录

    @CrossOrigin
    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(String username, String password){
        User user = userService.login( username );
        String password1 = user.getPassword();
        if (password.equals( password1 )){
            return new JsonResult( 1,user );
        }
        return new JsonResult( 0,"账号或者密码错误" );
    }

    //退出
    @CrossOrigin
    @RequestMapping("/loout.do")
    @ResponseBody
    public JsonResult loout(HttpSession session){
        session.removeAttribute( "user" );
        session.invalidate();
        return new JsonResult( 1,"退出成功，客官再来玩啊~~~" );
    }

    //注册  默认注册成为学生

    @CrossOrigin
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
    @CrossOrigin
    @RequestMapping("/findAllRole.do")
    @ResponseBody
    public Map<String,Object> findAllRole(Integer rid,Integer page,Integer limit){
        List<User> list = userService.findAllRole(rid, page, limit );
        long total = ((Page) list).getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0); // 结合layui的表格组件，0表示成功
        map.put("msg", "");
        map.put("count", total);// 表中总记录数
        map.put("data", list); // 获取到的分页数据
        return map;
    }

    //修改个人密码
    @CrossOrigin
    @RequestMapping("/updatePassword.do")
    @ResponseBody
    public JsonResult updatePassword(Integer id, String password) {
        User user = userService.findUserById( id );
        user.setPassword( password );
        userService.updatePassword( user );

        return new JsonResult(1, "修改成功，请重新登陆" );
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
        User userById = userService.findUserById( id );
        Integer rid = userById.getRid();
        return new JsonResult( 1,rid );
    }

    /**
     * 根据用户Id查询用户信息
     * @param id 用户Id
     * @return  map
     */
    @CrossOrigin
    @RequestMapping("/findUserById.do")
    @ResponseBody
    public JsonResult findUserById(Integer id){
        User user = userService.findUserById(id);
        return new JsonResult( 1,user );
    }



}
