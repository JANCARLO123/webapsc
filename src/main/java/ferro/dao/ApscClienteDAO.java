package ferro.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ferro.interfaces.ApscClienteInterf;
import ferro.model.ApscCliente;

@Repository
public class ApscClienteDAO implements ApscClienteInterf {

	private Session session;
	protected final Log log = LogFactory.getLog(getClass());

	// CONTROL DE SE SESIONES HIBERNATE

	/*
	 * (non-Javadoc)
	 * 
	 * @see ferro.dao.ApscClienteInterf#queryClientes(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ApscCliente> queryClientes(String dato) {
		List<ApscCliente> list = null;
		try {
			inicia();
			Criteria cri = session.createCriteria(ApscCliente.class);
			cri.add(Restrictions.or(
						Restrictions.ilike("rsocial", dato, MatchMode.ANYWHERE),
						Restrictions.ilike("ndoc", dato, MatchMode.ANYWHERE),
						Restrictions.ilike("nomCli", dato, MatchMode.ANYWHERE),
						Restrictions.ilike("apePat", dato, MatchMode.ANYWHERE),
						Restrictions.ilike("apeMat", dato, MatchMode.ANYWHERE)
						)
					);
			// cri.add(Restrictions.ilike("rsocial", dato,MatchMode.ANYWHERE));nomCli
			// cri.add(Restrictions.ilike("ndoc", dato,MatchMode.ANYWHERE));
			list = cri.list();
		} catch (HibernateException he) {
			he.getMessage();
		} finally {
			closeSession();
		}
		return list;
	}
	
	
	public ApscCliente getBycdCliente(String cdCliente){
		ApscCliente apscCliente=null;
			try{
				inicia();
				apscCliente=(ApscCliente) session.createCriteria(ApscCliente.class).add(Restrictions.eq("cdCliente", cdCliente)).uniqueResult();
			}catch(HibernateException he){
				he.getMessage();
			}finally{
				closeSession();
			}
		return apscCliente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ferro.dao.ApscClienteInterf#inicia()
	 */
	public void inicia() {
		log.info("En Inicia");
		session = ApscDBUtil.currentSession();
		// tx = session.beginTransaction();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ferro.dao.ApscClienteInterf#closeSession()
	 */
	public void closeSession() {
		// log.info("En Close session");
		try {
			ApscDBUtil.closeSession();
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
