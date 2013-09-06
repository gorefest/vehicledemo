package com.lsy.training.soapclient;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.lsy.training.dto.VehicleDto;
import com.lsy.training.ws.VehicleWebService;


/**
 * Dynamischer CXF-Webclient. Dies ist möglich, weil wir ein API-Modul haben und
 * direkt die im JAR enthaltenen Interfaces zum Binden des Webservice nutzen können
 *
 * Man kann auch mit wsdl2java oder mit JBoss' wsconsume eine WSDL in Java-Klassen umwandeln,
 * allerdings ist dieser Client dann statisch.
 *
 */
public class DynamicClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(VehicleWebService.class);
        factory.setAddress("http://localhost:8080/vehicledemo-ejb-1.0-SNAPSHOT/VehicleWebServiceImpl?wsdl");

        VehicleWebService client = (VehicleWebService) factory.create();

        for (VehicleDto dto : client.loadAll()) {
        	System.out.println(dto.getIdentifier());
        }
        
	}

}
