package com.lsy.training.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 * Fahrzeug-Entity
 *
 *
 */
@NamedQueries({
	@NamedQuery(name = Vehicle.GET_BY_FNGR
		      , query="SELECT o FROM Vehicle o WHERE o.fgnr = :fgnr"),
  	@NamedQuery(name = Vehicle.LOAD_ALL_COMPLETELY  // Hier wird über JOIN FETCHes dafür gesorgt,
                                                    // dass die Entity keinen LazyLoad mehr macht
      , query="SELECT o FROM Vehicle o JOIN FETCH o.engine JOIN FETCH o.vendor")
})
@Entity
@Indexed
public class Vehicle extends AbstractEntity implements Identifiable{

	
	public static final String GET_BY_FNGR="Vehicle.getByFgnr";
	public static final String LOAD_ALL_COMPLETELY ="Vehicle.loadAllCompletely";
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@DocumentId
	private Long id;
	
	@Version
	private Long version;

    // Constraints
	@NotNull
	@Size(min=13, max=13)
	@Pattern(regexp="[A-Z][A-Z][A-Z][A-Z][A-Z][0-9]*")
	@Column(unique = true)
	private String fgnr;
	
	@NotNull
	@Size(max=30)
	@Field
	@Boost(5.0f)
	private String modelName;
	
	@ManyToOne(optional=false)
	@NotNull
	@Valid
	@IndexedEmbedded
    Vendor vendor;
	
	@ManyToOne(optional=false)
	@NotNull
	@Valid
	@IndexedEmbedded
	Engine engine;
	
	@NotNull
	@Min(0L)
	BigDecimal price;

    @Temporal(TemporalType.DATE)
    Date initialRegistrationDate;
	
	public Vehicle() {
		
	}
	
	public Vehicle(String fgnr, String modelName, Vendor vendor,
			Engine engine, BigDecimal price, Date initialRegistrationDate) {
		super();
		this.fgnr = fgnr;
		this.modelName = modelName;
		this.vendor = vendor;
		this.engine = engine;
		this.price = price;
        this.initialRegistrationDate = initialRegistrationDate;
	}



	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@Temporal(TemporalType.DATE)
	private Date lastModified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getFgnr() {
		return fgnr;
	}

	public void setFgnr(String fgnr) {
		this.fgnr = fgnr;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
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

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", fgnr=" + fgnr + ", modelName="
				+ modelName + ", vendor=" + vendor + "]";
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

    public Date getInitialRegistrationDate() {
        return initialRegistrationDate;
    }

    public void setInitialRegistrationDate(Date initialRegistrationDate) {
        this.initialRegistrationDate = initialRegistrationDate;
    }
}
