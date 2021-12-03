/**
 * 
 */
package com.madhu.aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

/**
 * @author 15197
 *
 */
@Component
public class TrafficFortuneService {
	public String getFortune()	{
		// simulate a delay
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// return a fortune
		return "Expect heave traffic this morning";
	}
	
	public String getFortuneException()	{
		String message=null;
		
		try	{
			message.concat("Major Accident! Highway is closed");
		}	catch(Exception e)	{
			throw new RuntimeException("traffic disrupted due to runtime exception");
		}
		return message;
	}
}
