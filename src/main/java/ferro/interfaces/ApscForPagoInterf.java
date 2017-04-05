package ferro.interfaces;

import java.util.List;

import ferro.model.ApscForpago;

public interface ApscForPagoInterf {

	public abstract List<ApscForpago> getAllForPagoForm();
	public abstract ApscForpago getForCDForpago(String cdForpago);

	//Para Sistemas
	public abstract List<ApscForpago> getAllForPagoFormSis();

	// sistemas

	//control de sesiones	
	public abstract void inicia();

	public abstract void closeSession();

}