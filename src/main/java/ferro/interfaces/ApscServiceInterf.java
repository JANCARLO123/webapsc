package ferro.interfaces;

import java.util.List;

import ferro.auxi.ApscListProductos;
import ferro.model.ApscCliente;
import ferro.model.ApscEstado;
import ferro.model.ApscForpago;
import ferro.model.ApscParamGenerales;
import ferro.model.ApscPedido;
import ferro.model.ApscPedidoDet;
import ferro.model.ApscUsuarios;
import ferro.model.ApscVendedor;

public interface ApscServiceInterf {
	//Parametros
	public abstract ApscParamGenerales obtenerParametro(String cod_cat,	String cod_param, boolean est_param);
	public abstract boolean insertApscParamGenerales(ApscParamGenerales apscParametrosGenerales);
	public abstract List<ApscParamGenerales> getAllParametros();

	// update
	public abstract void updateApscParamGenerales(ApscParamGenerales apscParametrosGenerales);
	public abstract boolean deleteApscParamGenerales(ApscParamGenerales apscParametrosGenerales);
	
	
	// fin de Parametros
	
	//Usuarios
	public abstract ApscUsuarios getByUserName(String j_username);
	//fin de Usuarios
	
	
	//query libre
	
	public abstract List<ApscListProductos> getApscListProductos();
	
	// forma de pago
	public abstract List<ApscForpago> getAllForPagoForm();	
	public abstract ApscForpago getForCDForpago(String cdForpago);
	public abstract List<ApscForpago> getAllForPagoFormSis();
	
	//pedidos
	public abstract long idPedidoInsert();
	public void updatePedido(ApscPedido apscPedido);
	public abstract long insertPedido(ApscPedido apscPedido);
	
	public abstract ApscPedido getApscPedidoByID(int idPedido);//transformar idPedido a long	
	public abstract List<ApscPedido> getApscPedidoByEstado(ApscEstado apscEstado);
	public abstract List<ApscPedido> getApscPedidoByVendedor(ApscVendedor apscVendedor);
	public abstract List<ApscPedido> getApscPedidoByCliente(ApscCliente apscCliente);
	
	//pedidos detalle
	public abstract void insertApscPedidoDet(ApscPedidoDet apscPedidoDet);
	
	//clientes
	public abstract List<ApscCliente> queryClientes(String dato);
	public abstract ApscCliente getBycdCliente(String cdCliente);
	
	//Vendedor
	public abstract ApscVendedor getApscVendedorbyUserIn(String cdVend);
	
	//estado pedidos
	public abstract ApscEstado getEstadoDefecto();
	
}