package packt;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PopulationManager {

	@EJB
	CityFacade cityFacade;
	
    public PopulationManager() {
    }

    public void addCity(String cityName, String county, long population) {
    	City city = new City(cityName, county, population);
    	cityFacade.create(city);
    }
    
    public void updatePopulation(String cityName, long count){
    	cityFacade.changePopulation(cityName, count);
    }
}
