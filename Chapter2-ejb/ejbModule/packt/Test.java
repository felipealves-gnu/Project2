package packt;

import java.security.Security;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.sasl.JBossSaslProvider;

public class Test {

	static {
		Security.addProvider(new JBossSaslProvider());
	}
	
	public static void main(String[] args) throws NamingException {
	//	InitialContext ctx = new InitialContext();
		//NamesBean bean = (NamesBean) ctx.lookup("java:app/Chapter2-ejb/NamesBean");
	//	NamesBean bean = (NamesBean) ctx.lookup("packt.NamesBean");
		
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		
		final String appName = "";
		 
		final String moduleName = "Chapter2-ejb";// THIS IS THE NAME OF THE JAR WITH YOUR EJBs. Write its name here, without the .jar.
		 
		final String distinctName = "";//   AS7 allows deployments to have an distinct name. If you don't use this feature, let this field empty.
		 
		final String beanName = NamesBean.class.getSimpleName();	//EJB CLASS WITH THE IMPLEMENTATION (simple name)
		 
		final String viewClassName = NamesBean.class.getName();	// FULLY QUALIFIED NAME OF THE REMOTE CLASS (interface).
		 
		
		/*java:global/Chapter2/Chapter2-ejb/NamesBean!packt.NamesBean
		java:app/Chapter2-ejb/NamesBean!packt.NamesBean
		--java:module/NamesBean!packt.NamesBean
		java:global/Chapter2/Chapter2-ejb/NamesBean
		--java:app/Chapter2-ejb/NamesBean
		java:module/NamesBean*/
		
		//NamesBean bean = (NamesBean) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!"	+ viewClassName + "?stateful");
		NamesBean bean = (NamesBean) context.lookup("java:global/Chapter2/Chapter2-ejb/NamesBean!packt.NamesBean");
		
		bean.addName("felipe");
		System.out.println(bean.getNames());
	}
}
