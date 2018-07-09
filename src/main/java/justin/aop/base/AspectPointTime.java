package justin.aop.base;

import java.util.Date;

import justin.util.DateUtil;

public class AspectPointTime {

	public void printTime(){
		System.out.println("切入点打印时间： "+ DateUtil.formatLongDate(new Date()));
	}
	
	public void printChinaTime(){
		System.out.println("切入点打印时间： "+ DateUtil.formatChinaWeekDate(new Date()));
	}
}
