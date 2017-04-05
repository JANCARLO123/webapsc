package ferro.interfaces;

import java.util.List;

import ferro.model.ApscCliente;

public interface ApscClienteInterf {

	public abstract List<ApscCliente> queryClientes(String dato);
	
	public abstract ApscCliente getBycdCliente(String cdCliente);

	public abstract void inicia();

	public abstract void closeSession();

}