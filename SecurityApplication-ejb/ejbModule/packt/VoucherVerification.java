package packt;

import java.security.Principal;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
@DeclareRoles("manager")
//@RunAs("manager")
public class VoucherVerification {

	@Resource
	private SessionContext sessionContext;

	public void submit(){
		Principal principal = sessionContext.getCallerPrincipal();
		System.out.println("Principal: " + principal.getName());
		//Perform verification checks
	}
}
