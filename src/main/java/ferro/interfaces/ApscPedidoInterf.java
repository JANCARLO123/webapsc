package ferro.interfaces;

import java.util.List;

import ferro.model.ApscCliente;
import ferro.model.ApscEstado;
import ferro.model.ApscPedido;
import ferro.model.ApscVendedor;

public interface ApscPedidoInterf {

	public abstract long idPedidoInsert();
	
	public abstract long insertPedido(ApscPedido apscPedido);
	
	public abstract ApscPedido getApscPedidoByID(int idPedido);//transformar idPedido a long	
	public abstract List<ApscPedido> getApscPedidoByEstado(ApscEstado apscEstado);
	public abstract List<ApscPedido> getApscPedidoByVendedor(ApscVendedor apscVendedor);
	public abstract List<ApscPedido> getApscPedidoByCliente(ApscCliente apscCliente);
	
	public void updatePedido(ApscPedido apscPedido);
	public abstract void inicia();
	public abstract void closeSession();

}