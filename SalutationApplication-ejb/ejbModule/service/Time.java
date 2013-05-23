package service;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import ejb.TimeOfDay;

@WebService
public class Time {

	@EJB
	private TimeOfDay timeOfDay;
	
	@WebMethod(operationName = "timeOfDay")
	public String timeOfDay(){
		return timeOfDay.timeOfDay();
	}
}
