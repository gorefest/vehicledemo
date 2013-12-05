package com.lsy.training.model;

import com.lsy.training.constraints.DefectRequiresComment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.lsy.training.indexing.EngineTypeBridge;
import com.lsy.training.indexing.EngineTypeFieldBridge;
import org.hibernate.search.annotations.*;

/**
 * Engine Entity
 */
@Entity
@Table(uniqueConstraints = {                        // Constraint. Achtung, DB-Namen!
	@UniqueConstraint(name="UIDX_ENGINE_ID",columnNames="engine_id")
})
@NamedQueries({
	@NamedQuery(name=Engine.GET_BY_ENGINE_ID
				,query="SELECT o FROM Engine o WHERE o.engineId = :engineId")
	
}	)
@Indexed
public class Engine extends AbstractEntity implements Identifiable{

	public static final String GET_BY_ENGINE_ID="Engine.getByEngineId";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@DocumentId
	Long id;
	
	@Version    // Versionsfield für optmistic Locking in der DB, braucht keine getter/setter
	Long version;

	public static enum EngineType {
		PETROL,
		DIESEL,
		PETROL_HYBRID,
		DIESEL_HYBRID,
		LPG,
		CNG,
		OTHER
	}

	@Enumerated(EnumType.ORDINAL)
	@Fields({
            @Field(name = "engine_type", bridge = @FieldBridge(impl = EngineTypeBridge.class)),
            @Field(name = "engine_type_int", bridge = @FieldBridge(impl = EngineTypeFieldBridge.class))
            })
    EngineType engineType;
	
	@NotNull
	Double kw;
	
	Integer cylinders;
	
	Integer ccm;

	@NotNull
	@Pattern(regexp = "[0-9]*/[0-9]*/[0-9]*")
	@Column(name="engine_id")
	String engineId;

	@Temporal(TemporalType.DATE)
	Date createDate;

	@Temporal(TemporalType.DATE)
	Date lastModified;
	
	@ManyToOne
	@IndexedEmbedded
    Vendor vendor;

	public Engine() {
		
	}
	
	
	
	public Engine(EngineType engineType, Double kw, Integer cylinders,
			Integer ccm, String engineId, Vendor vendor) {
		super();
		this.engineType = engineType;
		this.kw = kw;
		this.cylinders = cylinders;
		this.ccm = ccm;
		this.engineId = engineId;
		this.vendor = vendor;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EngineType getEngineType() {
		return engineType;
	}

	public void setEngineType(EngineType engineType) {
		this.engineType = engineType;
	}

	public Double getKw() {
		return kw;
	}

	public void setKw(Double kw) {
		this.kw = kw;
	}

	public Integer getCylinders() {
		return cylinders;
	}

	public void setCylinders(Integer cylinders) {
		this.cylinders = cylinders;
	}

	public Integer getCcm() {
		return ccm;
	}

	public void setCcm(Integer ccm) {
		this.ccm = ccm;
	}

	public String getEngineId() {
		return engineId;
	}

	public void setEngineId(String engineId) {
		this.engineId = engineId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
}
