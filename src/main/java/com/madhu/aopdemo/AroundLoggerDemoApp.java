/**
 * 
 */
package com.madhu.aopdemo;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.madhu.aopdemo.dao.AccountDAO;
import com.madhu.aopdemo.dao.MembershipDAO;
import com.madhu.aopdemo.service.TrafficFortuneService;

/**
 * @author 15197
 * Create main app
 */
public class AroundLoggerDemoApp {
	// setup Spring logger
	private static Logger myLogger = Logger.getLogger(AroundLoggerDemoApp.class.getName());
	public static void main(String[] args)	{
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		//AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService",TrafficFortuneService.class);
		
		myLogger.info("Main program: AroundDemoApp");
		
		myLogger.info("Calling getFortune");
		
		String data = theFortuneService.getFortune();
		//String data = theFortuneService.getFortuneException();
		
		myLogger.info("\nMy fortune is: "+data);
		
		myLogger.info("Finished");
		
		//close context
		context.close();
	}
}
