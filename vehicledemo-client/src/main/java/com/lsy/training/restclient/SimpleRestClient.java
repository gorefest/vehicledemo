package com.lsy.training.restclient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.bind.JAXB;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

import com.lsy.training.dto.VehicleDto;
import com.lsy.training.dto.VehicleList;

/**
 * Bareknuckle HTTP Rest Client. Arbeitet mit JSON, kann aber auch mit XML Rest arbeiten
 */
public class SimpleRestClient {

	private static final String REST_BASE_URL="http://localhost:8080/vehicledemo/rest";
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		VehicleDto vehicleDto = getVehicleByFgnr("WWAUZ12345678");
		System.out.println(vehicleDto.getIdentifier());
		
		VehicleDto probe = new VehicleDto();
		probe.setModelName("ETRON");
		probe.setEngineId(vehicleDto.getEngineId());
		probe.setPrice(new BigDecimal("25.0"));
		probe.setVendorName(vehicleDto.getVendorName());
		probe.setFgnr("WWAUZ92345672");

		saveVehicle(probe);

		probe.setVendorName("VOLKSWAGEN");
		probe.setPrice(new BigDecimal("37.0"));

		updateVehicle(probe);

		
		VehicleList vl = loadAll();
		for (VehicleDto vehicleDto2 : vl.getVehicles()) {
			System.out.println(vehicleDto2.getIdentifier());
		}
	}

	public static VehicleList loadAll() throws ClientProtocolException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(REST_BASE_URL+"/vehicle/all");
		// get.addHeader(HttpHeaders.ACCEPT,ContentType.APPLICATION_XML.getMimeType());
		get.addHeader(HttpHeaders.ACCEPT,ContentType.APPLICATION_JSON.getMimeType());
			HttpResponse response = client.execute(get);
		// VehicleList result = JAXB.unmarshal(response.getEntity().getContent(), VehicleList.class);
		VehicleList result = new ObjectMapper().readValue(response.getEntity().getContent(), VehicleList.class);
		return result;
	}
	
	public static VehicleDto getVehicleByFgnr(String fgnr) throws ClientProtocolException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(REST_BASE_URL+"/vehicle/"+fgnr);
		get.addHeader(HttpHeaders.ACCEPT, "application/xml");
		HttpResponse response = client.execute(get);
		VehicleDto result = JAXB.unmarshal(response.getEntity().getContent(), VehicleDto.class);
		return result;
	}
	
	public static void saveVehicle(VehicleDto vehicleDto) throws ClientProtocolException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(REST_BASE_URL+"/vehicle/");
		BasicHttpEntity entity= new BasicHttpEntity();
		
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		JAXB.marshal(vehicleDto, bo);
		
		entity.setContent(new ByteArrayInputStream(bo.toByteArray()));
		
		entity.setContentType(ContentType.APPLICATION_XML.getMimeType());
		
		post.setEntity(entity);

		HttpResponse response = client.execute(post);
		
		if (!(response.getStatusLine().getStatusCode() >= 200 
			&& response.getStatusLine().getStatusCode() <= 205)) {
			throw new RuntimeException("ARGL ARGL ARGL!");
		}
		
	}
	
	public static void updateVehicle(VehicleDto vehicleDto) throws ClientProtocolException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPut put = new HttpPut(REST_BASE_URL+"/vehicle/");
		BasicHttpEntity entity= new BasicHttpEntity();
		
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		JAXB.marshal(vehicleDto, bo);
		
		entity.setContent(new ByteArrayInputStream(bo.toByteArray()));
		
		entity.setContentType(ContentType.APPLICATION_XML.getMimeType());
		
		put.setEntity(entity);

		HttpResponse response = client.execute(put);
		
		if (!(response.getStatusLine().getStatusCode() >= 200 
			&& response.getStatusLine().getStatusCode() <= 205)) {
			throw new RuntimeException("ARGL ARGL ARGL!");
		}
		
	}

}
