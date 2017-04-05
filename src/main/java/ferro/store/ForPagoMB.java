package ferro.store;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ferro.interfaces.ApscServiceInterf;
import ferro.model.ApscForpago;

@Component(value = "forPagoMB")
//@SessionScoped
//@Scope("session")
public class ForPagoMB {
	
	@Autowired
	ApscServiceInterf apscService;
	//objetos procesos
	private ApscForpago apscForpago;
	private ApscForpago apscForpagoSelect;
	private List<ApscForpago> listForPago;
	private List<ApscForpago> listForPagoSelect;
	
	protected final Log log = LogFactory.getLog(getClass());
	
	
	@PostConstruct
	public void init() {
		log.info("En PostConstruct ForPagoMB");
		getAllForPagoForm();
		log.info("Fin PostConstruct ForPagoMB");
	}
	
	
	public void getAllForPagoForm(){
		apscForpago=new ApscForpago();
		apscForpagoSelect=new ApscForpago();
		listForPago=new ArrayList<ApscForpago>();
		listForPagoSelect=new ArrayList<ApscForpago>();
		try{
			listForPago.addAll(apscService.getAllForPagoForm());
		}catch(Exception e){
			e.getMessage();
		}
	}


	public ApscForpago getApscForpago() {
		return apscForpago;
	}


	public void setApscForpago(ApscForpago apscForpago) {
		this.apscForpago = apscForpago;
	}


	public ApscForpago getApscForpagoSelect() {
		return apscForpagoSelect;
	}


	public void setApscForpagoSelect(ApscForpago apscForpagoSelect) {
		this.apscForpagoSelect = apscForpagoSelect;
	}


	public List<ApscForpago> getListForPago() {
		return listForPago;
	}


	public void setListForPago(List<ApscForpago> listForPago) {
		this.listForPago = listForPago;
	}


	public List<ApscForpago> getListForPagoSelect() {
		return listForPagoSelect;
	}


	public void setListForPagoSelect(List<ApscForpago> listForPagoSelect) {
		this.listForPagoSelect = listForPagoSelect;
	}
	
	
}
