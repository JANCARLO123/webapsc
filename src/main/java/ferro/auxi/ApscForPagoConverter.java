package ferro.auxi;

import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ferro.dao.ApscForPagoDAO;
import ferro.model.ApscForpago;


@FacesConverter(value="apscForpagoConverter",forClass=ApscForpago.class)
@SessionScoped
public class ApscForPagoConverter implements Converter{
	
	ApscForPagoDAO apscforpagodao=new ApscForPagoDAO();

	public Object getAsObject(FacesContext context, UIComponent componet, String value) {
		System.err.println("El value es : "+value);
		return apscforpagodao.getForCDForpago(value);
	}

	public String getAsString(FacesContext contex, UIComponent componet, Object value) {
		
		String rtu="";
		if(value instanceof ApscForpago){
			ApscForpago apscForpago=(ApscForpago)value;
			rtu=apscForpago.getCdForpago();
		}
		
		System.err.println("Dentro de String fin for pago "+rtu);
		return  rtu;
	}

}
