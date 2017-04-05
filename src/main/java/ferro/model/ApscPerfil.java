package ferro.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

//, GrantedAuthority
@Entity
@Table(name = "APSC_PERFIL",schema="ERP"
/*"dbo"
,catalog="ERP"*/)
public class ApscPerfil implements java.io.Serializable, GrantedAuthority  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idPerfil;
	private String cdPerfil;
	private String descripcion;
	private Date fecReg;
	private Date fecMod;
	private String usuReg;
	private String usuMod;
	private Boolean estado;
	private Set<ApscUsuarios> apscUsuarioses = new HashSet<ApscUsuarios>(0);

	public ApscPerfil() {
	}

	public ApscPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public ApscPerfil(long idPerfil, String cdPerfil, String descripcion,
			Date fecReg, Date fecMod, String usuReg, String usuMod,
			Boolean estado, Set<ApscUsuarios> apscUsuarioses) {
		this.idPerfil = idPerfil;
		this.cdPerfil = cdPerfil;
		this.descripcion = descripcion;
		this.fecReg = fecReg;
		this.fecMod = fecMod;
		this.usuReg = usuReg;
		this.usuMod = usuMod;
		this.estado = estado;
		this.apscUsuarioses = apscUsuarioses;
	}

	@Id
	@Column(name = "ID_PERFIL", unique = true, nullable = false)
	public long getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}

	@Column(name = "CD_PERFIL", length = 16)
	public String getCdPerfil() {
		return this.cdPerfil;
	}

	public void setCdPerfil(String cdPerfil) {
		this.cdPerfil = cdPerfil;
	}

	@Column(name = "DESCRIPCION", length = 128)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FEC_REG", length = 23)
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

	@Column(name = "USU_REG", length = 16)
	public String getUsuReg() {
		return this.usuReg;
	}

	public void setUsuReg(String usuReg) {
		this.usuReg = usuReg;
	}

	@Column(name = "USU_MOD", length = 16)
	public String getUsuMod() {
		return this.usuMod;
	}

	public void setUsuMod(String usuMod) {
		this.usuMod = usuMod;
	}

	@Column(name = "ESTADO")
	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "apscPerfil")
	public Set<ApscUsuarios> getApscUsuarioses() {
		return this.apscUsuarioses;
	}

	public void setApscUsuarioses(Set<ApscUsuarios> apscUsuarioses) {
		this.apscUsuarioses = apscUsuarioses;
	}
	
	@Transient
	public String getAuthority() {		
		return getDescripcion();
	}

}