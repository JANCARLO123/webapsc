package ferro.auxi;

import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ferro.dao.ApscClienteDAO;
import ferro.model.ApscCliente;

@FacesConverter(value="apscClienteConverter",forClass=ApscCliente.class)

@SessionScoped
public class ApscClienteConverter implements Converter {
	
	//@Autowired
	ApscClienteDAO apscService=new ApscClienteDAO();
	
	

	public Object getAsObject(FacesContext context, UIComponent componet, String value) {
		System.err.println("El Valor es : "+value);	
	//	Log.info("El Valor es : "+value);
		return apscService.getBycdCliente(value.trim());
	}

	public String getAsString(FacesContext contex, UIComponent componet, Object value) {
		//Log.info("Dentro de String");
		String rtu="";
		if (value instanceof ApscCliente) {
			ApscCliente apscCliente=(ApscCliente)value;
			rtu=apscCliente.getCdCliente();
		}
		
		//Log.info("Dentro de String fin "+rtu);
		System.err.println("Dentro de String fin "+rtu);
		return rtu;
	}

}
