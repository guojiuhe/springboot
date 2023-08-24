package guojiuhe.demo.aop;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class AnnotationAop {
	
	Log log = LogFactory.get(AnnotationAop.class);
	
	//定义切点，注解作为切入点
    @Pointcut("@annotation(guojiuhe.demo.aop.ViewRecords)")
    public void viewRecordsPoinCut() {
    	
    }
    
    /**
     * before 目标方法执行前执行，前置通知
     * after 目标方法执行后执行，后置通知
     * after returning 目标方法返回时执行 ，后置返回通知
     * after throwing 目标方法抛出异常时执行 异常通知
     * around 在目标函数执行中执行，可控制目标函数是否执行，环绕通知
     */
    @Before("viewRecordsPoinCut()")
    public void before(JoinPoint joinPoint) throws Throwable {
        log.info("进入Before通知....");
    }
 
    //@After("viewRecordsPoinCut()") // 注解形式1
    @After("@annotation(guojiuhe.demo.aop.ViewRecords)") // 注解形式2
    //@After("execution(* guojiuhe.demo.aop.*.*(..))") // 注解形式2

    public void after(JoinPoint joinPoint) throws Throwable {
        log.info("进入After通知....");
    }
 
    @AfterReturning("viewRecordsPoinCut()")
    public void afterReturning(JoinPoint joinPoint) throws Throwable {
        log.info("进入AfterReturning通知....");
    }
 
    @AfterThrowing("viewRecordsPoinCut()")
    public void afterThrowing(JoinPoint joinPoint) throws Throwable {
        log.info("进入AfterThrowing通知....");
    }
 
 
 
    @Around("viewRecordsPoinCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("进入Around通知....前");
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
 
        //获取注解信息
        ViewRecords viewRecords = method.getAnnotation(ViewRecords.class);
        Long idValue = viewRecords.id();
        log.info("idValue的值为：{}",idValue);
        Long id = null;
 
        //获取切入点方法参数
        Object[] objects = joinPoint.getArgs();
        String[] paramNames = signature.getParameterNames();
        for (int i =0;i<paramNames.length;i++){
            /*
            if (Objects.equals("id",paramNames[i]) && objects[i] != null){
                id = Long.valueOf(objects[i].toString());
            }
            */
            log.info("objects " + (i + 1) + objects[i]);
            log.info("paramNames " + (i + 1) + paramNames[i]);
        }
        log.info("id的值为：{}",id);
        /**
         * 进行业务操作
         */
        // 执行目标方法
        Object r  = joinPoint.proceed();
        /**
         * 目标方法执行完毕之后，执行的业务增强操作
         */
        log.info("进入Around通知....后");
        return r;
    }
}
