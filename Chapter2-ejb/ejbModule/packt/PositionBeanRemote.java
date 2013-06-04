package packt;

import javax.ejb.Remote;

@Remote
public interface PositionBeanRemote {
	public double getArgumentOfPeriapsis();
	public double getEccentricity();
	public double getInclination();
	public double getLongitudeOfTheAscendingNode();
	public double getMeanAnomaly();
	public double getSemimajorAxis();
}
