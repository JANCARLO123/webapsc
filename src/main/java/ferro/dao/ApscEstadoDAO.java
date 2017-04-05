package ferro.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ferro.interfaces.ApscEstadoInterf;
import ferro.model.ApscEstado;

@Repository
public class ApscEstadoDAO implements ApscEstadoInterf {
	private Session session;
	protected final Log log = LogFactory.getLog(getClass());

	/* (non-Javadoc)
	 * @see ferro.dao.ApscEstadoInterf#getEstadoDefecto()
	 */
	public ApscEstado getEstadoDefecto() {
		ApscEstado estado = null;
		try {
			inicia();
			estado = (ApscEstado) session.createCriteria(ApscEstado.class)
					.add(Restrictions.eq("idEstado", new Long(1)))
					.uniqueResult();
		} finally {
			closeSession();
		}
		return estado;
	}

	/* (non-Javadoc)
	 * @see ferro.dao.ApscEstadoInterf#inicia()
	 */
	public void inicia() {
		log.info("En Inicia");
		session = ApscDBUtil.currentSession();
		// tx = session.beginTransaction();

	}

	/* (non-Javadoc)
	 * @see ferro.dao.ApscEstadoInterf#closeSession()
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
