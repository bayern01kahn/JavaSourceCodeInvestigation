package justin.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Operator {
	
	/*
	 * 通知执行的优先级
	   进入目标方法时,先织入Around,再织入Before，退出目标方法时，先织入Around,再织入AfterReturning,最后才织入After。
	   注意:Spring AOP的环绕通知会影响到AfterThrowing通知的运行,不要同时使用!同时使用也没啥意义。 
	 */
	
	//@Pointcut("execution(* justin.aop.annotation..*.*(..))")  //包下所有类和方法
	@Pointcut("execution(* justin.aop.annotation.AOPTargetClass.add*(..))")   //AOPTargetClass的add方法
    public void pointCut(){}
    
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("AOP Before Advice...");
    }
    
    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint){
    	AOPTargetClass atc = (AOPTargetClass)joinPoint.getTarget();
    	System.out.println(atc.getPara1());
        System.out.println("AOP After Advice...");
    }
    
    @AfterReturning(pointcut="pointCut()",returning="returnVal")
    public void afterReturn(JoinPoint joinPoint,Object returnVal){
        System.out.println("AOP AfterReturning Advice:" + returnVal);
    }
    
    @AfterThrowing(pointcut="pointCut()",throwing="error")
    public void afterThrowing(JoinPoint joinPoint,Throwable error){
        System.out.println("AOP AfterThrowing Advice..." + error);
        System.out.println("AfterThrowing...");
    }
    
    @Around("pointCut()")
    public void around(ProceedingJoinPoint pjp){
        System.out.println("AOP Aronud before...");
        try {
            pjp.proceed();  //做环绕通知的时候，调用ProceedingJoinPoint的proceed()方法才会执行目标方法。
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("AOP Aronud after...");
    }
    
}
