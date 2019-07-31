package com.github.web.filter;
/**
 * 过滤器验证登录状态
 * @autor lzy
 */
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/*")
public class loginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //0.强制转换
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //1.获取资源请求路径
        String uri = request.getRequestURI();
        //2.判断是否包含登录相关资源路径,要注意排除掉 css/js/图片 等资源
        if(uri.contains("/login-regist-writeText/css/") || uri.contains("/login-regist-writeText/img/") || uri.contains("/login-regist-writeText/js/") || uri.contains("/login-regist-writeText/enter.html") || uri.contains("/login-regist-writeText/login.html") || uri.contains("/registerservlet") || uri.contains("/signinservlet") ){
            //包含，用户就是想登录。放行
            chain.doFilter(req, resp);
        }else {
            //不包含，需要验证用户是否登录
            //3.从获取session中获取user
            Object user = request.getSession().getAttribute("status");
            //System.out.println(user);
            if (user != null) {
                //登录了。放行
                //System.out.println(user);
                chain.doFilter(req, resp);
            } else {
                //没有登录。跳转登录页面
                /**
                 * 重定向不能实现前端alert功能
                 * response.sendRedirect("/Music_forum/login-regist-writeText/enter.html");
                 * 内部跳转 --> 地址不变，因此不能使得filter放行全部登录注册界面
                 * request.getRequestDispatcher("/login-regist-writeText/enter.html").forward(request, resp);
                **/
                //设置字体
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out=response.getWriter();
                out.print("<script language='javascript'>alert('登录已失效请重新登陆');window.location.href='http://172.20.151.117:8066/Music_forum/login-regist-writeText/enter.html';</script>");

            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
