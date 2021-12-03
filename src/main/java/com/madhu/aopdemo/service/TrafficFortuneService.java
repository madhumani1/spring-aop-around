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
	
	// has to be getFortune as method name. any other name and it will not work
		public String getFortune(String message)	{
			//String message=null;
			
			if (message!=null) {
				throw new RuntimeException("Major accident! Highway is closed!");
			}
			
			return getFortune();
			
		}
		
		public String getFortune(boolean tripWire) {

			if (tripWire) {
				throw new RuntimeException("Major accident! Highway is closed!");
			}
			
			return getFortune();
		}
}
