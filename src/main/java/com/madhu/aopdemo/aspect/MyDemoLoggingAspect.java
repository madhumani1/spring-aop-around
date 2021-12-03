/**
 * 
 */
package com.madhu.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.madhu.aopdemo.Account;
import com.madhu.aopdemo.AroundLoggerDemoApp;

/**
 * @author 15197
 * Create an Aspect with @Before advice
 */

@Aspect
@Component
@Order(1)
public class MyDemoLoggingAspect {
	// setup Spring logger
	private Logger myLogger = Logger.getLogger(AroundLoggerDemoApp.class.getName());
		
	@Around("execution(* com.madhu.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint )	throws Throwable	{
		// print out method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @Around on method: " + method);
		
		// get begin timestamp
		long begin = System.currentTimeMillis();
		
		// now execute the given method
		Object result = null;
		
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			// log the exception
			myLogger.warning(e.getMessage());
			
			// give users a custom messagee
			result = "Major accident! But no worries, "
					+ "your private AOP helicopter is on the way!";
		}
		
		// get end timestamp
		long end = System.currentTimeMillis();
		
		
		// compute duration and displaying it
		myLogger.info("=====> Time Taken: " + ( (end-begin)/1000.0) + " seconds");
		
		return result;
	}
	
	@AfterThrowing(pointcut="execution(* com.madhu.aopdemo.dao.AccountDAO.findAccounts(..))", throwing="e")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable e)	{
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @AfterThrowing on method: " + method);
		
		// log the exception
		myLogger.info("\n=====>>> The exception is: "+e);
	}
	
	@AfterReturning(pointcut="execution(* com.madhu.aopdemo.dao.AccountDAO.findAccounts(..))", returning="result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result)	{
		// printout which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @AfterReturning on method: " + method);
		
		// print out the results of the method call
		myLogger.info("\n=====>>> result is: " + result);
		
		// let's post-process the data ... let's modify it :-)
		// convert the account names to uppercase
		//convertAccountNamesToUpperCase(result);
		
		myLogger.info("\n=====>>> result is: " + result);
		
	}
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		// loop through accounts
		for (Account tempAccount : result) {
			// get uppercase version of name
			String theUpperName = tempAccount.getName().toUpperCase();
			
			// update the name on the account
			tempAccount.setName(theUpperName);
		}
	}
}
