package com.lsy.training.model;

import java.util.Date;

/**
 * Interface, welches beschreibt, dass das Objekt einen Erzeugungs- und Änderungszeitstempel hat
 */
public interface Timestamped {
	
	public Date getLastModified();
	public Date getCreateDate();
	
	public void setLastModified(Date date);
	public void setCreateDate(Date date);

}
