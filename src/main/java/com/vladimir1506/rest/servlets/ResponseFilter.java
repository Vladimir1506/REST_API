package com.vladimir1506.rest.servlets;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.servlet.*;
import java.io.IOException;

public class ResponseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
