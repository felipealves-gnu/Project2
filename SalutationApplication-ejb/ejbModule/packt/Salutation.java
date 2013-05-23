package packt;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless(mappedName = "salutationBean")
public class Salutation {
	
	@Resource
	private SessionContext context;
	
  	public String getFormalSalutation(String name){
		return "Dear " + name;
	}
	
	public String  getInformalSalutation(String name) {
		return "Hi " + name;
	}
	
	public String getContextInformation(){
		StringBuilder contextInformation = new StringBuilder();
		contextInformation.append(context.toString() + "<br/>");
		
		try {
			contextInformation.append(context.getInvokedBusinessInterface().toString());
		} catch (IllegalStateException e) {
			contextInformation.append(e);
		}
		return contextInformation.toString();		
	}
}
