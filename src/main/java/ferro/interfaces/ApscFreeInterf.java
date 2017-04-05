package ferro.interfaces;

import java.util.List;

import ferro.auxi.ApscListProductos;

public interface ApscFreeInterf {

	//LISTA DE PRODUCTOS
	public abstract List<ApscListProductos> getApscListProductos();

	public abstract void inicia();

	public abstract void closeSession();

}