package ryu.assign.mvc.aop;

import java.util.Base64;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import ryu.assign.mvc.model.FreePost;

@Aspect
@Component
public class DecodeAop {
	
    @Pointcut("execution(* ryu.assign.mvc.controller..*.*(..))")
    private void cut() {}


    @Before("cut()")
    public void before(JoinPoint joinPoint) throws Throwable {
        
        //메서드로 들어오는 매개변수들
        Object[] args = joinPoint.getArgs();
        
        String publisher = "";
        String contents = "";
        
        for(Object arg : args) {
            if(arg instanceof FreePost)  {
            	//클래스 캐스팅
            	FreePost freePost = FreePost.class.cast(arg);
                publisher = freePost.getPublisher();
                contents = freePost.getContents();
                
                System.out.println("디코딩 전 : " + publisher);
                
                String base64publisher =  new String(Base64.getDecoder().decode(publisher),"UTF-8");
                freePost.setPublisher(base64publisher);
                
                System.out.println("디코딩 후 : " + base64publisher);
                
                if(base64publisher.contains("ryu")) {
                	System.out.println("ryu가 게시물을 작성함");
                }
                

                
                if(contents.contains("badWord")) {
                	freePost.setContents("!@#$");
                	System.out.println("필터링됨");
                }
                
            }
        }
        
        
    }
	

    
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
    	
        if(returnObj instanceof FreePost ){
        	FreePost user = FreePost.class.cast(returnObj);
 
            String publisher = user.getPublisher();
            System.out.println("인코딩 전 : " + publisher);
            String  base64email =  Base64.getEncoder().encodeToString(publisher.getBytes());
            	
            user.setPublisher(base64email);  //user 객체 디코드
            System.out.println("인코딩 후 : " + base64email);
            System.out.println("");
        }

    }
    
    
    /*
    @Around("cut() && enableDecode()")
    public void around(ProceedingJoinPoint joinPoint) {
    	
    	//joinPoint.ABCD
    	//.....
    	
    	
    	//joinPoint.proceed();
    	
    	
    	//............
    }
    */
}