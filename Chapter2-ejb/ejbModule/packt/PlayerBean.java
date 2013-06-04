package packt;

import javax.ejb.Singleton;

@Singleton
public class PlayerBean {

	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
