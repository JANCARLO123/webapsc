package ferro.store;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ferro.auxi.ApscListProductos;
import ferro.auxi.NumberToWords;
import ferro.interfaces.ApscServiceInterf;
import ferro.model.ApscCliente;
import ferro.model.ApscEstado;
import ferro.model.ApscForpago;
import ferro.model.ApscPedido;
import ferro.model.ApscPedidoDet;
import ferro.model.ApscUsuarios;
import ferro.model.ApscVendedor;

@Component(value = "freeMB")
@SessionScoped
@Scope("session")
public class FreeMB {

	@Autowired
	ApscServiceInterf apscService;
	private List<ApscListProductos> listaProductos;
	private List<ApscListProductos> listaProductosSelect;

	// filtro de datatables
	private List<ApscListProductos> listaProductosSelectFiltro;

	private ApscListProductos apscListProductos;
	private ApscListProductos apscListProductosSelect;

	// pedido efectivo
	private ApscPedido apscPedido;

	// productos en para pedidos efectivos
	private ApscPedidoDet apscPedidoDet;
	private List<ApscPedidoDet> apscPedidoDetList;

	// formas de Pago select
	private ApscForpago apscForpagoSelect;
	private List<ApscForpago> listaForPago;
	private String condiciones;

	// vendedor
	private ApscVendedor apscVendedor;

	// cliente
	private ApscCliente apscCliente;

	// estados de pedido
	private ApscEstado apscEstado;

	// autocomple de clientes
	private ApscCliente apscClienteSelect;
	private List<ApscCliente> listaClientes;

	private String dircliente;
	private String telfCelCliente;
	
	//fecha de sistema
	//@Temporal(TemporalType.DATE)
	//@DateTimeFormat(pattern = "yy-MM-dd hh:mm:ss")
	@Value("#{new java.util.Date()}")
	// @Temporal(TemporalType.DATE)
	// @DateTimeFormat(pattern = "d.M.Y G")	
	Date created;
	Calendar now = Calendar.getInstance();
	/*
	  Calendar cal = Calendar.getInstance();
      cal.setTime(datetime);  //use java.util.Date object as arguement
      // get the value of all the calendar date fields.
      System.out.println("Calendar's Year: " + cal.get(Calendar.YEAR));
      System.out.println("Calendar's Month: " + cal.get(Calendar.MONTH));
      System.out.println("Calendar's Day: " + cal.get(Calendar.DATE));
	  */
	

	// usuario activo
	private ApscUsuarios apscUsuarios;

	@Inject
	private LoginMB loginMB;

	// totales compra

	private BigDecimal VVenta;
	private BigDecimal IGV;
	private BigDecimal VTotal;

	// numero a letras
	// private NumberToWords toWords;
	private String cantidadSoles;

	// observacion de venta
	private String obs;
	
	//ciudad
	
	private String ciudad;

	protected final Log log = LogFactory.getLog(getClass());

	@PostConstruct
	public void init() {
		log.info("En PostConstruct FreeMB");
		listaProductos();
		apscPedido.setEstado(false);
		//insertamos pedido
		apscService.insertPedido(apscPedido);
		
		// forma de pago
		setListaForPago(apscService.getAllForPagoForm());
		setApscUsuarios(loginMB.getUsuarioLog());
		setApscVendedor(apscService.getApscVendedorbyUserIn(apscUsuarios.getUserIn()));
		//
		apscPedidoDet = new ApscPedidoDet();
		setApscPedidoDetList(new ArrayList<ApscPedidoDet>());
		log.info("Fin PostConstruct FreeMB");
		
		log.info("Fecha de sistema es : "+created);

	}

