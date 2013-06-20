package packt;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.EJBAccessException;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class SecurityInterceptor {

	@Resource
	private SessionContext sessionContext;
	
	@AroundInvoke
	public Object verifyAccess(InvocationContext context) throws Exception{
		System.out.println("SecurityInterceptor: Invoking method: " + context.getMethod().getName());
		
		if (sessionContext.isCallerInRole("manager")) {
			Object result = context.proceed();
			System.out.println("SecurityInterceptor: Returned from method: " + context.getMethod().getName());
			return result;
		} else {
			throw new EJBAccessException();
		}
	}
}
