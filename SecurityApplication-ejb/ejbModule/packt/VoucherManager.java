package packt;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class VoucherManager {

	@EJB
	VoucherFacade voucherFacade;
	Voucher voucher;
	
    public void createVoucher(String name, String destination, BigDecimal amount) {
    	voucher = new Voucher(name, destination, amount);
    	voucherFacade.create(voucher);
    }
    
    public String getName() {
    	return voucher.getName();
    }
    
    public String getDestination() {
    	return voucher.getDestination();
    }
    
    public BigDecimal getAmount() {
    	return voucher.getAmount();
    }
    
    /**
     * This method is intended to be used by an employee to submit a voucher
     * for approval by a manager.
     */
    public void submit(){
    	System.out.println("voucher submitted");
    }
    
    /**
     * This method is used by a manager to approve a voucher.
     * 
     * @return boolean
     */
    public boolean approve(){
    	voucher.setApproved(true);
    	return true;
    }
    
    /**
     * This method is used by a manager to reject a voucher. 
     * 
     * @return boolean
     */
    public boolean reject(){
    	voucher.setApproved(false);
    	return false;
    }
}
