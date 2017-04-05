package ferro.dao;

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import ferro.auxi.ApscListProductos;
import ferro.interfaces.ApscFreeInterf;

@Repository
public class ApscFreeDAO implements ApscFreeInterf {

	private Session session;
	// private Transaction tx;

	protected final Log log = LogFactory.getLog(getClass());

	// LISTA DE PRODUCTOS
	@SuppressWarnings("unchecked")
	public List<ApscListProductos> getApscListProductos() {
		List<ApscListProductos> list = Collections.emptyList();
		try {
			inicia();
			// Query query = session.getNamedQuery("@HQL_GET_ALL_PRODUCTOS");
			//String sql = "SELECT S.*,COALESCE(D.DSCTO_REMATE,0)[DSCTO_REMATE] FROM VISTA_PRODUCTOS_STOCK S LEFT JOIN VENCLIPRO_PROD_DSCTO D ON D.COD_PROD=S.COD_PROD AND D.COD_COMER=S.COD_COMER ORDER BY S.CATEGORIA,S.SUB_CATEGORIA,S.MARCA,S.PVENTA_REF DESC";
			String sql ="SELECT S.* FROM VISTA_PRODUCTOS_STOCK S";
			Query query = session
					.createSQLQuery(sql)
					// .addEntity(ApscListProductos.class);
					.addScalar("cod_Prod", StandardBasicTypes.STRING)
					.addScalar("cod_Comer", StandardBasicTypes.STRING)
					.addScalar("categoria", StandardBasicTypes.STRING)
					.addScalar("sub_Categoria", StandardBasicTypes.STRING)
					.addScalar("nom_Prod", StandardBasicTypes.STRING)
					.addScalar("pventa_Ref", StandardBasicTypes.BIG_DECIMAL)
					.addScalar("dscto_Remate", StandardBasicTypes.BIG_DECIMAL)
					.addScalar("cantidad", StandardBasicTypes.INTEGER)
					.addScalar("flag", StandardBasicTypes.STRING)
					.addScalar("marca", StandardBasicTypes.STRING)
					.setResultTransformer(Transformers.aliasToBean(ApscListProductos.class));
				query.setComment("VISTA PERSONALIZADA");

			list = query.list();
		} finally {
			closeSession();
		}
		return list;
	}

	// CONTROL DE SE SESIONES HIBERNATE

	/*
	 * (non-Javadoc)
	 * 
	 * @see ferro.dao.ApscFreeInterf#inicia()
	 */
	public void inicia() {
		log.info("En Inicia");
		session = ApscDBUtil.currentSession();
		// tx = session.beginTransaction();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ferro.dao.ApscFreeInterf#closeSession()
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
