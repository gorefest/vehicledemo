package com.lsy.training.rest;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lsy.training.dto.VehicleDto;
import com.lsy.training.dto.VehicleList;
import com.lsy.training.service.dto.VehicleDtoService;

/**
 * Beispiel eines REST-Services, welcher mit Dto's arbeitet
 *
 * @see com.lsy.training.service.dto.VehicleDtoService
 *
 */
@Path("/vehicle/")  // Pfad relativ zum REST-Kontext ($server:$port/$webAppContext/$RestContext/vehicle)
                    //                                                  ^            ^           ^
                    //                                             aus EAR-Plugin    |           |
                    //                                                      aus JaxRsActivator   |
                    //                                                                       aus @Path

@Stateless          // REST-Services packt man klassischerweise in EJBs. Damit sind die Aufrufe darauf immer transaktional
public class VehicleRestService {

	@EJB            // Da ganz normales SLSB, sind auch Injections erlaubt
            VehicleDtoService dtoService;
	
	@Path("/")  // direkt an den Path des Beans hängen
	@GET        // wir nehmen hier einen GET an
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML}) // ... und geben - je nach Client - JSON  oder XML zurück
	public Collection<VehicleDto> loadAll() {
		return dtoService.loadAll();		
	}

	@GET            // wieder ein GET
	@Path("/{fgnr}")// <---------------------------------------------------------------------------o
                    //                                                                             |
                    // Diesmal nehmen wir aber einen String auf, welcher in die Methodenvariable fgnr gesteckt wird
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML}) //                           |
	public VehicleDto getByFgnr(@PathParam("fgnr") String fgnr) {     //                           |
        //                                     ^---------------------------------------------------o
		return dtoService.getByFgnr(fgnr);
	}
	
	@GET            // noch ein Getter für alle
	@Path("/all")   // ,,, diesmal aber über eine "echte" URL, während wir oben eher dem WebDAV-Directory-Gedanken nachhingen
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public VehicleList loadAllVehicles() {
		return new VehicleList(dtoService.loadAll());
	}
	
	@POST  // hier nehmen wir einen POST, welcher JSON oder XML Entity-Bodies verträgt.
           // das Unmarshalling wird direkt vom Container in den Parameter übernommen
           // In unserer Definition ist POST gleich dem Speichern eines neuen Objekts ...
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public void saveNewVehicle(VehicleDto dto) {
		dtoService.saveVehicle(dto);
	}
	
	@PUT   // ... währen PUT für das Aktualisieren zuständig ist
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public void saveVehicle(VehicleDto dto) {
		dtoService.updateVehicle(dto);
	}
}
