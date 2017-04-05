package ferro.interfaces;

import ferro.model.ApscVendedor;

public interface ApscVendedorInterf {
	public abstract ApscVendedor getApscVendedorbyUserIn(String cdVend);

	public abstract void inicia();

	public abstract void closeSession();
}
