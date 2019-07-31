package com.github.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class FilterAll implements Filter {
    private FilterConfig config;
    private String encoding = "ISO8859_1";
    @Override
    public void destroy() {
        config = null;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(encoding);
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        String s = config.getInitParameter("encoding");
        if (s != null) {
            encoding = s;
        }
    }

}
