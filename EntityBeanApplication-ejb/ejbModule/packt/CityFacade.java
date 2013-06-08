package packt;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CityFacade extends AbstractFacade<City>  implements SessionSynchronization{
	
	@PersistenceContext(unitName = "EntityBeanApplication-ejbPU")
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public CityFacade() {
		super(City.class);
	}

	public void create(City entity) {
		getEntityManager().persist(entity);
	}
	
	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	//@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void changePopulation(String cityName, long count) {
		System.out.println("--- start changePopulation");
		Query query = em.createQuery( "UPDATE City c " +
										"SET c.population = c.population + :count " +
										"WHERE c.name = :cityName");
		query.setParameter("count", count);
		query.setParameter("cityName", cityName);
		int result = query.executeUpdate();
		System.out.println("result: " + result);
		System.out.println("--- end changePopulation");
	}

	@Override
	public void afterBegin() throws EJBException, RemoteException {
		System.out.println("\nCityFacade afterBegin");		
	}


	@Override
	public void beforeCompletion() throws EJBException, RemoteException {
		System.out.println("CityFacade beforeCompletion");
	}
	
	@Override
	public void afterCompletion(boolean committed) throws EJBException,
	RemoteException {
		System.out.println("CityFacade afterCompletion\n");		
	}
	
}
