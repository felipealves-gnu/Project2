package packt;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.interceptor.AroundTimeout;
import javax.interceptor.InvocationContext;


@Stateless
public class SystemReportManager {

	@Resource
	TimerService timerService;
	long duration = 5000;
	
    public SystemReportManager() {
    }

    public String getMemoryReport(){
    	StringBuilder report 			 = new StringBuilder();
    	GregorianCalendar reportCalendar = new GregorianCalendar();
    	Date reportDate 				 = reportCalendar.getTime();
    	Runtime runtime 				 = Runtime.getRuntime();
    	DateFormat dateFormat 			 = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
    	
    	report.append("\n").append(dateFormat.format(reportDate));
    	report.append("\nTotal Memory: ").append(runtime.totalMemory());
    	report.append("\n");
    	report.append("Maximum Memory: ").append(runtime.maxMemory());
    	report.append("\n");
    	report.append("Free Memory: ").append(runtime.freeMemory());
    	report.append("\n");
    	return report.toString();
    }
    
    //@Schedule(second = "*", minute="*", hour = "*", persistent=false)
    //@Schedule(second = "0,10,20,30,40,50", minute="*", hour = "*", persistent=false)
    /*@Schedules({
    		 @Schedule(second = "0", minute="*", hour = "*", persistent=false),
    		 @Schedule(second = "20, 30", minute="*", hour = "*", persistent=false)})*/
    @Schedule(second = "0", minute="*", hour = "*", persistent=false)
    public void displayMemoryReport(Timer timer){
    	System.out.println("SystemReportManager: displayMemoryReport occured");
    	System.out.println(getMemoryReport());
    	System.out.println(getTimerData(timer));
    }
    
//    @Schedule(second = "0", minute = "*", hour="*" )
    public void clearStatistics(Timer timer){
    	System.out.println("clearStatistics executed");
    }
    
    public void createTimer(){
    	GregorianCalendar reportCalendar = new GregorianCalendar(2013, Calendar.JUNE, 26, 15, 41);
    	Date reportDate = reportCalendar.getTime();
    	ArrayList<String> list = new ArrayList<String>();
    	list.add("capitalize");
    	list.add("center");
    	list.add("arial");
    	timerService.createTimer(reportDate, list);
        //timerService.createSingleActionTimer(reportDate, new TimerConfig());
    	//timerService.createSingleActionTimer(duration, new TimerConfig());    //The first argument is either a Date object or a long value	
    }
    
    public void createCalendarTimer(){
//    	ScheduleExpression scheduleExpression = new ScheduleExpression();
//    	scheduleExpression.year(2013);
//    	scheduleExpression.month(6);
//    	scheduleExpression.dayOfMonth(25);
//    	scheduleExpression.hour(12);
//    	scheduleExpression.minute(8);
//    	scheduleExpression.second(3);
//    	timerService.createCalendarTimer(scheduleExpression, new TimerConfig());
    	ScheduleExpression scheduleExpression = new ScheduleExpression();
    	scheduleExpression.year(2012);
    	scheduleExpression.month(1);
    	scheduleExpression.dayOfMonth(1);
    	timerService.createCalendarTimer(scheduleExpression, new TimerConfig());
    	
    	scheduleExpression = new ScheduleExpression();
    	scheduleExpression.year(2013);
    	scheduleExpression.month(1);
    	scheduleExpression.dayOfMonth(1);
    	timerService.createCalendarTimer(scheduleExpression, new TimerConfig());
    	Collection<Timer> timers = timerService.getTimers();
    	
    	for(Timer timer : timers) {
    		System.out.println(timer.getSchedule());
    	}
    }
    
    public String getTimerData(Timer timer){
    	StringBuilder timerData = new StringBuilder();
    	timerData.append("\nInfo: ").append(timer.getInfo());
    	timerData.append("\nNext timeout: ").append(timer.getNextTimeout());
    	timerData.append("\nSchedule: ").append(timer.getSchedule());
    	timerData.append("\nTime remaining:	").append(timer.getTimeRemaining());
    	timerData.append("\nCalendar timer: ").append(timer.isCalendarTimer());
    	timerData.append("\nPersistent: ").append(timer.isPersistent());
    	
    	return timerData.toString();
    }
    
//    @Timeout
    public void timeout(Timer timer){
    	ArrayList<String> list = (ArrayList<String>) timer.getInfo();
    	System.out.println("List Elements");
    	for (String element : list) {
			System.out.println(element);
		}
    	System.out.println("timeout: timeout occured");
    	System.out.println("getMemoryReport: " + getMemoryReport());
    }
    
    @AroundTimeout
    public Object interceptorTimeout(InvocationContext invocationContext) throws Exception {
    	System.out.println("interceptTimeout executing");
    	Timer timer = (Timer) invocationContext.getTimer();
    	System.out.println("Timer: " + timer.getSchedule());
    	Object object = invocationContext.proceed();
    	System.out.println("interceptTimeout returning");
    	
    	return object;
    }
}
