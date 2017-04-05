package ferro.dao;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ferro.interfaces.ApscParamGeneralesInterf;
import ferro.model.ApscParamGenerales;

@Repository
public class ApscParamGeneralesDAO implements ApscParamGeneralesInterf {

	private Session session;
	//private Transaction tx;

	protected final Log log = LogFactory.getLog(getClass());

	public ApscParamGenerales obtenerParametro(String cod_cat,String cod_param, boolean est_param) {
		System.out.println("Entra");
		ApscParamGenerales apscParamGenerales = null;
		try {
			// log.info("en try");
			// System.out.println("en try");
			inicia();
			// System.out.println("desde funcion obtenerParametro");
			// System.out.println(session.isConnected());
			Criteria cri = session.createCriteria(ApscParamGenerales.class);
			cri.add(Restrictions.eq("codCate", cod_cat));
			cri.add(Restrictions.eq("codParam", cod_param));
			cri.add(Restrictions.eq("estado", est_param));
			apscParamGenerales = (ApscParamGenerales) cri.uniqueResult();
		} catch (HibernateException e) {
			System.out.println("La excepcion es por : " + e.getMessage());

		} finally {
			closeSession();
		}

		return apscParamGenerales;
	}

	@SuppressWarnings("unchecked")
	public List<ApscParamGenerales> obtenerParametros(String cod_cat,
			String cod_param, boolean est_param) {
		List<ApscParamGenerales> list = Collections.emptyList();

		try {
			inicia();
			Criteria cri = session.createCriteria(ApscParamGenerales.class);
			cri.add(Restrictions.eq("codCate", cod_cat));
			cri.add(Restrictions.eq("codParam", cod_param));
			cri.add(Restrictions.eq("estado", est_param));

			list = cri.list();
		} finally {
			closeSession();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ApscParamGenerales> getAllParametros() {
		List<ApscParamGenerales> list = null; //new ArrayList<ApscParamGenerales>(); //Collections.emptyList();

		try {
			inicia();
			//add(Restrictions.eq("estado",true))
			list = session.createCriteria(ApscParamGenerales.class).addOrder(Order.desc("fecReg")).list();
			
			//list = Collections.checkedList(session.createCriteria(ApscParamGenerales.class).addOrder(Order.desc("fecReg")).list(), ApscParamGenerales.class);
		} finally {
			closeSession();
			log.info("Tamaño lista desde DAO es : "+list.size());
		}

		return list;
	}

	public boolean insertApscParamGenerales(ApscParamGenerales apscParametrosGenerales) {

		System.err.println(apscParametrosGenerales.getCodCate());
		boolean b = false;
		String verif = "";
		// session.save(apscParametrosGenerales).toString();
		try {
			inicia();

			verif = session.save(apscParametrosGenerales).toString();
			// /tx.commit();
			System.err.println(verif);

		} catch (HibernateException he) {
			he.getMessage();
			//tx.rollback();
		} finally {
			log.info("En finally del insert ParametrosGenerales");
			closeSession();
			log.info("estado sesion " + !session.isOpen());
		}

		if (!verif.equals("")) {
			b = true;
			log.info("se guardo con el id :" + verif);
		}

		return b;
	}

	
	public boolean deleteApscParamGenerales(ApscParamGenerales apscParametrosGenerales) {
		
		if(apscParametrosGenerales.getEstado()){
			apscParametrosGenerales.setEstado(false);
			updateApscParamGenerales(apscParametrosGenerales);
			return true;
		}else{
			return false;
		}
		

		
	}

	public ApscParamGenerales getApscParamGeneralesById(int idParam) {
		ApscParamGenerales apscParamGenerales=null;
		try{
			inicia();
			apscParamGenerales=(ApscParamGenerales) session.createCriteria(ApscParamGenerales.class).add(Restrictions.eq("idParam", new Long(idParam)));
			
		}finally{
			closeSession();
		}
		
		return apscParamGenerales;
	}

	public void updateApscParamGenerales(ApscParamGenerales apscParametrosGenerales) {
		try {
			inicia();
			//set new date fecha de modificacion			
			apscParametrosGenerales.setFecMod(new Date());
			session.update(apscParametrosGenerales);
			session.flush();
			//tx.commit();
		} finally {
			closeSession();
		}

	}
	
	public void inicia() {
		log.info("En Inicia");
		session = ApscDBUtil.currentSession();
		// tx = session.beginTransaction();

	}

	public void closeSession() {
		//log.info("En Close session");
		try {
			ApscDBUtil.closeSession();
		} catch (Exception e) {
			e.getMessage();
		}
	}


}
