package packt;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Singleton
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SimulationContainerManaged {
	public enum State {PAUSED, RUNNING, TERMINATED}
	private State state;
	
	@Lock(LockType.READ)
	public State getState() {
		return state;
	}
	
	@Lock(LockType.WRITE)
	public void setState(State state) {
		this.state = state;
	}

}
