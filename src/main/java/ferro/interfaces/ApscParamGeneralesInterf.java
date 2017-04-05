package ferro.interfaces;

import java.util.List;

import ferro.model.ApscParamGenerales;

public interface ApscParamGeneralesInterf {
	
	public abstract ApscParamGenerales obtenerParametro(String cod_cat,String cod_param,boolean est_param);
	public abstract List<ApscParamGenerales> obtenerParametros(String cod_cat,	String cod_param, boolean est_param);
	public abstract List<ApscParamGenerales> getAllParametros();
	public abstract boolean insertApscParamGenerales(ApscParamGenerales apscParametrosGenerales);
	public abstract boolean deleteApscParamGenerales(ApscParamGenerales apscParametrosGenerales);
	
	public abstract ApscParamGenerales getApscParamGeneralesById(int idParam);
	public void updateApscParamGenerales(ApscParamGenerales apscParametrosGenerales);
	public  void inicia();
	public  void closeSession();
}
