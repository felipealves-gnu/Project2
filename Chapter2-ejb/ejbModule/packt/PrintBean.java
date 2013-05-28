package packt;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * The two ways to implement asynchronously. 
 * @author felipe
 *
 */
@Stateless
@LocalBean
public class PrintBean {

	@Asynchronous
	public void printAndForget(){
		System.out.println("***printAndForget***");
	}
	
	@Asynchronous
	public Future<String> printAndCheckLater(){
		System.out.println("***printAndCheckLater***");
		return new AsyncResult<String>("OK");
	}
	
}
