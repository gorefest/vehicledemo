package com.lsy.training.service;

import java.util.Date;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstrationsbean für asynchrone Ausführung.
 * Der Aufruf dieser Funktionen führt dazu, das die Verarbeitung in einem Threadpool nebenläufig
 * stattfindet
 *
 * @see InitializerService -  dort werden diese Funktionen gerufen
 *
 *
 */
@Stateless
public class NumberService {

	private final Logger logger = LoggerFactory.getLogger(NumberService.class);
	
	@EJB
	EngineService engineService;
	
	@EJB
    VendorService vendorService;
	
	@EJB
	VehicleService vehicleService;


    /**
     * Fire-And-Forget-Skalierung. Spielt das Ergebnis der Ausführung keine Rolle für den
     * Aufrufer, dann kann man einfach eine vod-Funktion mit dem @Asynchronous versehen.
     * Die Tatsache, dass es keinen Rückgabewert gibt, lässt diese Funktion ganz intuitiv
     * wie eine normale Funktion nutzen.
     *
     * @param l - ein beliebiger Longwert
     */
	@Asynchronous
	public void doSomething(Long l) {
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("Doing something "+l+" at "+new Date().getTime());
	}

    /**
     * Funktion mit Rückgabewert. Hierbei wird ein Future auf ein zukünftiges Ergebnis zurückgegeben
     * Dieses Future wird im Aufrufer iteriert. Wenn man am Ende der Liste angekommen ist, sind definitiv
     * alle Objekte verarbeitet worde.
     *
    * @param l
     * @return
     */
	@Asynchronous
	public Future<Long> doSomethingImportant(Long l) {
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		logger.info("Doing something "+l+" at "+new Date().getTime());
		
		return new AsyncResult<Long>(new Date().getTime() % l);
	}
	
	public void initData() {
		vendorService.initData();
		engineService.initData();
		vehicleService.initData();
			
	}
	
		
	
}