	// tranforma canasta a pedidos efectivos como pedidosdetalles
	public void pedidoEfectivo(){
		
		//lista de pedidos
		log.info("Tamaño pedido lista : "+listaProductosSelect.size());
		
		for(ApscListProductos obj:listaProductosSelect){
			ApscPedidoDet pedidodet=new ApscPedidoDet();
			pedidodet.setApscPedido(getApscPedido());
			pedidodet.setCdVta(null);			
			pedidodet.setEstProd(new Long(1));
			pedidodet.setItemNro((short)obj.getNroItem());
			pedidodet.setCodProd(obj.getCod_Prod());
			pedidodet.setCodComer(obj.getCod_Comer());
			pedidodet.setCant(new BigDecimal(obj.getCantidadCompra()));
			pedidodet.setPrecio(obj.getPventa_Ref());
			pedidodet.setDscto(obj.getDsctoCompra());
			pedidodet.setProdDesc(obj.getNom_Prod());
			pedidodet.setCu(obj.getPventa_Ref());
			//pedidodet.setCammda(new BigDecimal(3.12));
			//pedidodet.setFecReg(new Date());
			//pedidodet.setFecMod(new Date());
			pedidodet.setEstado(true);
			getApscPedidoDetList().add(pedidodet);
		}
		
		//log.info("Tamaño lista pedido es : "+getApscPedidoDetList());
		
		for(ApscPedidoDet obj:getApscPedidoDetList()){
			apscService.insertApscPedidoDet(obj);
		}
		
		log.info("Pedido detalle cantidad es :"+ getApscPedidoDetList().size());
		
	}

	// fin
	// seteamos datos del pedido
	public void setPedidoEfectivo() {
		pedidoEfectivo();
		apscPedido.setApscCliente(getApscCliente());
		apscPedido.setApscForpago(getApscForpagoSelect());
		apscPedido.setApscVendedor(getApscVendedor());
		apscPedido.setApscEstado(apscService.getEstadoDefecto());
		//
		apscPedido.setAnio(now.get(Calendar.YEAR)+"");
		apscPedido.setPeriodo((now.get(Calendar.MONTH) + 1)+"");
		apscPedido.setBimNeto(getVVenta());
		apscPedido.setIgv(getIGV());
		apscPedido.setTotal(getVTotal());
		apscPedido.setApscPedidoDets(getApscPedidoDetList());
		apscPedido.setObs(getObs().toUpperCase());
		apscPedido.setCiudad(getCiudad().toUpperCase());
		apscPedido.setCondPago(getCondiciones().toUpperCase());
		apscPedido.setEstado(true);
		
		log.info("Pedido Efectivo");
		apscService.updatePedido(apscPedido);

	}

	// fin

	// metodo de llenado de clientes

	public List<ApscCliente> llenarClientes(String dato) {
		listaClientes = apscService.queryClientes(dato);
		return listaClientes;
	}

	// ubicar telefono cliente ajax

	public void obtenerTelCel(ApscCliente cliente) {
		String numero1 = (cliente.getNumCel1() == null ? (cliente.getNumCel2() == null ? (cliente.getNumCel3() == null ? "SIN CEL" : cliente.getNumCel3()): cliente.getNumCel2()):cliente.getNumCel1());
		System.err.println("numero1  : " + numero1);

		String numero2 = (cliente.getTelf1() == null ? (cliente.getTelf2() == null ? "SIN CEL": cliente.getTelf2())	: cliente.getTelf1());
		System.err.println("numero2  : " + numero2);
		String final1 = ((numero1.equals("SIN CEL")) ? (numero2.equals("SIN CEL") ? "ACTUALIZAR DATOS DE CLIENTE" : cliente.getTelf2()) : numero1);

		String num1 = (cliente.getNumCel1() == null ? "" : cliente.getNumCel1())+ (cliente.getNumCel1() == null ? "": (cliente.getNumCel2() == null ? "" : "/"))+ (cliente.getNumCel2() == null ? "" : cliente.getNumCel2())+ (cliente.getNumCel2() == null ? "": (cliente.getNumCel3() == null ? "" : "/"))+ (cliente.getNumCel3() == null ? "" : cliente.getNumCel3());

		System.err.println("num1_: " + num1);

		System.err.println("numero es : " + final1);
		setTelfCelCliente((num1.equals("") ? final1 : num1));
	}

