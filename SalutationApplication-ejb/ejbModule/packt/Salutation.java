package packt;

import javax.ejb.Stateless;

@Stateless(mappedName = "salutationBean")
public class Salutation {
  	public String getFormalSalutation(String name){
		return "Dear " + name;
	}
	
	public String  getInformalSalutation(String name) {
		return "Hi " + name;
	}
}
