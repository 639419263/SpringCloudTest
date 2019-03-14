package com.chenhao.aop;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;
@Component
@Aspect
public class AopCommon {
    private static Logger logger= LoggerFactory.getLogger(AopCommon.class);

    /**
     * 前置通知:前置通知，方法调用前被调用
     * 注意：这里用到了JoinPoint和RequestContextHolder。
     * 1）、通过JoinPoint可以获得通知的签名信息，如目标方法名、目标方法参数信息等；
     * 2）、通过RequestContextHolder来获取请求信息，Session信息；
     * @param joinPoint
     */
    @Before("execute()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("我是前置通知");
        Object[] obj=joinPoint.getArgs();//获取目标方法的参数信息
        joinPoint.getThis(); // AOP代理类信息
        joinPoint.getTarget(); // 代理的目标对象
        Signature signature=joinPoint.getSignature(); //  用的最多，通知的签名
        logger.info("代理的方法是 ： "+signature.getName()); //  打印 代理的是哪一个方法
        // AOP 代理的名字
        logger.info("AOP 代理的名字 ： "+signature.getDeclaringTypeName());
        signature.getDeclaringType();//  AOP代理类的类（class）信息
        /*
          通过RequestContextHolder获取请求信息，如session 信息 ;
         */
        //  获取RequestAttributes
        RequestAttributes requestAttributes= RequestContextHolder.getRequestAttributes();
        //  从requestAttributes中获取HttpServletRequest信息
        HttpServletRequest request=(HttpServletRequest)requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //  获取session信息
        HttpSession session=(HttpSession)requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        logger.info("请求地址:"+request.getRequestURI()+"    方法:"+request.getMethod());
        Enumeration<String> enumerations=request.getParameterNames();
//        Map<String,String> parameterMaps=new HashMap<>();
        Map<String,String> parameterMaps= Maps.newHashMap();
        while(enumerations.hasMoreElements()){
            String parameter=enumerations.nextElement();
            parameterMaps.put(parameter,request.getParameter(parameter));
        }
        String str= JSON.toJSONString(parameterMaps);//   alibaba.fastjson
        if(obj.length>0){
            logger.info("请求参数信息为 ： "+ str );
        }
    }

    /**
     * 切点
     */
    @Pointcut("execution(public * com.chenhao.controller.*.*(..))")
    public void execute(){
        logger.info("--正在处理请求-------");
    }

    /**
     * 后置最终通知（目标方法只要执行完了就会执行后置通知方法）
     */
    @After("execute()")
    public void doAfter(JoinPoint joinPoint){
        logger.info("------doAfter()----");
    }
    /**
     * 环绕通知：
     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around("execute()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        logger.info("环绕通知的目标方法名："+proceedingJoinPoint.getSignature().getName());
        try {
            Object obj = proceedingJoinPoint.proceed();
            return obj;
        } catch (Throwable throwable) {
            logger.error("方法执行出现了异常",throwable.getMessage());
            throwable.printStackTrace();
        }
        return null;
    }

    /**
     * 后置返回通知
     * 需要注意：
     *  4      *      如果第一个参数是JoinPoint，则第二个参数是返回值的信息
     *  5      *      如果参数中的第一个不是JoinPoint，则第一个参数是returning中对应的参数，
     *  6      *    returning 限定了只有目标方法返回值与通知方法相应参数类型时才能
     *  7      * 执行后置返回通知，否则不执行;
     *  8      * 对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     * @param result
     */
    @AfterReturning(returning = "result",pointcut = "execute()")
    public void afterReturn(JoinPoint point,Object result){
        logger.info("后置返回通知：result---"+JSON.toJSONString(result));
        Object[] array=point.getArgs();
        logger.info("后置返回通知--   "+JSON.toJSONString(array));
    }
    /**
     * 后置异常通知
     *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     *  throwing:限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     *           对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = "execute()",throwing = "exception")
    public void afterThrow(JoinPoint joinPoint,Throwable exception){
        logger.info("后置异常通知---方法名::"+joinPoint.getSignature().getName());
         if(exception instanceof  NullPointerException){
             logger.warn("发生了空指针异常",exception);
         }else if(exception instanceof NumberFormatException){
             logger.warn("类型转换异常",exception);
         }else if(exception instanceof  Exception){
             logger.warn("其他类型异常",exception);
         }
    }


}
