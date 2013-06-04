package packt;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

@Stateless
public class SphereBean {
	private String unit;
	
	@PostConstruct 
	private void initialize(){
		unit = "meters";
		System.out.println("SphereBean initialized");
	}
	
	@PreDestroy
	private void destroy(){
		System.out.println("Clean up SphereBean");
	}

	public double computeVolume(double radius) {
		return (4.0 / 3.0) * Math.PI * (radius * radius * radius);
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
}
