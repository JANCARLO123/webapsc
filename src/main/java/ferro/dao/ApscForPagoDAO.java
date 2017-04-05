package ferro.dao;

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ferro.interfaces.ApscForPagoInterf;
import ferro.model.ApscForpago;

@Repository
public class ApscForPagoDAO implements ApscForPagoInterf {
	
	private Session session;
	protected final Log log = LogFactory.getLog(getClass());
	
	
	
	
	/* (non-Javadoc)
	 * @see ferro.dao.ApscForPagoInterf#getAllForPagoForm()
	 */
	@SuppressWarnings("unchecked")
	public List<ApscForpago> getAllForPagoForm(){
		List<ApscForpago> list = Collections.emptyList();
		try {
			inicia();
			//add(Restrictions.eq("estado",true))
			list = session.createCriteria(ApscForpago.class).add(Restrictions.eq("estado",true)).addOrder(Order.desc("fecReg")).list();
		} catch (HibernateException e) {
			 e.getMessage();
			 log.info("Error : "+e.getMessage());
		}finally{
			closeSession();
		}
		
		return list;
	}
	
	//Para Sstemas
	/* (non-Javadoc)
	 * @see ferro.dao.ApscForPagoInterf#getAllForPagoFormSis()
	 */
	@SuppressWarnings("unchecked")
	public List<ApscForpago> getAllForPagoFormSis(){
		List<ApscForpago> list = Collections.emptyList();
		try {
			inicia();
			//add(Restrictions.eq("estado",true))
			list = session.createCriteria(ApscForpago.class).addOrder(Order.desc("fecReg")).list();
		} catch (HibernateException e) {
			 e.getMessage();
			 log.info("Error : "+e.getMessage());
		}finally{
			closeSession();
		}
		
		return list;
	}
	
	
	public ApscForpago getForCDForpago(String cdForpago){
		ApscForpago forpago = null;
		try{
			inicia();
			forpago=(ApscForpago) session.createCriteria(ApscForpago.class).add(Restrictions.eq("cdForpago", cdForpago)).uniqueResult();
		}finally{
			closeSession();
		}
		return forpago;
	}
	// sistemas
	
	
	//control de sesiones	
	/* (non-Javadoc)
	 * @see ferro.dao.ApscForPagoInterf#inicia()
	 */
	public void inicia() {
		log.info("En Inicia");
		session = ApscDBUtil.currentSession();
		// tx = session.beginTransaction();

	}

	/* (non-Javadoc)
	 * @see ferro.dao.ApscForPagoInterf#closeSession()
	 */
	public void closeSession() {
		//log.info("En Close session");
		try {
			ApscDBUtil.closeSession();
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
