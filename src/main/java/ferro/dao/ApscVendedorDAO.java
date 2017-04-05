package ferro.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ferro.interfaces.ApscVendedorInterf;
import ferro.model.ApscVendedor;

@Repository
public class ApscVendedorDAO implements ApscVendedorInterf {
	
	private Session session;
	protected final Log log = LogFactory.getLog(getClass());
	
	public ApscVendedor getApscVendedorbyUserIn(String cdVend) {
		ApscVendedor apscVendedor = null;
		try {
			inicia();
			apscVendedor = (ApscVendedor) session
					.createCriteria(ApscVendedor.class)
					.add(Restrictions.eq("cdVend", cdVend)).uniqueResult();
		} finally {
			closeSession();
		}
		return apscVendedor;
	}

	public void inicia() {
		log.info("En Inicia");
		session = ApscDBUtil.currentSession();

	}

	public void closeSession() {
		try {
			ApscDBUtil.closeSession();
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
