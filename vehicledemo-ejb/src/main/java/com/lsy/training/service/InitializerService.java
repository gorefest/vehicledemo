/**
 * 
 */
package com.lsy.training.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.lsy.training.dao.ApplicationLogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lsy.training.model.ApplicationLog;

/**
 * Initializerbean. Dieses Bean ist ein Singleton, dessen @PostConstruct nach dem Deployment durchlaufen+
 * wird. Dies wird durch die @Startup-Annotation erreicht
 *
 */
@Singleton  // Singleton-Bean = 1 Instanz pro JVM, im Cluster ein "Multington"
@Startup    // soll nach Deploy ausgeführt werden
public class InitializerService {

	private final Logger log = LoggerFactory.getLogger(InitializerService.class);
	
	@EJB
    ApplicationLogDao applicationLogDao;
	
	@EJB
	NumberService numberService;
	
	@EJB
	EngineService engineService;
	
	@EJB
    VendorService vendorService;
	
	@EJB
	VehicleService vehicleService;
	
	@EJB
	CustomerUnitService customerUnitService;
	
	@EJB
	FleetItemService fleetItemService;

    /**
     * Lifecycle-Management-Funktion. Das PostConstruct wird nach dem Injizieren der Abhängigkeiten, nach
     * der "Inbetriebnahme" des EJBs (@Startup) und vor der Auslieferung an andere EJBs durchgeführt.
     * Wir wissen bei dieser Annotation, dass die Methode ausgeführt wird, bevor wir das Bean bei DI erhalten,
     * aber nicht wann. Normalerweise wird diese Funktion gerufen, bevor eine EJB-Methode eines referenzierenden
     * Beans gerufen (vor dem Aufruf)
     *
     * Hier wird durch @Startup quasi ein Post-Deploy-Trigger in die Anwendung eingebaut.
     *
     */
	@PostConstruct
	public void postConstruct() {
		log.info("BEGIN INIT");

		numberService.initData();
		
		customerUnitService.initData();
		
		fleetItemService.initFleetData();
		
		log.info("END INIT");
	}

    /**
     * Neuer Timer. Die JEE 6 Timer verwenden die Quartz-Notation und müssen - im Gegensatz zu den Timern
     * aus JEE 5 - nicht initialisiert werden.
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
	// @Schedule(hour="*", minute="*", second="0,15,30,45")
	public void scheduleOutput() throws InterruptedException, ExecutionException {
		log.info("BEGIN OUTPUT");
		
		Collection<ApplicationLog> logs = applicationLogDao.loadAll();
		
		for (ApplicationLog applicationLog : logs) {
			log.info(applicationLog.getComment() + " at " 
						+ applicationLog.getBeginTimestamp() 
						+ " duration " 
						+ (applicationLog.getEndTimestamp() - applicationLog.getBeginTimestamp()));
		}
		
		
		List<Future<Long>> futures = new ArrayList<Future<Long>>();
		for (Long l  = 1L; l < 25L; ++l) {
			futures.add(numberService.doSomethingImportant(l));
		}
		
		Thread.sleep(250);
		
		for (Future<Long> future : futures) {
			log.info("Result is "+future.get());
		}
		
		log.info("END OUTPUT");
	}
	
}