	// fin de telefono

	// Ajax behavior
	public void actionAjax(AjaxBehaviorEvent event) {
		System.err.println("EN AJAX ");
		log.info("EN AJAX");
		try {
			ApscCliente cliente = (ApscCliente) event.getComponent().getAttributes().get("value");
			System.err.println("Direccion es _: " + cliente.getDirClie());
			setDircliente(cliente.getDirClie());			
			//SET CLIENTE
			setApscCliente(cliente);
			//telefono cliente
			obtenerTelCel(cliente);
		} catch (Exception e) {
			log.info("Error ajaxBehaviorEvent : " + e.getMessage());
		}

	}

	// lista de productos en grilla para vendedores
	public void listaProductos() {
		this.apscListProductos = new ApscListProductos();
		this.apscListProductosSelect = new ApscListProductos();

		// pedido efectivo
		apscPedido = new ApscPedido();

		// producto detalle
		apscPedidoDet = new ApscPedidoDet();
		// apscPedidoDetList=new ArrayList<ApscPedidoDet>();

		// formas de pago;

		log.info("listaProductos()");

		// cliente select

		apscClienteSelect = new ApscCliente();
		listaClientes = new ArrayList<ApscCliente>();

		this.listaProductos = new ArrayList<ApscListProductos>();
		this.listaProductosSelect = new ArrayList<ApscListProductos>();
		this.listaProductosSelectFiltro = new ArrayList<ApscListProductos>();
		try {
			getListaProductos().addAll(apscService.getApscListProductos());
			getListaProductosSelectFiltro().addAll(getListaProductos());

		} catch (Exception e) {
			e.getMessage();
		}
		log.info("fin listaProductos()");
	}

	// fin lista

	// agregar productos a pedido ApscListProductos apscListProductosSelect
	public void addProd() {
		try {
			if (buscarProd(apscListProductosSelect.getCod_Prod()) == null) {
				System.err.println("cantidad compra: "+ apscListProductosSelect.getCantidadCompra());
				System.err.println("descuento : "+ apscListProductosSelect.getDsctoCompra());
				apscListProductosSelect.setSubTotalPro();
				System.err.println("subtotal: "+ apscListProductosSelect.getSubTotalPro());

				this.listaProductosSelect.add(getApscListProductosSelect());
				log.info("se agrego producto a pedido : "+ getListaProductosSelect().size());
				totalesVenta();
				//addMessage("Se agrego correctamente ");
			} else {
				addMessage("Ya esta en la canasta ");
				throw new IllegalStateException("Producto ya en la canasta");
				
			}
		} catch (Exception e) {
			e.getMessage();
		}

	}

	// fin agregar productos a pedido

	// quitar producto de carrito de compra
	public void deleteProd(String cod_Prod) {
		//
		ApscListProductos apscListProductos = null;
		apscListProductos = buscarProd(cod_Prod);
		if (apscListProductos != null) {
			this.listaProductosSelect.remove(apscListProductos);
		}
		totalesVenta();
	}

	// de quitar producto

	// buscar producto
	public ApscListProductos buscarProd(String cod_Prod) {
		ApscListProductos apscListProductos = null;
		for (ApscListProductos obj : this.listaProductosSelect) {
			if (obj.getCod_Prod().equals(cod_Prod)) {
				apscListProductos = obj;
			}
		}
		return apscListProductos;
	}

