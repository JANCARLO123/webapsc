package ferro.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApscDBUtil {
	
	@Autowired
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected final Log log = LogFactory.getLog(getClass());

	public static final String BD_APSC = "APSC";

	private static final Map<String, SessionFactory> sessionFactorys = new HashMap<String, SessionFactory>();
	private static final Map<String, Session> sessions = new HashMap<String, Session>();

	
	//@Qualifier("sessionFactory")
	/*@Autowired*/
	public ApscDBUtil(){
		
	}
	
	
	@Autowired
	public ApscDBUtil(SessionFactory sessionFactory) {
		//this.sessionFactory=sessionFactory;
		setSessionFactory(sessionFactory);
		//System.out.println("constructor");
		sessionFactorys.put("APSC", getSessionFactory());
	}

	public static Session getSession(String key) throws HibernateException {

		System.err.println("sessions :" + key);
		//System.out.println(sessionFactorys.get(key).toString());
		Session s = sessions.get(key);
		//System.out.println(s.isConnected());
		// Abre una nueva sesi�n si este thread todav�a no tiene ninguna
		if (s==null) {
			System.out.println("entra a sesion nula");
			s = sessionFactorys.get(key).openSession();
			sessions.put(key, s);
			System.out.println("session es :");
			System.out.println(s.isConnected());
		}
		return s;
	}

	public static Session currentSession() throws HibernateException {
		return getSession("APSC");
	}

	public static void closeSession(String key) throws HibernateException {
		System.err.println("en Close sessions:" + sessions);
		Session s = sessions.get(key);
		System.err.println("session :" + key);
		if (s != null) {
			s.close();
			sessions.put(key, null);
			System.err.println("se cerro la conexion " + s.isOpen());
		}

	}

	public static void closeSession() throws HibernateException {
		closeSession("APSC");
	}



}
