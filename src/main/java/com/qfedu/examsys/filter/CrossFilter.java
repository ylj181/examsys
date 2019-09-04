package com.qfedu.examsys.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*@WebFilter(urlPatterns = "/*")*/
public class CrossFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        // token name 是程序中自定义的请求头，需要在Access-Control-Allow-Headers 进行设置
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Authorization,Accept,Content-Type,token,name");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST,DELETE,PUT");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
