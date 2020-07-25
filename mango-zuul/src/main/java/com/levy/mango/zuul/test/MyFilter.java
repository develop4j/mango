package com.levy.mango.zuul.test;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component // 把普通的pojo实例化到spring容器中
public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre"; // 定义filter类型 有pre route post error
    }

    @Override
    public int filterOrder() {
        return 0; // 定义filter的顺序，越小 顺序越高，越先执行
    }

    @Override
    public boolean shouldFilter() {
        return true; // 是否需要执行该filter ，true表示执行
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getParameter("token");
        if(token == null){
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            try{
                requestContext.getResponse().getWriter().write("there is no request token");
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
        return null;
    }
}