	public void totalesVenta() {

		BigDecimal sum = new BigDecimal(0);
		for (ApscListProductos obj : this.listaProductosSelect) {
			sum = sum.add(obj.getSubTotalPro());
		}

		sum = sum.setScale(3, BigDecimal.ROUND_HALF_UP);
		setVVenta(sum);
		setIGV(sum.multiply(new BigDecimal(0.18)).setScale(3,BigDecimal.ROUND_HALF_UP));
		setVTotal(getVVenta().add(getIGV()));

		System.err.println("Suma : " + sum);
		System.err.println("V.Venta : " + getVVenta());
		System.err.println("IGV : " + getIGV());
		System.err.println("TOTAL : " + getVTotal());

		// prueba numero a letras
		Float f;
		f = getVTotal().floatValue();

		System.err.println("el Float del total es :" + f);

		int dollars = (int) Math.floor(f);
		System.err.println("el entero es :" + dollars);
		int cent = (int) Math.ceil((f - dollars) * 100.0f);
		System.err.println("el decimal es :" + cent);
		System.err.println("SON : " + NumberToWords.convert(dollars) + " Y "+ cent + "/100" + " NUEVOS SOLES");

		if (f > 0) {
			setCantidadSoles(NumberToWords.convert(dollars) + " Y " + cent+ "/100" + " NUEVOS SOLES");
		} else {
			setCantidadSoles("");
		}

	}

	public void buttonAction(ActionEvent actionEvent) {
		// addMessage("Welcome to Primefaces!!");
		log.info("tipo pago es : " + getApscForpagoSelect().getDescripcion()+ " " + "Consiciones : " + getCondiciones());
		log.info("los productos son : " + getListaProductosSelect().size());
		//log.info("Vendedor : " + getApscVendedor().getNomVend());
		//log.info("Cliente : " + getApscCliente().getnombreTotal());
		System.err.println("los productos son : "+ getListaProductosSelect().size());
		
		if(getListaProductosSelect().size()>0){
			log.info("if de pedido");
			setPedidoEfectivo();
			resetFormPedido();
		}else{
			addMessage("El Pedido debe tener al menos un Item");
		}
		
	}
	
