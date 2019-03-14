package com.chenhao.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 继承ZuulFilter   实现对所有服务请求的统一规则：
 * 这里校验一下是否都带了token，相当于拦截器一样
 */
@Component
public class ZuulFilterTest extends ZuulFilter {
    private static Logger logger= Logger.getLogger(ZuulFilterTest.class);

    @Override
    public String filterType() {
        logger.info("---filterType------");
        return "pre";
    }

    @Override
    public int filterOrder() {
        logger.info("---filterOrder------");
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        logger.info("---shouldFilter------");
        return true;
    }

    @Override
    public Object run() {
        logger.info("---run------");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String path=request.getRequestURI();
        logger.info("contextPath::"+path);
        //检查一下后缀可以不用添加token，只针对后台请求需要加
        String[] array={"jpg","JPG","png","JPEG","html","js","jsp"};
        boolean flag=false;
        for(String ss:array){
            if(path.indexOf(ss)!=-1){
                flag=true;
                break;
            }
        }
        if(flag){
            logger.info("当前是前端资源访问，无需校验token，直接忽略...");
            return null;
        }else{
            if(path.indexOf(".do")==-1){
                logger.info("不带.do的请求属于测试，非业务接口不做过滤校验...");
                return null;
            }
        }

        String token=request.getParameter("token");
        logger.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        logger.info("token::"+token);
        String msg=null;
        if(token==null || "".equals(token)){
            logger.warn("token check error：token is null");
            msg="token 校验失败：token为空";
        }else{
            if(token.length()<=5 || token.length() >100){
                logger.warn("token check error：length is in [5,100]");
                msg="token check error：length is in [5,100]";
                return null;
            }
        }
        if(msg !=null){
            logger.info("这里说明校验失败了,请求被拦截!");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write(msg);
            }catch (Exception e){
                logger.error("拦截处理异常",e);
            }
            return null;
        }
        return null;
    }
}
