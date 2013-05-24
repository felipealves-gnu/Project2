package packt;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;

@Stateless
@Named("account")
public class AccountBean implements AccountBeanRemote, AccountBeanLocal {
	private float corporateDiscount;
	private float nonProfitDiscount;
	
	@PostConstruct
	public void initialize(){
		corporateDiscount = 0.15f;
		nonProfitDiscount = 0.25f;
	}

	@Override
	public void setCorporateDiscount(float corporateDiscount) {
		this.corporateDiscount = corporateDiscount;
	}

	@Override
	public void setNonProfitDiscount(float nonProfitDiscount) {
		this.nonProfitDiscount = nonProfitDiscount;
	}

	@Override
	public float getCorporateDiscount() {
		return corporateDiscount;
	}

	@Override
	public float getNonProfitDiscount() {
		return nonProfitDiscount;
	}

	
}
