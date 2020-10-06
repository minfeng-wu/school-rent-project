package com.ascending.training.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "myFilter2", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
//名字可以改变顺序， 100 先 200 后
public class MyFilter200 implements Filter {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;


        //do something before pre execution
        logger.info("222222222222 pre action from MyFirstFilter, remoteHost={}", req.getRemoteHost());
        logger.info("222222222222 pre action from MyFirstFilter, RequestURI={}", req.getRequestURI());
        logger.info("222222222222 pre action from MyFirstFilter, Header={}", req.getHeader("testHeader"));

        //do something to keep the request flow going through chain
        filterChain.doFilter(req,resp);

        //do something after pst execution
        logger.info("222222222222 post action from MyFirstFilter, status={}", resp.getStatus());
        logger.info("222222222222 post action from MyFirstFilter, Headernames={}", resp.getHeaderNames());
    }

    @Override
    public void destroy() {

    }
}
