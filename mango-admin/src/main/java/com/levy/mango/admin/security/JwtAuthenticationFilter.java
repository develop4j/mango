package com.levy.mango.admin.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.levy.mango.admin.utils.SecurityUtils;

/**
 * 登录认证过滤器
 * @author levy
 * @date Mar 14, 2020
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

	
	@Autowired
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    	response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
    	response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE");
    	response.setHeader("Access-Control-Max-Age","3600");
    	response.setHeader("Access-Control-Allow-Headers","Origin,No-Cache,X-Requested-With,token");
    	response.setHeader("Access-Control-Allow-Credentials","true");
	    // 获取token, 并检查登录状态
        SecurityUtils.checkAuthentication(request);
        chain.doFilter(request, response);
    }
    
}