package com.stone.ripple.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.stone.ripple.dal.pojo.music.SongDoExample;
import com.stone.ripple.mvc.controller.home.MusicHomeController;

//@Aspect
//@Component
public class EventAspect {
    
    private static Logger logger = Logger.getLogger(EventAspect.class);

    private final String execution1 = "execution(* com.stone.lava.dao.*.*(..))";
    private final String execution2 = "execution(* com.stone.ripple.dao.music.SongDoMapper.getSongList2(com.stone.ripple.dal.pojo.music.SongDoExample) )";

    @Pointcut(execution1 + "||" + execution2)
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        logger.info("@Around：开始执行前...");
        // 访问目标方法的参数：
        Object[] args = point.getArgs();
        // 用改变后的参数执行目标方法
        Object returnValue = point.proceed(args);
        logger.info("@Around：目标方法执行完成后...");
        logger.info("@Around：被织入的目标对象为：" + point.getTarget());
        return returnValue;
    }

    @Before("pointCut()")
    public void check(JoinPoint point) throws ClassNotFoundException {
        try{
            logger.info("@Before：方法执行前检查...");
            Signature signature = point.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            String methodName = method.getDeclaringClass().getName() + "." + method.getName();
            logger.info("@Before：切入目标方法为：" + point.getSignature().getDeclaringTypeName() + "."
                               + point.getSignature().getName());

            if (1 == 1) {
                throw new RuntimeException("123");
            }
            logger.info(method.getParameterTypes()[0].getName());
            if ("com.stone.ripple.dao.music.SongDoMapper.getSongList2".equals(methodName)) {
                logger.info("===============");
            } else {
                return;
            }

            logger.info("com.stone.ripple.dal.".equals(method.getParameterTypes()[0].getName()));

            if ("com.stone.ripple.dal.pojo.music.SongDoExample".equals(point.getArgs()[0])) {
                logger.info("===============");
            }
            logger.info("@Before：参数为：" + Arrays.toString(point.getArgs()));
            logger.info("@Before：被织入的目标对象为：" + point.getTarget());
        }catch(Exception e){
            logger.info("================---");
        }
    }

    @AfterReturning(pointcut = "pointCut()", returning = "returnValue")
    public void returning(JoinPoint point, Object returnValue) {
        logger.info("@AfterReturning：模拟日志记录功能...");
        logger.info("@AfterReturning：目标方法为：" + point.getSignature().getDeclaringTypeName() + "."
                           + point.getSignature().getName());
        logger.info("@AfterReturning：参数为：" + Arrays.toString(point.getArgs()));
        logger.info("@AfterReturning：返回值为：" + returnValue);
        logger.info("@AfterReturning：被织入的目标对象为：" + point.getTarget());
    }

    @After("pointCut()")
    public void releaseResource(JoinPoint point) {
        logger.info("@After：模拟释放资源...");
        logger.info("@After：目标方法为：" + point.getSignature().getDeclaringTypeName() + "."
                           + point.getSignature().getName());
        logger.info("@After：参数为：" + Arrays.toString(point.getArgs()));
        logger.info("@After：被织入的目标对象为：" + point.getTarget());
    }
    
    @After("pointCut()")
    public void releaseResource2(JoinPoint point) {
        logger.info("@After2：模拟释放资源...");
        logger.info("@After2：目标方法为：" + point.getSignature().getDeclaringTypeName() + "."
                           + point.getSignature().getName());
        logger.info("@After2：参数为：" + Arrays.toString(point.getArgs()));
        logger.info("@After2：被织入的目标对象为：" + point.getTarget());
    }

    public static void main(String[] args) throws ClassNotFoundException {
        logger.info(Class.forName("com.stone.lava.pojo.LavaDoExample").isAssignableFrom(Class.forName("com.stone.lava.pojo.LavaDoExample")));
        logger.info(Class.forName("com.stone.lava.pojo.LavaDoExample").isAssignableFrom(Class.forName("com.stone.ripple.dal.pojo.music.SongDoExample")));
    }
}
