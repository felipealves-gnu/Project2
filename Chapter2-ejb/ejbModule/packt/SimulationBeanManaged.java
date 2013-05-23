package packt;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class SimulationBeanManaged {
	public enum State {PAUSED, RUNNING, TERMINATED};
	private State state;

	public State geState(){
		return state;
	}
	
	public synchronized void setState(State state){
		this.state = state;
	}
}
