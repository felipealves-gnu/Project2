package packt;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class DefaultInterceptor {
	@AroundInvoke
	public Object defaultMethod(InvocationContext context) throws Exception{
		System.out.println("Default Interceptor: Invoking target class: " + context.getTarget() + " Invoking method: " + context.getMethod().getName());
		Object result = context.proceed();
		System.out.println("Default Interceptor: Invoking target class: " + context.getTarget() + " Invoking method: " + context.getMethod().getName());
		return result;
	}
}
