package ferro.model;

// Generated 17/03/2015 01:50:11 PM by Hibernate Tools 3.6.0

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 * ApscPedidoDet generated by hbm2java
 */
@Entity
@Table(name = "APSC_PEDIDO_DET",schema="ERP"
/*"dbo"
,catalog="ERP"*/)
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ApscPedidoDet implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long idPedDet;
	private ApscPedido apscPedido;
	private String cdVta;
	private String idProd;
	private Long estProd;
	private Short itemNro;
	private String codProd;
	private String codComer;
	private BigDecimal cant;
	private BigDecimal precio;
	private BigDecimal dscto;
	private BigDecimal cammda;
	private Date fecReg;
	private Date fecMod;
	private Boolean estado;
	private String prodDesc;
	private BigDecimal cu;
	private Set<ApscEstProdXPed> apscEstProdXPeds = new HashSet<ApscEstProdXPed>(0);

	public ApscPedidoDet() {
	}

	public ApscPedidoDet(long idPedDet) {
		this.idPedDet = idPedDet;
	}

	public ApscPedidoDet(long idPedDet, ApscPedido apscPedido, String cdVta,
			String idProd, Long estProd, Short itemNro, String codProd,
			String codComer, BigDecimal cant, BigDecimal precio,
			BigDecimal dscto, BigDecimal cammda, Date fecReg, Date fecMod,
			Boolean estado,String prodDesc,BigDecimal cu, Set<ApscEstProdXPed> apscEstProdXPeds) {
		this.idPedDet = idPedDet;
		this.apscPedido = apscPedido;
		this.cdVta = cdVta;
		this.idProd = idProd;
		this.estProd = estProd;
		this.itemNro = itemNro;
		this.codProd = codProd;
		this.codComer = codComer;
		this.cant = cant;
		this.precio = precio;
		this.dscto = dscto;
		this.cammda = cammda;
		this.fecReg = fecReg;
		this.fecMod = fecMod;
		this.estado = estado;
		this.prodDesc=prodDesc;
		this.cu=cu;
		this.apscEstProdXPeds = apscEstProdXPeds;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PED_DET", unique = true, nullable = false)
	public long getIdPedDet() {
		return this.idPedDet;
	}

	public void setIdPedDet(long idPedDet) {
		this.idPedDet = idPedDet;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PEDIDO")
	public ApscPedido getApscPedido() {
		return this.apscPedido;
	}

	public void setApscPedido(ApscPedido apscPedido) {
		this.apscPedido = apscPedido;
	}

	@Column(name = "CD_VTA", length = 16)
	public String getCdVta() {
		return this.cdVta;
	}

	public void setCdVta(String cdVta) {
		this.cdVta = cdVta;
	}

	@Column(name = "ID_PROD", length = 16)
	public String getIdProd() {
		return this.idProd;
	}

	public void setIdProd(String idProd) {
		this.idProd = idProd;
	}

	@Column(name = "EST_PROD")
	public Long getEstProd() {
		return this.estProd;
	}

	public void setEstProd(Long estProd) {
		this.estProd = estProd;
	}

	@Column(name = "ITEM_NRO")
	public Short getItemNro() {
		return this.itemNro;
	}

	public void setItemNro(Short itemNro) {
		this.itemNro = itemNro;
	}

	@Column(name = "COD_PROD", length = 16)
	public String getCodProd() {
		return this.codProd;
	}

	public void setCodProd(String codProd) {
		this.codProd = codProd;
	}

	@Column(name = "COD_COMER", length = 16)
	public String getCodComer() {
		return this.codComer;
	}

	public void setCodComer(String codComer) {
		this.codComer = codComer;
	}

	@Column(name = "CANT", precision = 18, scale = 7)
	public BigDecimal getCant() {
		return this.cant;
	}

	public void setCant(BigDecimal cant) {
		this.cant = cant;
	}

	@Column(name = "PRECIO", precision = 18, scale = 7)
	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	@Column(name = "DSCTO", precision = 18, scale = 7)
	public BigDecimal getDscto() {
		return this.dscto;
	}

	public void setDscto(BigDecimal dscto) {
		this.dscto = dscto;
	}

	@Column(name = "CAMMDA", precision = 18, scale = 7)
	public BigDecimal getCammda() {
		return this.cammda;
	}

	public void setCammda(BigDecimal cammda) {
		this.cammda = cammda;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FEC_REG", insertable = false, updatable = false, length = 23)
	public Date getFecReg() {
		return this.fecReg;
	}

	public void setFecReg(Date fecReg) {
		this.fecReg = fecReg;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FEC_MOD", length = 23)
	public Date getFecMod() {
		return this.fecMod;
	}

	public void setFecMod(Date fecMod) {
		this.fecMod = fecMod;
	}

	@Column(name = "ESTADO")
	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	
	@Column(name = "PROD_DESC", length = 300)
	public String getProdDesc() {
		return prodDesc;
	}

	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "apscPedidoDet")
	public Set<ApscEstProdXPed> getApscEstProdXPeds() {
		return this.apscEstProdXPeds;
	}

	public void setApscEstProdXPeds(Set<ApscEstProdXPed> apscEstProdXPeds) {
		this.apscEstProdXPeds = apscEstProdXPeds;
	}
	@Column(name = "CU", precision = 18, scale = 7)
	public BigDecimal getCu() {
		return cu;
	}

	public void setCu(BigDecimal cu) {
		this.cu = cu;
	}

}