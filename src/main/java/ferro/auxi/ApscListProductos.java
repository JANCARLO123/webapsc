package ferro.auxi;

import java.math.BigDecimal;

//@Entity
//@Table(name = "VISTA_PRODUCTOS_STOCK", schema = "dbo", catalog = "ERP")
/*@NamedNativeQueries({ 
 @NamedNativeQuery(
 name = "@SQL_GET_ALL_PRODUCTOS", 
 query = "SELECT * FROM VISTA_PRODUCTOS_STOCK S ORDER BY S.CATEGORIA,S.SUB_CATEGORIA,S.PVENTA_REF DESC",
 resultClass=ApscListProductos.class) })*/

//@Entity
//@Table(name = "VISTA_PRODUCTOS_STOCK", schema = "dbo", catalog = "ERP")
public class ApscListProductos {

	private String cod_Prod;
	private String cod_Comer;
	private String categoria;
	private String sub_Categoria;
	private String nom_Prod;
	private BigDecimal pventa_Ref;
	private int cantidad;
	private String flag;
	private String marca;
	private BigDecimal dscto_Remate;
	// datos de compra
	private int nroItem;
	private int cantidadCompra;
	private BigDecimal dsctoCompra;
	private BigDecimal subTotalPro;
	

	// forma de pago
	private long idForpago;
	// cliente
	private long idCliente;
	// vendedor
	private long idVend;

	// estado pedido
	private long idEstado;

	public long getIdForpago() {
		return idForpago;
	}

	public void setIdForpago(long idForpago) {
		this.idForpago = idForpago;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public long getIdVend() {
		return idVend;
	}

	public void setIdVend(long idVend) {
		this.idVend = idVend;
	}

	public long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(long idEstado) {
		this.idEstado = idEstado;
	}

	/*public void setSubTotalPro(BigDecimal subTotalPro) {
		this.subTotalPro = subTotalPro;
	}*/

	public int getCantidadCompra() {
		return cantidadCompra;
	}

	public void setCantidadCompra(int cantidadCompra) {
		this.cantidadCompra = cantidadCompra;
	}

	public BigDecimal getDsctoCompra() {
		return dsctoCompra;
	}

	public void setDsctoCompra(BigDecimal dsctoCompra) {
		this.dsctoCompra = dsctoCompra;
	}

	public BigDecimal getSubTotalPro() {
		return subTotalPro;
	}

	public void setSubTotalPro() {
		// bi3 = bi1.multiply(bi2);
		 BigDecimal canti_x_precio=(getPventa_Ref()).multiply((new BigDecimal(getCantidadCompra())));
		 BigDecimal descuento=getDsctoCompra().divide(new BigDecimal(100));
		 BigDecimal canti_dscto=canti_x_precio.multiply(descuento);
		 
		 System.err.println("Sub_total_precio :"+canti_x_precio);
		 System.err.println("descuento :"+descuento);
		 System.err.println("cantidad descuento :"+canti_dscto);
		
		this.subTotalPro =canti_x_precio.subtract(canti_dscto); 
				
	}

	public String getCod_Prod() {
		return cod_Prod;
	}

	public void setCod_Prod(String cod_Prod) {
		this.cod_Prod = cod_Prod;
	}

	public String getCod_Comer() {
		return cod_Comer;
	}

	public void setCod_Comer(String cod_Comer) {
		this.cod_Comer = cod_Comer;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getSub_Categoria() {
		return sub_Categoria;
	}

	public void setSub_Categoria(String sub_Categoria) {
		this.sub_Categoria = sub_Categoria;
	}

	public String getNom_Prod() {
		return nom_Prod;
	}

	public void setNom_Prod(String nom_Prod) {
		this.nom_Prod = nom_Prod;
	}

	public BigDecimal getPventa_Ref() {
		return pventa_Ref;
	}

	public void setPventa_Ref(BigDecimal pventa_Ref) {
		this.pventa_Ref = pventa_Ref;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public int getNroItem() {
		return nroItem;
	}

	public void setNroItem(int nroItem) {
		this.nroItem = nroItem;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public BigDecimal getDscto_Remate() {
		return dscto_Remate;
	}

	public void setDscto_Remate(BigDecimal dscto_Remate) {
		this.dscto_Remate = dscto_Remate;
	}

}
