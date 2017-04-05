package ferro.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ferro.interfaces.ApscPedidoDetInterf;
import ferro.model.ApscPedidoDet;


@Repository
public class ApscPedidoDetDAO implements ApscPedidoDetInterf {
	private Session session;
	protected final Log log = LogFactory.getLog(getClass());
	
	
	
	
	/* (non-Javadoc)
	 * @see ferro.dao.ApscPedidoDetInterf#insertApscPedidoDet(ferro.model.ApscPedidoDet)
	 */
	public void insertApscPedidoDet(ApscPedidoDet apscPedidoDet){
		try{
			inicia();
			session.save(apscPedidoDet);
		}finally{
			closeSession();
		}
	}
	
	/* (non-Javadoc)
	 * @see ferro.dao.ApscPedidoDetInterf#inicia()
	 */
	public void inicia() {
		log.info("En Inicia");
		session = ApscDBUtil.currentSession();
		
	}

	
	/* (non-Javadoc)
	 * @see ferro.dao.ApscPedidoDetInterf#closeSession()
	 */
	public void closeSession() {
		
		try {
			ApscDBUtil.closeSession();
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
