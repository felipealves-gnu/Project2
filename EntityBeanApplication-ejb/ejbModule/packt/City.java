package packt;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: City
 *
 */
@Entity
public class City implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String country;
	private long population;
	
	public City() {
		super();
	}
	
	public City(String name, String country, long population) {
		this.name = name;
		this.country = country;
		this.population = population;
	}
}
