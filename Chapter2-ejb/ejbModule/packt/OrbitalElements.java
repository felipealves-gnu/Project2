package packt;

import javax.ejb.Singleton;

@Singleton
public class OrbitalElements implements OrbitalElementsRemote {

	@Override
	public PositionBean getPosition() {
		return new PositionBean();
	}


}
