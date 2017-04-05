package ferro.interfaces;

import ferro.model.ApscEstado;

public interface ApscEstadoInterf {

	public abstract ApscEstado getEstadoDefecto();

	public abstract void inicia();

	public abstract void closeSession();

}