	//reset form pedido
	public void resetFormPedido(){
		//setApscCliente(null);
		log.info("inicio resetPedido");
		setApscPedido(null);
		setApscClienteSelect(null);
		setApscForpagoSelect(null);
		setListaProductosSelect(null);
		setCondiciones(null);
		setCiudad(null);
		setTelfCelCliente(null);
		setDircliente(null);
		setCantidadSoles(null);
		setObs(null);
		//montos
		setVVenta(null);
		setVTotal(null);
		setIGV(null);
		
		init();
		log.info("fin resetPedido");
	}
	//fin reset form pedido

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// NOTIFICACIONES
	public void notificationSuccess(String operation) {
		// RequestContext requestContext = RequestContext.getCurrentInstance();

		Logger.getLogger(this.getClass().getName()).log(Level.INFO,
				"Operation " + operation + " success");
		FacesMessage msg = null;
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification",
				"Success");
		FacesContext.getCurrentInstance().addMessage("messages", msg);
		// requestContext.update("growl");
	}

	public void notificationError(Exception e, String operation) {
		Logger.getLogger(this.getClass().getName()).log(Level.ERROR,
				"Operation " + operation + " Error ", e);
		FacesMessage msg = null;
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification",
				"Une erreur est survenue");
		FacesContext.getCurrentInstance().addMessage("messages", msg);
	}

	// fin buscar producto

	public List<ApscListProductos> getListaProductos() {
		return listaProductos;
	}

	public ApscServiceInterf getApscService() {
		return apscService;
	}

	public void setApscService(ApscServiceInterf apscService) {
		this.apscService = apscService;
	}

	public void setListaProductos(List<ApscListProductos> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<ApscListProductos> getListaProductosSelect() {
		return listaProductosSelect;
	}

	public void setListaProductosSelect(
			List<ApscListProductos> listaProductosSelect) {
		this.listaProductosSelect = listaProductosSelect;
	}

	public ApscListProductos getApscListProductos() {
		return apscListProductos;
	}

	public void setApscListProductos(ApscListProductos apscListProductos) {
		this.apscListProductos = apscListProductos;
	}

	public ApscListProductos getApscListProductosSelect() {
		return (apscListProductosSelect == null ? new ApscListProductos()
				: apscListProductosSelect);
	}

	public void setApscListProductosSelect(
			ApscListProductos apscListProductosSelect) {
		this.apscListProductosSelect = apscListProductosSelect;
	}

	// otros getters_setters
	public ApscPedido getApscPedido() {
		return apscPedido;
	}

	public void setApscPedido(ApscPedido apscPedido) {
		this.apscPedido = apscPedido;
	}

	public ApscPedidoDet getApscPedidoDet() {
		return apscPedidoDet;
	}

	public void setApscPedidoDet(ApscPedidoDet apscPedidoDet) {
		this.apscPedidoDet = apscPedidoDet;
	}



	public ApscForpago getApscForpagoSelect() {
		return apscForpagoSelect;
	}

	public void setApscForpagoSelect(ApscForpago apscForpagoSelect) {
		this.apscForpagoSelect = apscForpagoSelect;
	}

	public ApscVendedor getApscVendedor() {
		return apscVendedor;
	}

	public void setApscVendedor(ApscVendedor apscVendedor) {
		this.apscVendedor = apscVendedor;
	}

	public ApscCliente getApscCliente() {
		return apscCliente;
	}

	public void setApscCliente(ApscCliente apscCliente) {
		this.apscCliente = apscCliente;
	}

	public ApscEstado getApscEstado() {
		return apscEstado;
	}

	public void setApscEstado(ApscEstado apscEstado) {
		this.apscEstado = apscEstado;
	}

	public ApscCliente getApscClienteSelect() {
		return apscClienteSelect;
	}

	public void setApscClienteSelect(ApscCliente apscClienteSelect) {
		this.apscClienteSelect = apscClienteSelect;
	}

	public List<ApscCliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<ApscCliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public String getDircliente() {
		return dircliente;
	}

	public void setDircliente(String dircliente) {
		this.dircliente = dircliente;
	}

	public String getTelfCelCliente() {
		return telfCelCliente;
	}

	public void setTelfCelCliente(String telfCelCliente) {
		this.telfCelCliente = telfCelCliente;
	}

	public ApscUsuarios getApscUsuarios() {
		return apscUsuarios;
	}

	public void setApscUsuarios(ApscUsuarios apscUsuarios) {
		this.apscUsuarios = apscUsuarios;
	}

	public LoginMB getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginMB loginMB) {
		this.loginMB = loginMB;
	}

	public BigDecimal getVVenta() {
		return VVenta;
	}

	public void setVVenta(BigDecimal vVenta) {
		VVenta = vVenta;
	}

	public BigDecimal getIGV() {
		return IGV;
	}

	public void setIGV(BigDecimal iGV) {
		IGV = iGV;
	}

	public BigDecimal getVTotal() {
		return VTotal;
	}

	public void setVTotal(BigDecimal vTotal) {
		VTotal = vTotal;
	}

	public String getCantidadSoles() {
		return cantidadSoles;
	}

	public void setCantidadSoles(String cantidadSoles) {
		this.cantidadSoles = cantidadSoles;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public List<ApscForpago> getListaForPago() {
		return listaForPago;
	}

	public void setListaForPago(List<ApscForpago> listaForPago) {
		this.listaForPago = listaForPago;
	}

	public String getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(String condiciones) {
		this.condiciones = condiciones;
	}

	public List<ApscListProductos> getListaProductosSelectFiltro() {
		return listaProductosSelectFiltro;
	}

	public void setListaProductosSelectFiltro(
			List<ApscListProductos> listaProductosSelectFiltro) {
		this.listaProductosSelectFiltro = listaProductosSelectFiltro;
	}

	public List<ApscPedidoDet> getApscPedidoDetList() {
		return apscPedidoDetList;
	}

	public void setApscPedidoDetList(List<ApscPedidoDet> apscPedidoDetList) {
		this.apscPedidoDetList = apscPedidoDetList;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
}
