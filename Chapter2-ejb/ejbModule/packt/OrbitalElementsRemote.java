package packt;

import javax.ejb.Remote;

@Remote
public interface OrbitalElementsRemote {

	/**
	 * This technique illustrates a coarse-grained approach.
	 */
	public PositionBean getPosition();
}
