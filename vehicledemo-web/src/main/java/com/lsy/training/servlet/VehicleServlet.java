package com.lsy.training.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;

import com.lsy.training.dto.VehicleList;
import com.lsy.training.service.dto.VehicleDtoService;

/**
 * Billiges, kleines JAXB-Servlet, welches mit der Servlet API 3.0 eingehangen wird
 * Bezeichnend hierfür ist die Abwesenheit einer web.xml und das Deklarieren über Annotationen
 *
 */
@WebServlet(displayName="Vehicle Servlet"
		  , name="vehicleServlet"
		  , urlPatterns="/vehicleServlet")	
public class VehicleServlet extends HttpServlet{

	@Inject
	VehicleDtoService vehicleDtoService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		VehicleList list = new VehicleList(vehicleDtoService.loadAll());
		
		JAXB.marshal(list, resp.getOutputStream());
		
	}
	
}
