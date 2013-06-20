package packt;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AttendeeFacade extends AbstractFacade<Attendee> {
	
	@PersistenceContext(unitName = "RegistrationApplication-ejbPU")
	private EntityManager em;

	public AttendeeFacade() {
		super(Attendee.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
