package ferro.interfaces;

import ferro.model.ApscPedidoDet;

public interface ApscPedidoDetInterf {

	public abstract void insertApscPedidoDet(ApscPedidoDet apscPedidoDet);

	public abstract void inicia();

	public abstract void closeSession();

}