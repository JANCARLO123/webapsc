package ferro.store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ferro.interfaces.ApscServiceInterf;
import ferro.model.ApscParamGenerales;

//@ManagedBean//(name = "paramMB")
@Component(value = "paramMB")
@ViewScoped
public class ParamMB implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	ApscServiceInterf apscParamGeneralesSer;
	private ApscParamGenerales paramGenerales;
	private ApscParamGenerales paramGeneralesSelec;
	private List<ApscParamGenerales> list;// = new
											// ArrayList<ApscParamGenerales>();
	private List<ApscParamGenerales> listSelected;// = new
													// ArrayList<ApscParamGenerales>();
	private List<String> listId;

	protected final Log log = LogFactory.getLog(getClass());



	@PostConstruct
	public void init() {
		log.info("En PostConstruct");
		refreshList();
		log.info("Fin PostConstruct");
	}

	// lista de parametros

	public void refreshList() {
		log.info("INICIO REFRESH");
		this.paramGenerales = new ApscParamGenerales();
		this.paramGeneralesSelec = new ApscParamGenerales();
		this.list = new ArrayList<ApscParamGenerales>();
		this.listSelected = new ArrayList<ApscParamGenerales>();
		this.listId = new ArrayList<String>();
		try {			
			log.info("En el List " + apscParamGeneralesSer.toString());
			getList().addAll(apscParamGeneralesSer.getAllParametros());
			log.info("Es vacio : " + getList().isEmpty());			
			getListSelected().addAll(getList());			
			System.err.println("Tamaño de lista fuera: " + getList().size());
		
		} catch (Exception e) {
			e.printStackTrace();
			log.info("error : " + e.getMessage());
		}

		log.info("FIN DE REFRESH ");
	}

	public void register() {

		try {
			apscParamGeneralesSer.insertApscParamGenerales(this.paramGenerales);
			refreshList();
			notificationSuccess("se inserto correctamente el parámetro con el ID : "
					+ this.paramGenerales.getIdParam());
		} catch (Exception e) {
			notificationError(e, "Hubo un error!");
		}
	
	}

	public void update() {
		try {
			apscParamGeneralesSer.updateApscParamGenerales(this.paramGeneralesSelec);
			refreshList();
			notificationSuccess("Se uptualizo de forma correcta");
		} catch (Exception e) {
			notificationError(e, "Hubo un error!");
		}
	}

	public void delete() {
		try {
			apscParamGeneralesSer.deleteApscParamGenerales(this.paramGeneralesSelec);
			refreshList();
			notificationSuccess("Se borro de forma correcta");
		} catch (Exception e) {
			notificationError(e, "Hubo un error!");
		}
	}

	// activar parametro
	public void activate() {
		try {
			if (this.paramGeneralesSelec.getEstado() != true) {
				this.paramGeneralesSelec.setEstado(true);
				apscParamGeneralesSer.updateApscParamGenerales(this.paramGeneralesSelec);
				notificationSuccess("Se uptualizo de forma correcta");
			} else {
				notificationSuccess("El parámetro esta activo");
			}

		} catch (Exception e) {
			notificationError(e, "Hubo un error!");
		}
	}

	// fin activar parametro

	// generales
	public void onCancel(RowEditEvent event) {
		refreshList();
	}

	public void reset() {
		refreshList();
		RequestContext.getCurrentInstance().reset("form1:panel");
	}

	// notificaciones

	public void notificationSuccess(String operation) {
		Logger.getLogger(this.getClass().getName()).log(Level.INFO,"Operation " + operation + " success");
		FacesMessage msg = null;
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification","Success");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void notificationError(Exception e, String operation) {
		Logger.getLogger(this.getClass().getName()).log(Level.ERROR,"Operation " + operation + " Error ", e);
		FacesMessage msg = null;
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification","Une erreur est survenue");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	// agregado

	/*
	 * public void onRowSwipeRight(SwipeEvent event) { ApscParamGenerales
	 * apscGem = ((ApscParamGenerales) event.getData()); // cars3.remove(car);
	 * apscParamGeneralesSer.deleteApscParamGenerales(apscGem);
	 * 
	 * if (listSelected != null && !listSelected.isEmpty()) { //
	 * selectedCars.remove(car);
	 * apscParamGeneralesSer.deleteApscParamGenerales(apscGem); } }
	 * 
	 * public void onRowSwipeLeft(SwipeEvent event) { FacesMessage msg = new
	 * FacesMessage("Swiped Left", ((ApscParamGenerales)
	 * event.getData()).getIdParam() + "");
	 * FacesContext.getCurrentInstance().addMessage(null, msg); }
	 */

	// getters a setters
	public ApscParamGenerales getParamGenerales() {
		return paramGenerales;
	}

	public void setParamGenerales(ApscParamGenerales paramGenerales) {
		this.paramGenerales = paramGenerales;
	}

	// getter

	public ApscParamGenerales getParamGeneralesSelec() {
		return paramGeneralesSelec;
	}

	public void setParamGeneralesSelec(ApscParamGenerales paramGeneralesSelec) {
		this.paramGeneralesSelec = paramGeneralesSelec;
	}

	public List<ApscParamGenerales> getList() {
		
		return list;
	}

	public void setList(List<ApscParamGenerales> list) {
		this.list = list;
	}

	public List<ApscParamGenerales> getListSelected() {
		return listSelected;
	}

	public void setListSelected(List<ApscParamGenerales> listSelected) {
		this.listSelected = listSelected;
	}

	public List<String> getListId() {
		return listId;
	}

	public void setListId(List<String> listId) {
		this.listId = listId;
	}

	public ApscServiceInterf getApscParamGeneralesSer() {
		return apscParamGeneralesSer;
	}

	public void setApscParamGeneralesSer(
			ApscServiceInterf apscParamGeneralesSer) {
		this.apscParamGeneralesSer = apscParamGeneralesSer;
	}

	// exportadores
	public void postProcessXLS(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		CellStyle style = wb.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());

		for (Row row : sheet) {
			for (Cell cell : row) {
				cell.setCellValue(cell.getStringCellValue().toUpperCase());
				cell.setCellStyle(style);
			}
		}
	}
	// fin

}
