package packt;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class VoucherFacade extends AbstractFacade<Voucher> {

	@PersistenceContext(unitName = "SecurityApplication-ejbPU")
	private EntityManager em;
	
	public VoucherFacade() {
		super(Voucher.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
