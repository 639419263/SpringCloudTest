package com.chenhao.exception;

import org.jboss.logging.Logger;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *  全局异常统一处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger log= Logger.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Object defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
         log.info("----defaultErrorHandler----",e);
         e.printStackTrace();
         return "服务异常，请稍后重试...";
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object defaultErrorHandler2(HttpServletRequest request, Exception e) throws Exception {
        log.info("----defaultErrorHandler2----",e);
        e.printStackTrace();
        return "请检查此异常是否能正常捕获...";
    }

    /**
     *  请求异常定义统一的前端页面
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer webServerFactoryCustomizer(){
       return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>() {
          @Override
          public void customize(ConfigurableServletWebServerFactory factory) {
              // 对嵌入式servlet容器的配置
              // factory.setPort(8081);
                /* 注意：new ErrorPage(stat, path);中path必须是页面名称，并且必须“/”开始。
                    底层调用了String.java中如下方法：
                    public boolean startsWith(String prefix) {
                        return startsWith(prefix, 0);
                    }*/
              ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST,"/error-400");
              ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND,"/error-404");
              ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error-500");
              factory.addErrorPages(errorPage400, errorPage404,errorPage500);
               }
           };
   }


}
