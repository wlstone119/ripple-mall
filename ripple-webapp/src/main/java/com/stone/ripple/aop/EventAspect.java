package com.stone.ripple.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
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

@Aspect
@Component
public class EventAspect {

    private final String execution1 = "execution(* com.stone.lava.dao.*.*(..))";
    private final String execution2 = "execution(* com.stone.ripple.dao.music.SongDoMapper.getSongList2(com.stone.ripple.dal.pojo.music.SongDoExample) )";

    @Pointcut(execution1 + "||" + execution2)
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        System.out.println("@Around：开始执行前...");
        // 访问目标方法的参数：
        Object[] args = point.getArgs();
        // 用改变后的参数执行目标方法
        Object returnValue = point.proceed(args);
        System.out.println("@Around：目标方法执行完成后...");
        System.out.println("@Around：被织入的目标对象为：" + point.getTarget());
        return returnValue;
    }

    @Before("pointCut()")
    public void check(JoinPoint point) throws ClassNotFoundException {
        try{
            System.out.println("@Before：方法执行前检查...");
            Signature signature = point.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            String methodName = method.getDeclaringClass().getName() + "." + method.getName();
            System.out.println("@Before：切入目标方法为：" + point.getSignature().getDeclaringTypeName() + "."
                               + point.getSignature().getName());

            if (1 == 1) {
                throw new RuntimeException("123");
            }
            System.out.println(method.getParameterTypes()[0].getTypeName());
            if ("com.stone.ripple.dao.music.SongDoMapper.getSongList2".equals(methodName)) {
                System.out.println("===============");
            } else {
                return;
            }

            System.out.println("com.stone.ripple.dal.".equals(method.getParameterTypes()[0].getTypeName()));

            if ("com.stone.ripple.dal.pojo.music.SongDoExample".equals(point.getArgs()[0])) {
                System.out.println("===============");
            }
            System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
            System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
        }catch(Exception e){
            System.out.println("================---");
        }
    }

    @AfterReturning(pointcut = "pointCut()", returning = "returnValue")
    public void returning(JoinPoint point, Object returnValue) {
        System.out.println("@AfterReturning：模拟日志记录功能...");
        System.out.println("@AfterReturning：目标方法为：" + point.getSignature().getDeclaringTypeName() + "."
                           + point.getSignature().getName());
        System.out.println("@AfterReturning：参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@AfterReturning：返回值为：" + returnValue);
        System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
    }

    @After("pointCut()")
    public void releaseResource(JoinPoint point) {
        System.out.println("@After：模拟释放资源...");
        System.out.println("@After：目标方法为：" + point.getSignature().getDeclaringTypeName() + "."
                           + point.getSignature().getName());
        System.out.println("@After：参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@After：被织入的目标对象为：" + point.getTarget());
    }

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(Class.forName("com.stone.lava.pojo.LavaDoExample").isAssignableFrom(Class.forName("com.stone.lava.pojo.LavaDoExample")));
        System.out.println(Class.forName("com.stone.lava.pojo.LavaDoExample").isAssignableFrom(Class.forName("com.stone.ripple.dal.pojo.music.SongDoExample")));
    }
}
