package ferro.dao;

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ferro.interfaces.ApscPedidoInterf;
import ferro.model.ApscCliente;
import ferro.model.ApscEstado;
import ferro.model.ApscPedido;
import ferro.model.ApscVendedor;

@Repository
public class ApscPedidoDAO implements ApscPedidoInterf {

	private Session session;
	protected final Log log = LogFactory.getLog(getClass());

	// insert pedido

	public long insertPedido(ApscPedido apscPedido) {
		System.err.println("Ingresa a DAO");
		long idPedido = 0;
		try {
			inicia();
			idPedido = new Long(session.save(apscPedido).toString());
			// session.flush();
		} catch (HibernateException he) {
			log.info("error pedido : " + he.getMessage());
			he.getMessage();
		} finally {
			log.info("en finaly pedido :");
			closeSession();
		}
		return idPedido;
	}

	public void updatePedido(ApscPedido apscPedido) {
		log.info("EN DAO id de pedido es :" + apscPedido.getIdPedido());
		try {
			inicia();
			session.update(apscPedido);
			session.flush();
		} finally {
			closeSession();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ferro.dao.ApscPedidoInterf#idPedidoInsert()
	 */
	public long idPedidoInsert() {
		long idnew = 0;
		try {
			inicia();
			Criteria cri = session.createCriteria(ApscPedido.class);
			cri.setProjection(Projections.max("idPedido"));
			idnew = (Long) cri.list().get(0);

		} catch (Exception e) {
			e.getMessage();
		} finally {
			closeSession();
		}

		return (idnew == 0) ? 1 : idnew + 1;
	}

	
	public ApscPedido getApscPedidoByID(int idPedido) {
		//Collections.emptyList();
		
		ApscPedido list=null;//Collections.emptyList();
		try{
			inicia();
			list=(ApscPedido) session.createCriteria(ApscPedido.class).add(Restrictions.eq("idPedido", new Long(idPedido))).uniqueResult();
		}finally{
			closeSession();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ApscPedido> getApscPedidoByEstado(ApscEstado apscEstado) {
		
		List<ApscPedido> list=Collections.emptyList();
		try{
			inicia();
			list=session.createCriteria(ApscPedido.class).add(Restrictions.eq("apscEstado", apscEstado)).list();
		}finally{
			closeSession();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ApscPedido> getApscPedidoByVendedor(ApscVendedor apscVendedor) {
		List<ApscPedido> list=Collections.emptyList();
		try{
			inicia();
			list=session.createCriteria(ApscPedido.class).add(Restrictions.eq("apscVendedor", apscVendedor)).list();
		}finally{
			closeSession();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ApscPedido> getApscPedidoByCliente(ApscCliente apscCliente) {
		List<ApscPedido> list=Collections.emptyList();
		try{
			inicia();
			list=session.createCriteria(ApscPedido.class).add(Restrictions.eq("apscCliente", apscCliente)).list();
		}finally{
			closeSession();
		}
		return list;
	}

	// CONTROL DE SE SESIONES HIBERNATE

	public void inicia() {
		log.info("En Inicia");
		session = ApscDBUtil.currentSession();
		// tx = session.beginTransaction();

	}

	public void closeSession() {
		// log.info("En Close session");
		try {
			ApscDBUtil.closeSession();
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
