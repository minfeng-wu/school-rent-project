package com.ascending.training.filter;

import com.ascending.training.service.JWTService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class SecurityFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JWTService jwtService;

    private static String AUTH_URI = "/auth";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;

        int statusCode = authorization(req);
        if(statusCode == HttpServletResponse.SC_ACCEPTED)
            filterChain.doFilter(servletRequest, servletResponse);
        else
            resp.sendError(statusCode);
    }

    private int authorization(HttpServletRequest req) {
        int statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        String uri = req.getRequestURI();
        logger.info("###########, urs={}", uri);
        if(uri.equalsIgnoreCase(AUTH_URI))
            statusCode = HttpServletResponse.SC_ACCEPTED;

        String httpMethodValue = req.getMethod(); //get post put patch delete ...
        try{
            String wholeTokenString = req.getHeader("Authorization");
            String token = wholeTokenString.replaceAll("^(.*?) ", "");
            if(token == null || token.trim().isEmpty())
                return statusCode;
            Claims claims = jwtService.decryptJwtToken(token);
            String allowedResources = "";
            switch(httpMethodValue) {
                case "GET"    :
                    allowedResources = (String)claims.get("allowedReadResources");
                    break;
                case "POST"    :
                    allowedResources = (String)claims.get("allowedCreateResources");
                    break;
                case "PUT"    :
                    allowedResources = (String)claims.get("allowedUpdateResources");
                    break;
                case "PATCH"    :
                    allowedResources = (String)claims.get("allowedUpdateResources");
                    break;
                case "DELETE"    :
                    allowedResources = (String)claims.get("allowedDeleteResources");
                    break;
            }

            String[] allowedResourceArray = allowedResources.split(",");
            for(String eachAllowedResourceString : allowedResourceArray){
                if(uri.trim().toLowerCase().startsWith(eachAllowedResourceString.trim().toLowerCase()));
                statusCode = HttpServletResponse.SC_ACCEPTED;
                break;
            }
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return statusCode;
    }

    @Override
    public void destroy() {

    }
}
