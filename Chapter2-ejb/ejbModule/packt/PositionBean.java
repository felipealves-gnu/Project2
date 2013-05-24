package packt;

import javax.ejb.Remote;
import javax.ejb.Startup;
import javax.ejb.Stateless;

@Stateless
@Remote
@Startup
public class PositionBean implements PositionBeanRemote {

	private double eccentricity;
	private double semimajorAxis;
	private double inclination;
	private double longitudeOfTheAscendingNode;
	private double argumentOfPeriapsis;
	private double meanAnomaly;

	//@PostConstruct
	public PositionBean() {
		eccentricity = 1.0;
	}
	
	
	@Override
	public double getArgumentOfPeriapsis() {
		return 0;
	}

	@Override
	public double getEccentricity() {
		System.out.println("--- Return eccentricity");
		return 0;
	}

	@Override
	public double getInclination() {
		return 0;
	}

	@Override
	public double getLongitudeOfTheAscendingNode() {
		return 0;
	}

	@Override
	public double getMeanAnomaly() {
		return 0;
	}

	@Override
	public double getSemimajorAxis() {
		return 0;
	}

}
