package packt;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@DependsOn("PlayerBean") //there is only in Singleton components
public class GameBean {
	private String state;
	
	@PostConstruct
	private void initialize(){
		state = "Initializing";
		System.out.println("GameBean initialized");
	}
	
	public String getState(){
		return state;
	}
	
	public void setState(String state){
		this.state = state;
	}
}
