package com.lsy.training.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Abstrakte Superklasse, welche die PreUpdate und PrePersist
 * Hooks and die Entities heruntervererbt, ohne selbst
 * Bestandteil der Vererbungshierarchie zu sein
 *
 */
@MappedSuperclass // Hiermit signalisieren wir, dass diese Klasse nicht zur Entity-Vererbung gehört
public abstract class AbstractEntity implements Timestamped {

    @PrePersist // Hooks, die beim Speichern bzw. Aktualisieren der Entity gerufen werden
    @PreUpdate  // Dies passiert idR zur Detach-Phase
    public void prePersist() {
		Date d = new Date();
		if (getCreateDate() == null) {
			setCreateDate(d);
		}
		setLastModified(d);		
	}
	
	
}
