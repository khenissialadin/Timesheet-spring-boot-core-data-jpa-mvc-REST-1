package tn.esprit.spring.configuration.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

	@Before("execution(* tn.esprit.spring.services.*.*(..))")
	public void logMethodEntry(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		log.info("In service method " + " : " + name);
	}

	@After("execution(* tn.esprit.spring.services.*.*(..))")
	public void logMethodOut(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		log.info("Out service method " + " : " + name);
	}

	@Around("execution(* tn.esprit.spring.services.*.*(..))")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object obj = null;
		try {
			obj = pjp.proceed();
		} catch (Exception e) {
			log.error(e.getMessage());
			return obj;
		}
		finally {
			long elapsedTime = System.currentTimeMillis() - start;
			log.info("Method " + pjp.getSignature().getName() + " execution time: " + elapsedTime + " milliseconds.");
		}
		return obj;
		
	}

}
