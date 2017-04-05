package ferro.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ferro.auxi.ApscListProductos;
import ferro.interfaces.ApscClienteInterf;
import ferro.interfaces.ApscEstadoInterf;
import ferro.interfaces.ApscForPagoInterf;
import ferro.interfaces.ApscFreeInterf;
import ferro.interfaces.ApscParamGeneralesInterf;
import ferro.interfaces.ApscPedidoDetInterf;
import ferro.interfaces.ApscPedidoInterf;
import ferro.interfaces.ApscServiceInterf;
import ferro.interfaces.ApscUsuariosInterf;
import ferro.interfaces.ApscVendedorInterf;
import ferro.model.ApscCliente;
import ferro.model.ApscEstado;
import ferro.model.ApscForpago;
import ferro.model.ApscParamGenerales;
import ferro.model.ApscPedido;
import ferro.model.ApscPedidoDet;
import ferro.model.ApscUsuarios;
import ferro.model.ApscVendedor;


//@ManagedBean
//@ApplicationScoped
@Service
public class ApscServiceImple implements ApscServiceInterf {
	
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired	
	ApscParamGeneralesInterf apscParamGeneralesDAO;
	
	@Autowired
	ApscUsuariosInterf apscUsuariosDAO;
	
	
	// free DAO
	@Autowired
	ApscFreeInterf apscFreeDAO;
	
	@Autowired
	ApscForPagoInterf apscForPagoDAO;
	
	
	//pedidos
	@Autowired
	ApscPedidoInterf apscPedidoDAO;
	
	//cliente
	@Autowired
	ApscClienteInterf apscClienteDAO;
	
	//vendedor
	@Autowired
	ApscVendedorInterf apscVendedorDAO;
	
	//estado pedidos
	@Autowired
	ApscEstadoInterf apscEstadoDAO;
	
	//pedidos detalle
	@Autowired
	ApscPedidoDetInterf apscPedidoDetDAO;
	
	public ApscServiceImple(){
		
	}
	
	// Obtener parametro
	
	/* (non-Javadoc)
	 * @see ferro.services.ServiceInterf#obtenerParametro(java.lang.String, java.lang.String, boolean)
	 */
	public ApscParamGenerales obtenerParametro(String cod_cat,String cod_param, boolean est_param) {
		log.info("En el servcio");
		return apscParamGeneralesDAO.obtenerParametro(cod_cat, cod_param, est_param);
	}
	
	// insert
	
	/* (non-Javadoc)
	 * @see ferro.services.ServiceInterf#insertApscParamGenerales(ferro.model.ApscParamGenerales)
	 */
	public boolean  insertApscParamGenerales(ApscParamGenerales apscParametrosGenerales) {
	//	System.err.println("servicio insert : "+apscParametrosGenerales.getValParam());
		return apscParamGeneralesDAO.insertApscParamGenerales(apscParametrosGenerales);
	}
	
	//todos los parametros
	
	/* (non-Javadoc)
	 * @see ferro.services.ServiceInterf#getAllParametros()
	 */
	public List<ApscParamGenerales> getAllParametros() {
		return apscParamGeneralesDAO.getAllParametros();
	}
	
	// update
	/* (non-Javadoc)
	 * @see ferro.services.ServiceInterf#updateApscParamGenerales(ferro.model.ApscParamGenerales)
	 */
	public void updateApscParamGenerales(ApscParamGenerales apscParametrosGenerales){
		apscParamGeneralesDAO.updateApscParamGenerales(apscParametrosGenerales);
	}
	
	/* (non-Javadoc)
	 * @see ferro.services.ServiceInterf#deleteApscParamGenerales(ferro.model.ApscParamGenerales)
	 */
	public boolean deleteApscParamGenerales(ApscParamGenerales apscParametrosGenerales){
		return apscParamGeneralesDAO.deleteApscParamGenerales(apscParametrosGenerales);
	}

	public ApscUsuarios getByUserName(String userIn) {
		
		return apscUsuariosDAO.getByUserName(userIn);
	}
	
	//Lista de productos

	public List<ApscListProductos> getApscListProductos() {
		
		return apscFreeDAO.getApscListProductos();
	}
	
	//Forma de Pago
	public List<ApscForpago> getAllForPagoForm() {
		
		return apscForPagoDAO.getAllForPagoForm();
	}

	public List<ApscForpago> getAllForPagoFormSis() {
		
		return apscForPagoDAO.getAllForPagoFormSis();
	}
	public ApscForpago getForCDForpago(String cdForpago){
		return apscForPagoDAO.getForCDForpago(cdForpago);
	}
	
	//pedido
	
	public long idPedidoInsert(){
		return apscPedidoDAO.idPedidoInsert();
	}
	
	public long insertPedido(ApscPedido apscPedido) {
		System.err.println("Pedido en servicio");
		return apscPedidoDAO.insertPedido(apscPedido);
	}
	public void updatePedido(ApscPedido apscPedido) {
		apscPedidoDAO.updatePedido(apscPedido);
		
	}
	
	public ApscPedido getApscPedidoByID(int idPedido) {
		
		return apscPedidoDAO.getApscPedidoByID(idPedido);
	}

	public List<ApscPedido> getApscPedidoByEstado(ApscEstado apscEstado) {
		
		return apscPedidoDAO.getApscPedidoByEstado(apscEstado);
	}

	public List<ApscPedido> getApscPedidoByVendedor(ApscVendedor apscVendedor) {
		
		return apscPedidoDAO.getApscPedidoByVendedor(apscVendedor);
	}

	public List<ApscPedido> getApscPedidoByCliente(ApscCliente apscCliente) {
		
		return apscPedidoDAO.getApscPedidoByCliente(apscCliente);
	}
	

	//cliente
	public  List<ApscCliente> queryClientes(String dato){
		return apscClienteDAO.queryClientes(dato);
	}

	public ApscCliente getBycdCliente(String cdCliente) {
		
		return apscClienteDAO.getBycdCliente(cdCliente);
	}

	public ApscVendedor getApscVendedorbyUserIn(String cdVend) {
	
		return apscVendedorDAO.getApscVendedorbyUserIn(cdVend);
	}

	//estados pedidos
	public ApscEstado getEstadoDefecto() {
	
		return apscEstadoDAO.getEstadoDefecto();
	}
	
	//pedidos detalle
	public void insertApscPedidoDet(ApscPedidoDet apscPedidoDet) {
		apscPedidoDetDAO.insertApscPedidoDet(apscPedidoDet);
		
	}

	

	

}
