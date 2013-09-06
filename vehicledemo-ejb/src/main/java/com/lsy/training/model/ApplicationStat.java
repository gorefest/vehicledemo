package com.lsy.training.model;

/**
 * Transientes Statistik-Objekt, welches im
 * ApplicationLogDao einmal classic (Row/Column based) und
 * einmal per inline Konstruktor in JPQL gerufen wird
 *
 * @see com.lsy.training.dao.ApplicationLogDao
 * @see ApplicationLog (Named Queries)
 *
 */
public class ApplicationStat {
	
	String className;
	String functionName;
	Double avgDuration;
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
	public Double getAvgDuration() {
		return avgDuration;
	}
	public void setAvgDuration(Double avgDuration) {
		this.avgDuration = avgDuration;
	}
	public ApplicationStat() {
		
	}
	public ApplicationStat(String className, String functionName,
			Double avgDuration) {
		super();
		this.className = className;
		this.functionName = functionName;
		this.avgDuration = avgDuration;
	}
	
	public ApplicationStat(Object className, Object functionName,
			Object avgDuration) {
		this((String) className, (String) functionName, (Double) avgDuration);
	}
	@Override
	public String toString() {
		return "ApplicationStat [className=" + className + ", functionName="
				+ functionName + ", avgDuration=" + avgDuration + "]";
	}
	
	
	
}
