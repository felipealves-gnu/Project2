package packt;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
@DeclareRoles({"employee", "manager"})
@TransactionManagement(TransactionManagementType.BEAN)
public class RegistrationManager {
	@EJB
	AttendeeFacade attendeeFacade;
	Attendee attendee;
	
	@PersistenceContext(unitName = "RegistrationApplication-ejbPU")
	private EntityManager entityManager;
	
	//@ExcludeClassInterceptors
	//@Interceptors({MethodInterceptor.class})
	//@Interceptors(ValidationInterceptor.class)
	@Interceptors(SecurityInterceptor.class)
	public Attendee register(String name, String title, String company) {
		System.out.println("register");
		attendee = new Attendee(name, title, company);
		attendeeFacade.create(attendee);
		return attendee;
	} 
	
	@Interceptors({TransactionInterceptor.class})
	public void bulkRegister(String names[], String titles[], String company) {
		for(int i=0; i<names.length; i++) {
			attendeeFacade.create(new Attendee(names[i], titles[i],company));
		}
	}
	
	@AroundInvoke
	public Object internalMethod(InvocationContext context) throws Exception{
		System.out.println("internalMethod: Invoking method: " + context.getMethod().getName());
		Object result = context.proceed();
		System.out.println("internalMethod: Returned from method: " + context.getMethod().getName());
		return result;
	}
}
