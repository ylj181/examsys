package com.qfedu.examsys.aftercontroller;

import cn.dsna.util.images.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author imlee
 * @Date 2019-09-01 09:54
 *
 *  4位随机文字的图片验证码
 *  保存验证码到 redis 中
 */

@Controller
@WebServlet("/ValidateImg")
public class ValidateImgController extends HttpServlet {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  验证码 宽：80 高：30 位数：4位 干扰线：20条
        ValidateCode code = new ValidateCode(80, 30, 4, 20);

        //  获取生成的随机文字
        String codeStr = code.getCode();

        //	将验证码保存到redis中
        //		key：	codeStr
        //		value：	验证码生成的随机文字
        redisTemplate.opsForValue().set("codeStr", codeStr);

        //  将生成的验证码以图片形式,以流的方式,输出到前台页面
        code.write(resp.getOutputStream());
    }
}
