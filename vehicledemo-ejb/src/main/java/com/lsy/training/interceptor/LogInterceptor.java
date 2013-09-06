package com.lsy.training.interceptor;

import java.util.Date;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import com.lsy.training.dto.Dto;
import com.lsy.training.model.ApplicationLog;
import com.lsy.training.service.ApplicationLogService;

/**
 * Log Interceptor, welcher Start und EndeZeiten misst und
 * je nach Parameter / Rückgabewert an die EmbeddedCollection des
 * LogObjekts Identifikationsmerkmale der Transportobjekte
 * anhängt
 */
public class LogInterceptor {

	@EJB
	ApplicationLogService applicationLogService;

    // @Resource
    // Session session;
	
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
		Object result;
		
		ApplicationLog log = new ApplicationLog();
		log.setBegin(new Date());
		log.setClassName(context.getTarget().getClass().getSimpleName());
		log.setFunctionName(context.getMethod().getName());
		log.setComment("done with LogInterceptor");
		
		Object[] params = context.getParameters();
		
		for (Object object : params) {
			checkParameter(log, object);
		}
		
		try {
            // Hier springen wir in die abgefangene Funktion
			result = context.proceed();

            // Hier wird das Ergebnis ausgewertet
			if (result != null) {
				if (result instanceof Iterable<?>) {
					Iterable<?> iter = (Iterable<?>) result;
					for (Object object : iter) {
						checkResult(log, object);
					}
				} else {
					checkResult( log, result);
				}
			} 
			
		} finally {
			log.setEnd(new Date());
			applicationLogService.saveNewLogEntry(log);
		}
		
		return result;
	}

    /**
     * prüft, ob ein Objekt ein DTO ist und hängt
     * das Identifikationsmermal ggf an die Parameterliste an
     *
     * @param log das LogObjekt
     * @param object das zu prüfende Objekt
     */
	private void checkParameter(ApplicationLog log, Object object) {
		if (object instanceof Dto) {
			log.addParameter(((Dto) object).getIdentifier());
		}
	}

    /**
     * prüft, ob ein Objekt ein DTO ist und hängt
     * das Identifikationsmermal ggf an die Parameterliste an
     *
     * @param log das LogObjekt
     * @param object das zu prüfende Objekt
     */
	private void checkResult(ApplicationLog log, Object object) {
		if (object instanceof Dto) {
			log.addReturnValue(((Dto) object).getIdentifier());
		}
	}
	
}
