package ferro.interfaces;

import ferro.model.ApscUsuarios;

public interface ApscUsuariosInterf {

	public abstract ApscUsuarios getByUserName(String userIn);

	public void inicia();

	public void closeSession();

}