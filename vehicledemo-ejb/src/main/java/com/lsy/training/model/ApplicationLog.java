package com.lsy.training.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NamedQueries({
	@NamedQuery(name=ApplicationLog.LOAD_ALL
			  , query="SELECT o FROM ApplicationLog o"),
    @NamedQuery(name=ApplicationLog.GET_AVG_DURATION_1      // Clasic Row/Column Berechnung zur Verobjektung im DAO
    		  , query="SELECT o.className, o.functionName" +
    		  		"      ,  AVG(o.endTimestamp-o.beginTimestamp) AS avgValue " +
    		  		"    FROM ApplicationLog o" +
    		  		"   GROUP BY o.className, o.functionName"),
    @NamedQuery(name=ApplicationLog.GET_AVG_DURATION_2      // inline, Verobjektung finder hier statt
	  , query="SELECT new com.lsy.training.model.ApplicationStat(" +
	  		"         o.className, o.functionName" +
	  		"      ,  AVG(o.endTimestamp-o.beginTimestamp) AS avgValue " +
	  		" )"+
	  		"    FROM ApplicationLog o" +
	  		"   GROUP BY o.className, o.functionName")    		  		
})
@Entity
@Table(name="LOG_APP_LOG")      // Bei diesem Obejt steuern wir den Tabellennamen
public class ApplicationLog {

	public static final String LOAD_ALL = "ApplicationLog.loadAll";
	public static final String GET_AVG_DURATION_1= "ApplicationLog.getAvgDuration1";
	public static final String GET_AVG_DURATION_2= "ApplicationLog.getAvgDuration2";
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	Long id;
	
	@Version    // Versionsfield für optmistic Locking in der DB, braucht keine getter/setter
	Long version;
	
	// @Temporal(TemporalType.TIMESTAMP)
	@NotNull
	// Date 
	Long beginTimestamp;
	
	// @Temporal(TemporalType.TIMESTAMP)
	@NotNull
	// Date 
	Long endTimestamp;
	
	@NotNull
	@Size(max=100)
	String className;
	
	@NotNull
	@Size(max=100)
	String functionName;
	
	@NotNull
	@Size(max=100)
	String comment;

	@ElementCollection
	Collection<String> parameters;
	
	@ElementCollection
	Collection<String> returnValues;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBeginTimestamp() {
		return beginTimestamp;
	}

	public void setBeginTimestamp(Long beginTimestamp) {
		this.beginTimestamp = beginTimestamp;
	}

	public Long getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(Long endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	@Transient
	public Date getBegin() {
		return new Date(beginTimestamp);
	}

	public void setBegin(Date beginTimestamp) {
		this.beginTimestamp = beginTimestamp.getTime();
	}

	@Transient
	public Date getEnd() {
		return new Date(endTimestamp);
	}

	public void setEnd(Date endTimestamp) {
		this.endTimestamp = endTimestamp.getTime();
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public Collection<String> getParameters() {
		return parameters;
	}

	public void setParameters(Collection<String> parameters) {
		this.parameters = parameters;
	}

	public Collection<String> getReturnValues() {
		return returnValues;
	}

	public void setReturnValues(Collection<String> returnValues) {
		this.returnValues = returnValues;
	}
	
	public void addParameter(String param) {
		if (parameters == null) {
			parameters = new ArrayList<>();
		}
		parameters.add(param);
	}
	
	public void addReturnValue(String param) {
		if (returnValues == null) {
			returnValues = new ArrayList<>();
		}
		returnValues.add(param);
	}
	
}
