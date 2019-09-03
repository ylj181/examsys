package com.qfedu.examsys.aftercontroller;

import com.qfedu.examsys.pojo.JsonResult;
import com.qfedu.examsys.pojo.User;
import com.qfedu.examsys.service.UserService;
import com.qfedu.examsys.utils.SendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author imlee
 * @Date 2019-09-02 10:10
 */
@CrossOrigin
@RestController
public class ValidateController {

    //  注入 Redis
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    /**
     *                      获取短信验证码
     *
     * @Author  imlee
     *
     * @param telephoneNumber   用户的手机号码
     * @return                  验证码发送成功的提示
     */
    @RequestMapping("/getCode.do")
    public JsonResult getCode(String telephoneNumber) {

        //    将用的短信验证码保存到Redis中
        String validateCode = SendSms.sendMessage(telephoneNumber);

        //  将短信验证码保存到 Redis 中，有效期为5分钟
        //  key  ： 手机号码
        //  value： 验证码
        redisTemplate.opsForValue().set(telephoneNumber, validateCode);
        redisTemplate.expire(telephoneNumber, 300, TimeUnit.SECONDS);

        return new JsonResult(1, "发送成功，请查收！");
    }

    /**
     *          注册账号第一步：使用手机号码进行预注册
     *
     * @Author  imlee
     *
     * @param telephoneNumber   用户提交的电话号码
     * @param telephoneCode     短信验证码
     * @return
     */
    @RequestMapping("/signUpFirst.do")
    public JsonResult signUp(String telephoneNumber, String telephoneCode) {

        User user = userService.signInByTelephoneNumber(telephoneNumber);
        if (user != null) {
            return new JsonResult(0, "此号码已注册,请直接登录！");
        }

        //  按照 手机号码 + 短信验证码 注册
        //      获取 保存在 redis 中的短信验证码
        String code = redisTemplate.opsForValue().get(telephoneNumber);

        //  判断短信验证码是否过期
        if (code == null || code.equals("")) {
            return new JsonResult(0, "验证码已过期，请重新获取！");
        } else {
            //  验证 用户输入的验证码和redis中保存的验证码是否一致
            if (telephoneCode.equals(code)) {

                //  验证码正确，执行注册逻辑
                int i = userService.signUpFirst(telephoneNumber);

                if (i != 1) {
                    //  返回受影响行数，不为1，即注册失败
                    return new JsonResult(0, "注册失败！");
                }

            } else {
                //  验证码错误，返回提示
                return new JsonResult(0, "验证码输入有误，请重新输入！");
            }
        }

        return new JsonResult(1, telephoneNumber);
    }

    @RequestMapping("/signUp.do")
    public JsonResult signUp(String username, String password, String telephoneNumber, String validate) {

        //  获取 redis 中保存的验证码文字
        String codeStr = redisTemplate.opsForValue().get("codeStr");

        //  首先验证 验证码（忽略大小写）
        if (validate.equalsIgnoreCase(codeStr)) {

            //  执行注册逻辑
            //  先查询用户名是否存在
            User user = userService.login(username);

            //  判断用户名是否存在
            if (user != null) {
                //  用户名已存在
                return new JsonResult(0, "此用户名已存在，请使用其它用户名！");
            } else {
                //  用户名不存在，进行注册
                int i = userService.signUp(username, password, telephoneNumber);

                if (i != 1) {
                    //  返回受影响行数，不为1，即注册失败
                    return new JsonResult(0, "注册失败！");
                }
            }
        } else {

            //  验证码错误，返回提示
            return new JsonResult(0, "验证码输入有误，请重新输入！");
        }

        return new JsonResult(1, "注册成功！");
    }

    /**
     *      登录方法    1.使用账号 + 密码 + 文字验证码登录
     *                 2.使用手机号码 + 短信验证码登录
     *
     * @Author  imlee
     *
     * @param username          用户名
     * @param password          密码
     * @param validate          验证码
     * @param telephoneNumber   手机号码
     * @param telephoneCode     短信验证码
     * @return                  供前台使用的 Json 数据
     */
    @RequestMapping("/signIn.do")
    public JsonResult signIn(String username, String password, String validate, String telephoneNumber, String telephoneCode) {

        //  验证码非空或者不是null，说明使用的是账号 + 密码 + 验证码
        //      反之，说明使用的是，手机号码 + 短信验证码

        User user = null;

        if (validate != null && !validate.equals("")) {

            //  获取 redis 中保存的验证码文字
            String codeStr = redisTemplate.opsForValue().get("codeStr");

            //  首先验证 验证码（忽略大小写）
            //      验证码正确，再验证，账号、密码
            if (validate.equalsIgnoreCase(codeStr)) {

                //  执行登录逻辑
                user = userService.login(username);

                //  判断用户名是否存在
                if (user == null) {
                    return new JsonResult(0, "此用户名不存在，请核对后重新登录！");
                } else {

                    //  比对用户输入的密码和数据库中是否一致
                    if (!password.equals(user.getPassword())) {
                        return new JsonResult(0, "密码错误，请核对密码或用户名是否输入有误！");
                    }
                }
            } else {

                //  验证码错误，返回提示
                return new JsonResult(0, "验证码输入有误，请重新输入！");
            }
        } else {

            //  按照 手机号码 + 短信验证码 的逻辑登录
            //      获取 保存在 redis 中的短信验证码
            String code = redisTemplate.opsForValue().get(telephoneNumber);

            //  判断短信验证码是否过期
            if (code == null || code.equals("")) {
                return new JsonResult(0, "验证码已过期，请重新获取！");
            }

            //  验证 用户输入的验证码和redis中保存的验证码是否一致
            if (telephoneCode.equals(code)) {

                //  验证码正确，执行登录逻辑
                user = userService.signInByTelephoneNumber(telephoneNumber);

                if (user == null) {
                    return new JsonResult(0, "此号码未注册账号，如需注册请移步注册页面(验证码有效期5分钟)！");
                }

            } else {
                //  验证码错误，返回提示
                return new JsonResult(0, "验证码输入有误，请重新输入！");
            }
        }

        //  登录成功，返回 user 对象
        return new JsonResult(1, user);
    }

}
