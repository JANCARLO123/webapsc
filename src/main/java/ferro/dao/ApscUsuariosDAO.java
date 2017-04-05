package ferro.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ferro.interfaces.ApscUsuariosInterf;
import ferro.model.ApscUsuarios;

@Repository
public class ApscUsuariosDAO implements ApscUsuariosInterf {
	
	private Session session;
	//private Transaction tx;
	protected final Log log = LogFactory.getLog(getClass());
	
	
	
	/* (non-Javadoc)
	 * @see ferro.dao.ApscUsuariosInterf#getByUserName(java.lang.String)
	 */
	public ApscUsuarios getByUserName(String j_username){
		ApscUsuarios apscUsuarios=null;
		try{
			inicia();
			apscUsuarios=(ApscUsuarios)session.createCriteria(ApscUsuarios.class).add(Restrictions.eq("userIn", j_username)).uniqueResult(); 
		}finally{
			closeSession();
		}
		return apscUsuarios;
	}
	
	/* (non-Javadoc)
	 * @see ferro.dao.ApscUsuariosInterf#inicia()
	 */
	public void inicia() {
		log.info("En Inicia");
		session = ApscDBUtil.currentSession();
		// tx = session.beginTransaction();

	}

	/* (non-Javadoc)
	 * @see ferro.dao.ApscUsuariosInterf#closeSession()
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
