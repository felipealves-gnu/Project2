package packt;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class CityFacade extends AbstractFacade<City> {
	
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
	
	public void changePopulation(String cityName, long count) {
	System.out.println("Executing changePopulation");
	Query query = em.createQuery( "UPDATE City c " +
									"SET c.population = c.population+:count " +
									"WHERE c.name = :cityName");
	query.setParameter("count", count);
	query.setParameter("cityName", cityName);
	int result = query.executeUpdate();
	System.out.println("result: " + result);
	System.out.println("--- end changePopulation");
	}
}
